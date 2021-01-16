package com.github.hank9999.botconnector.Utils;

import com.github.hank9999.botconnector.BotConnector;
import com.github.hank9999.botconnector.Libs.Config;
import com.github.hank9999.botconnector.Libs.URL;
import com.github.hank9999.botconnector.Libs.WsClient;
import org.bukkit.scheduler.BukkitRunnable;
import org.java_websocket.enums.ReadyState;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class WebSocket {
    public static Boolean Connected = false;
    public static WsClient myClient;
    public static List<String> queue = new ArrayList<>();

    public static void Init(String url, String token, String name) {
        try {
            myClient = new WsClient(new URI(url + "?token=" + URL.Encoder(token) + "&name=" + URL.Encoder(name)));
            myClient.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Reconnect() {
        if (BotConnector.plugin != null) {
            (new BukkitRunnable() {
                public void run() {
                    try {
                        System.out.println("WebSocket Reconnecting...");
                        myClient.reconnect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).runTaskLaterAsynchronously(BotConnector.plugin, 8 * 20L);
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
