package com.github.hank9999.botconnector.Events.bukkit;

import com.github.hank9999.botconnector.Libs.Json;
import com.github.hank9999.botconnector.Utils.WebSocket;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerLoginEvent implements Listener {
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onLogin(AsyncPlayerPreLoginEvent event) {
        Map<String, String> map = new LinkedHashMap<String, String>() {
            {
                put("type", "Login");
                put("username", event.getName());
                put("uuid", String.valueOf(event.getUniqueId()));
                put("ip", String.valueOf(event.getAddress()));
            }
        };
        map = Collections.unmodifiableMap(map);
        WebSocket.sendMessage(Json.Serialization(map));
    }
}
