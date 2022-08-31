package com.github.hank9999.botconnector.utils;

import com.github.hank9999.botconnector.BotConnectorBukkit;
import com.github.hank9999.botconnector.libs.URL;
import com.github.hank9999.botconnector.libs.WsClient;
import org.bukkit.scheduler.BukkitRunnable;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class WebSocket {
    public static Boolean Connected = false;
    public static WsClient myClient;
    public static final List<String> queue = new ArrayList<>();

    public static void Init(String url, String token, String name) {
        try {
            myClient = new WsClient(new URI(url + "?token=" + URL.Encoder(token) + "&name=" + URL.Encoder(name)));
            myClient.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Reconnect() {
        if (BotConnectorBukkit.plugin != null) {
            (new BukkitRunnable() {
                public void run() {
                    try {
                        BotConnectorBukkit.logger.info("WebSocket Reconnecting...");
                        myClient.reconnect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).runTaskLaterAsynchronously(BotConnectorBukkit.plugin, 8 * 20L);
        }
    }

    public static void sendMessage(String text) {
        if (!Connected) {
            queue.add(text);
        } else {
            myClient.send(text);
        }
    }

    public static void Close() {
        myClient.close();
    }
}
