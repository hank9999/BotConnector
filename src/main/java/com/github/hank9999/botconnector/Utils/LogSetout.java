package com.github.hank9999.botconnector.Utils;

import com.github.hank9999.botconnector.Log.LogAppender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;

public class LogSetout {
    static Appender appender = new LogAppender();
    public static void append() {
        ((org.apache.logging.log4j.core.Logger)LogManager.getRootLogger()).addAppender(appender);
    }
    public static void remove() {
        ((org.apache.logging.log4j.core.Logger)LogManager.getRootLogger()).removeAppender(appender);
    }
}
