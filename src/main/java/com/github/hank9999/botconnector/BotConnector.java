package com.github.hank9999.botconnector;

import com.github.hank9999.botconnector.Events.*;
import com.github.hank9999.botconnector.Libs.Config;
import com.github.hank9999.botconnector.Utils.Timer;
import com.github.hank9999.botconnector.Utils.WebSocket;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class BotConnector extends JavaPlugin {

    public static BotConnector plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Config.loadConfig();
        getLogger().info(ChatColor.AQUA + "Config Loaded");
        WebSocket.Init(Config.url, Config.token, Config.name);
        if (Config.ChatEvent) {
            getServer().getPluginManager().registerEvents(new ChatEvent(), this);
            getLogger().info(ChatColor.AQUA + "ChatEvent registered");
        }
        if (Config.PlayerLoginEvent) {
            getServer().getPluginManager().registerEvents(new PlayerLoginEvent(), this);
            getLogger().info(ChatColor.AQUA + "PlayerLoginEvent registered");
        }
        if (Config.PlayerLogoutEvent) {
            getServer().getPluginManager().registerEvents(new PlayerLogoutEvent(), this);
            getLogger().info(ChatColor.AQUA + "PlayerLogoutEvent registered");
        }
        if (Config.PlayerCommandEvent) {
            getServer().getPluginManager().registerEvents(new PlayerCommandEvent(), this);
            getLogger().info(ChatColor.AQUA + "PlayerCommandEvent registered");
        }
        if (Config.RconCommandEvent) {
            getServer().getPluginManager().registerEvents(new RconCommandEvent(), this);
            getLogger().info(ChatColor.AQUA + "RconCommandEvent registered");
        }
        new Timer().queue();
    }

    @Override
    public void onDisable() {
        plugin = null;
        WebSocket.Close();
    }
}
