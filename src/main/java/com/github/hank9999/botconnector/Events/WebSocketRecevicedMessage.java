package com.github.hank9999.botconnector.Events;

import com.github.hank9999.botconnector.BotConnector;
import com.github.hank9999.botconnector.Libs.Config;
import com.github.hank9999.botconnector.Libs.Json;
import com.github.hank9999.botconnector.Utils.WebSocket;
import com.google.gson.Gson;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

class ReceivedMessage {
    String type;
    String name;
    Long sn;
    String command;
}

public class WebSocketRecevicedMessage {
    public void handler(String message) {
        ReceivedMessage item =new Gson().fromJson(message, ReceivedMessage.class);
        if (item.type == null) {
            return;
        }
        if (item.name == null) {
            return;
        }
        if (!item.name.equals("__ALL__")) {
            if (!item.name.equals(Config.name)) {
                return;
            }
        }
        if (item.sn == null) {
            return;
        }
        String type = item.type.toLowerCase();
        switch (type) {
            case "status": getStatus(item.sn);break;
            case "command": runCommand(item.sn, item.command);break;
            default:
        }
    }

    void getStatus(Long sn) {
        List<String> onlinePlayer = new ArrayList<>();
        for (Player p : BotConnector.plugin.getServer().getOnlinePlayers()) {
            onlinePlayer.add(p.getName());
        }
        Map<String, Object> map = new LinkedHashMap<String, Object>() {
            {
                put("type", "status");
                put("sn", sn);
                put("version", BotConnector.plugin.getServer().getVersion());
                put("onlinePlayer", onlinePlayer);
            }
        };
        map = Collections.unmodifiableMap(map);
        WebSocket.sendMessage(Json.Serialization(map));
    }

    void runCommand(Long sn, String command) {
        new BukkitRunnable() {
            public void run() {
                boolean status = BotConnector.plugin.getServer().dispatchCommand(BotConnector.cmdRunner, command);
                String CommandReturn = BotConnector.cmdRunner.getMessageLogStripColor();
                BotConnector.cmdRunner.clearMessageLog();

                if (CommandReturn.length() == 0) {
                    if (status) {
                        CommandReturn = "success";
                    } else {
                        CommandReturn = "failure";
                    }
                }

                String finalCommandReturn = CommandReturn;
                Map<String, Object> map = new LinkedHashMap<String, Object>() {
                    {
                        put("type", "command");
                        put("sn", sn);
                        put("return", finalCommandReturn);
                    }
                };

                map = Collections.unmodifiableMap(map);
                WebSocket.sendMessage(Json.Serialization(map));
            }
        }.runTask(BotConnector.plugin);
    }
}
