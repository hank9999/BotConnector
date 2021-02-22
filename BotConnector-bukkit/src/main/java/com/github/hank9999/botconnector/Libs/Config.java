package com.github.hank9999.botconnector.Libs;

import com.github.hank9999.botconnector.BotConnectorBukkit;

import java.util.List;

public class Config {

    public static String url = "ws://example.com";
    public static String name = "server";
    public static String token = "ffffffff-ffff-ffff-ffff-ffffffffffff";
    public static class ConsoleLogForward {
        public static Boolean enable = false;
        public static class filter {
            public static Boolean enable = false;
            public static List<String> list;
        }
    }
    public static Boolean ChatEvent = false;
    public static Boolean PlayerLoginEvent = false;
    public static Boolean PlayerLogoutEvent = false;
    public static Boolean PlayerCommandEvent = false;
    public static Boolean RconCommandEvent = false;

    public static void loadConfig() {
        BotConnectorBukkit.plugin.saveDefaultConfig();
        BotConnectorBukkit.plugin.reloadConfig();

        setValue();
    }

    public static void reloadConfig() {
        BotConnectorBukkit.plugin.saveDefaultConfig();
        BotConnectorBukkit.plugin.reloadConfig();

        setValue();
    }

    public static void saveConfig() {
        BotConnectorBukkit.plugin.saveConfig();
        reloadConfig();
    }

    public static void setConfig(String path, Object value) {
        BotConnectorBukkit.plugin.getConfig().set(path, value);
    }

    private static String getString(String path) {
        return BotConnectorBukkit.plugin.getConfig().getString(path);
    }

    private static List<String> getStringList(String path) {
        return BotConnectorBukkit.plugin.getConfig().getStringList(path);
    }

    private static int getInt(String path) {
        return BotConnectorBukkit.plugin.getConfig().getInt(path);
    }

    private static long getLong(String path) {
        return BotConnectorBukkit.plugin.getConfig().getLong(path);
    }

    private static Boolean getBoolean(String path) {
        return BotConnectorBukkit.plugin.getConfig().getBoolean(path);
    }

    public static void setValue() {
        url = getString("url");
        name = getString("name");
        token = getString("token");
        ConsoleLogForward.enable = getBoolean("ConsoleLogForward.enable");
        ConsoleLogForward.filter.enable = getBoolean("ConsoleLogForward.filter.enable");
        ConsoleLogForward.filter.list = getStringList("ConsoleLogForward.filter.list");
        ChatEvent = getBoolean("ChatEvent");
        PlayerLoginEvent = getBoolean("PlayerLoginEvent");
        PlayerLogoutEvent = getBoolean("PlayerLogoutEvent");
        PlayerCommandEvent = getBoolean("PlayerCommandEvent");
        RconCommandEvent = getBoolean("RconCommandEvent");
    }
}
