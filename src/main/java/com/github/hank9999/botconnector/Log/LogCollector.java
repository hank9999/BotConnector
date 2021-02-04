package com.github.hank9999.botconnector.Log;

import com.github.hank9999.botconnector.Events.bukkit.ConsoleLogForward;
import com.github.hank9999.botconnector.Libs.Config;
import org.bukkit.ChatColor;

public class LogCollector {

    public static void send(int hour, int minute, int second, String ThreadName, String Level, String Message) {
        Message = ChatColor.stripColor(Message);
        if (Config.ConsoleLogForward.filter.enable) {
            for (String text : Config.ConsoleLogForward.filter.list) {
                if (Message.toLowerCase().contains(text.toLowerCase())) {
                    return;
                }
            }
        }
        String str = "[" + hour + ":" + minute + ":" + second + "] " + "[" + ThreadName + "/" + Level + "]: " + Message;
        ConsoleLogForward.send(str);
    }
}
