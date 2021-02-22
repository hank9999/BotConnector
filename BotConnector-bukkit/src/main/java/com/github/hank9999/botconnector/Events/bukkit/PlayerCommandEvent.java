package com.github.hank9999.botconnector.Events.bukkit;

import com.github.hank9999.botconnector.Libs.Json;
import com.github.hank9999.botconnector.Utils.WebSocket;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerCommandEvent implements Listener {
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        Map<String, String> map = new LinkedHashMap<String, String>() {
            {
                put("type", "PlayerCommand");
                put("username", ChatColor.stripColor(p.getName().trim()));
                put("uuid", String.valueOf(p.getUniqueId()));
                put("command", event.getMessage());
            }
        };
        map = Collections.unmodifiableMap(map);
        WebSocket.sendMessage(Json.Serialization(map));
    }
}
