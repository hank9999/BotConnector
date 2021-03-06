package com.github.hank9999.botconnector;

import com.github.hank9999.botconnector.Events.bukkit.*;
import com.github.hank9999.botconnector.Libs.Config;
import com.github.hank9999.botconnector.Log.MessageInterceptingCommandRunner;
import com.github.hank9999.botconnector.Utils.LogSetout;
import com.github.hank9999.botconnector.Utils.Timer;
import com.github.hank9999.botconnector.Utils.Updater;
import com.github.hank9999.botconnector.Utils.WebSocket;
import com.github.hank9999.botconnector.bStats.MetricsLite;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class BotConnectorBukkit extends JavaPlugin {

    public static BotConnectorBukkit plugin;
    public static MessageInterceptingCommandRunner cmdRunner;

    @Override
    public void onEnable() {
        plugin = this;
        cmdRunner = new MessageInterceptingCommandRunner(getServer().getConsoleSender());

        Config.loadConfig();
        getLogger().info(ChatColor.AQUA + "Config Loaded");

        WebSocket.Init(Config.url, Config.token, Config.name);

        if (Config.ConsoleLogForward.enable) {
            LogSetout.append();
            new Timer().ConsoleLog();
            getLogger().info(ChatColor.AQUA + "ConsoleLogForward registered");
        }

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

        try {
            new MetricsLite(this, 10224);
            getLogger().info(ChatColor.AQUA + "bStats Metrics Enable");
        } catch (Exception exception) {
            getLogger().warning("An error occurred while enabling bStats Metrics!");
        }
        new Timer().queue();
        new Updater();
    }

    @Override
    public void onDisable() {
        plugin = null;
        cmdRunner = null;
        WebSocket.Close();
        LogSetout.remove();
    }
}
