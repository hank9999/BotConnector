package com.github.hank9999.botconnector.Libs;

import java.net.URLEncoder;

public class URL {
    public static String Encoder(String text) {
        try {
            return URLEncoder.encode(text, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
