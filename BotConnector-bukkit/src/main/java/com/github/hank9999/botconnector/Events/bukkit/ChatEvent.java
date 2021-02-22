package com.github.hank9999.botconnector.Events.bukkit;

import com.github.hank9999.botconnector.Libs.Json;
import com.github.hank9999.botconnector.Utils.WebSocket;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChatEvent implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        Map<String, String> map = new LinkedHashMap<String, String>() {
            {
                put("type", "Chat");
                put("username", ChatColor.stripColor(p.getName().trim()));
                put("uuid", String.valueOf(p.getUniqueId()));
                put("text", ChatColor.stripColor(event.getMessage().trim()));
            }
        };
        map = Collections.unmodifiableMap(map);
        WebSocket.sendMessage(Json.Serialization(map));
    }
}