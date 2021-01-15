package com.github.hank9999.botconnector.Libs;

import com.github.hank9999.botconnector.BotConnector;

import java.util.List;

public class Config {

    public static String url = "ws://example.com";
    public static String name = "server";
    public static String token = "ffffffff-ffff-ffff-ffff-ffffffffffff";
    public static Boolean ConsoleLogForward = false;
    public static Boolean ChatEvent = false;
    public static Boolean PlayerLoginEvent = false;
    public static Boolean PlayerLogoutEvent = false;
    public static Boolean PlayerCommandEvent = false;
    public static Boolean RconCommandEvent = false;

    public static void loadConfig() {
        BotConnector.plugin.saveDefaultConfig();
        BotConnector.plugin.reloadConfig();

        setValue();
    }

    public static void reloadConfig() {
        BotConnector.plugin.saveDefaultConfig();
        BotConnector.plugin.reloadConfig();

        setValue();
    }

    public static void saveConfig() {
        BotConnector.plugin.saveConfig();
        reloadConfig();
    }

    public static void setConfig(String path, Object value) {
        BotConnector.plugin.getConfig().set(path, value);
    }

    private static String getString(String path) {
        return BotConnector.plugin.getConfig().getString(path);
    }

    private static List<String> getStringList(String path) {
        return BotConnector.plugin.getConfig().getStringList(path);
    }

    private static int getInt(String path) {
        return BotConnector.plugin.getConfig().getInt(path);
    }

    private static long getLong(String path) {
        return BotConnector.plugin.getConfig().getLong(path);
    }

    private static Boolean getBoolean(String path) {
        return BotConnector.plugin.getConfig().getBoolean(path);
    }

    public static void setValue() {
        url = getString("url");
        name = getString("name");
        token = getString("token");
        ConsoleLogForward = getBoolean("ConsoleLogForward");
        ChatEvent = getBoolean("ChatEvent");
        PlayerLoginEvent = getBoolean("PlayerLoginEvent");
        PlayerLogoutEvent = getBoolean("PlayerLogoutEvent");
        PlayerCommandEvent = getBoolean("PlayerCommandEvent");
        RconCommandEvent = getBoolean("RconCommandEvent");
    }
}
