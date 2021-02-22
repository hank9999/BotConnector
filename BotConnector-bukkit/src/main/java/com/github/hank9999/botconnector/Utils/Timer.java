package com.github.hank9999.botconnector.Utils;

import com.github.hank9999.botconnector.BotConnectorBukkit;
import com.github.hank9999.botconnector.Log.LogCollector;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Iterator;
import java.util.TimerTask;

public class Timer {
    private Plugin plugin;

    public void queue() {
        this.plugin = BotConnectorBukkit.plugin;
        final java.util.Timer timer = new java.util.Timer(true); // We use a timer cause the Bukkit scheduler is affected by server lags
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!plugin.isEnabled()) { // Plugin was disabled
                    timer.cancel();
                    return;
                }
                Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> checkQueue());
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
        this.plugin = BotConnectorBukkit.plugin;
        final java.util.Timer timer = new java.util.Timer(true); // We use a timer cause the Bukkit scheduler is affected by server lags
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!plugin.isEnabled()) { // Plugin was disabled
                    timer.cancel();
                    return;
                }
                Bukkit.getScheduler().runTaskAsynchronously(plugin, LogCollector::check);
            }
        }, 0, 500);
    }
}
