package com.github.hank9999.botconnector.utils;

import com.github.hank9999.botconnector.BotConnectorBukkit;
import com.github.hank9999.botconnector.libs.Json;
import com.github.hank9999.botconnector.log.LogCollector;
import org.bukkit.Bukkit;

import java.util.*;

public class Timer {
    public void queue() {
        final java.util.Timer timer = new java.util.Timer(true); // We use a timer cause the Bukkit scheduler is affected by server lags
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (BotConnectorBukkit.plugin == null || !BotConnectorBukkit.plugin.isEnabled()) { // Plugin was disabled
                    timer.cancel();
                    return;
                }
                Bukkit.getScheduler().runTaskAsynchronously(BotConnectorBukkit.plugin, () -> checkQueue());
            }
        }, 0, 1000 * 5);
    }

    private void checkQueue() {
        if (WebSocket.queue.size() != 0 && WebSocket.Connected) {
            Iterator<String> iterator = WebSocket.queue.iterator();
            while (iterator.hasNext()){
                if (WebSocket.Connected) {
                    WebSocket.sendMessage(iterator.next());
                    iterator.remove();
                }
            }
        }
    }

    public void ConsoleLog() {
        final java.util.Timer timer = new java.util.Timer(true); // We use a timer cause the Bukkit scheduler is affected by server lags
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (BotConnectorBukkit.plugin == null || !BotConnectorBukkit.plugin.isEnabled()) { // Plugin was disabled
                    timer.cancel();
                    return;
                }
                Bukkit.getScheduler().runTaskAsynchronously(BotConnectorBukkit.plugin, LogCollector::check);
            }
        }, 0, 500);
    }

    public void ws() {
        final java.util.Timer timer = new java.util.Timer(true); // We use a timer cause the Bukkit scheduler is affected by server lags
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (BotConnectorBukkit.plugin == null || !BotConnectorBukkit.plugin.isEnabled()) { // Plugin was disabled
                    timer.cancel();
                    return;
                }
                Bukkit.getScheduler().runTaskAsynchronously(BotConnectorBukkit.plugin, () -> checkWs());
            }
        }, 0, 1000 * 30);
    }

    private void checkWs() {
        String time = String.valueOf(System.currentTimeMillis());
        time = time.substring(0, time.length() - 3);
        String finalTime = time;
        Map<String, String> map = new LinkedHashMap<String, String>() {
            {
                put("type", "checkWs");
                put("time", finalTime);
            }
        };
        map = Collections.unmodifiableMap(map);
        WebSocket.sendMessage(Json.Serialization(map));
    }
}
