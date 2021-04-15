package com.github.hank9999.botconnector.log;

import com.github.hank9999.botconnector.BotConnectorBukkit;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class LogAppender extends AbstractAppender {

    public boolean oldLog4j2 = false;

    public LogAppender() {
        super("com.github.hank9999.botconnector.BotConnector", null, null);

        String version = BotConnectorBukkit.plugin.getServer().getVersion();
        for(String old : Arrays.asList("1.7", "1.8", "1.9", "1.10", "1.11")) {
            if (version.contains(old)) {
                oldLog4j2 = true;
                break;
            }
        }

        start();
    }

    public void append(LogEvent event) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(getTimeStamp(event)));

        LogCollector.add(
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                c.get(Calendar.SECOND),
                event.getThreadName(),
                event.getLevel().toString(),
                event.getMessage().getFormattedMessage()
        );
    }

    public long getTimeStamp(LogEvent event) {
        if (oldLog4j2) {
            try {
                Method method = event.getClass().getMethod("getMillis");
                return (long) method.invoke(event);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                return Calendar.getInstance().getTimeInMillis();
            }
        } else {
            return event.getTimeMillis();
        }
    }
}
