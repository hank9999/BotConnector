package com.github.hank9999.botconnector.Events.bukkit;

import com.github.hank9999.botconnector.Libs.Json;
import com.github.hank9999.botconnector.Utils.WebSocket;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.RemoteServerCommandEvent;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class RconCommandEvent implements Listener {
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onCommand(RemoteServerCommandEvent event) {
        Map<String, String> map = new LinkedHashMap<String, String>() {
            {
                put("type", "RconCommand");
                put("command", event.getCommand());
            }
        };
        map = Collections.unmodifiableMap(map);
        WebSocket.sendMessage(Json.Serialization(map));
    }
}
