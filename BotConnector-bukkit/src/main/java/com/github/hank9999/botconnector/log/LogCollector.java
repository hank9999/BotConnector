package com.github.hank9999.botconnector.log;

import com.github.hank9999.botconnector.events.bukkit.ConsoleLogForward;
import com.github.hank9999.botconnector.libs.Config;
import org.bukkit.ChatColor;


public class LogCollector {
    public static final StringBuffer buffer = new StringBuffer();
    public static long time = 0;

    public static void add(int hour, int minute, int second, String ThreadName, String Level, String originMessage) {
        String Message = ChatColor.stripColor(originMessage);
        String str = "[" + hour + ":" + minute + ":" + second + "] " + "[" + ThreadName + "/" + Level + "]: " + Message;
        if (Config.ConsoleLogForward.filter.enable) {
            String str_lowCase = str.toLowerCase();
            for (String text : Config.ConsoleLogForward.filter.list) {
                if (str_lowCase.contains(text.toLowerCase())) {
                    return;
                }
            }
        }
        buffer.append(str);
        buffer.append("\n");
    }

    public static void check() {
        if (buffer.length() == 0) {
            time = System.currentTimeMillis();
            return;
        }
        if (System.currentTimeMillis() - time > 500) {
            send();
        }
    }

    public static void send() {
        String message = buffer.toString();
        buffer.setLength(0);
        time = System.currentTimeMillis();
        ConsoleLogForward.send(message);
    }
}
