package com.github.hank9999.botconnector.Events.bukkit;

import com.github.hank9999.botconnector.Libs.Json;
import com.github.hank9999.botconnector.Utils.WebSocket;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConsoleLogForward {
    static public void send(String str) {
        Map<String, String> map = new LinkedHashMap<String, String>() {
            {
                put("type", "log");
                put("log", str);
            }
        };
        map = Collections.unmodifiableMap(map);
        WebSocket.sendMessage(Json.Serialization(map));
    }
}
