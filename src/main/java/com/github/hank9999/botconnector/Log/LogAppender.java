package com.github.hank9999.botconnector.Log;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;

import java.util.Calendar;
import java.util.Date;

public class LogAppender extends AbstractAppender {
    public LogAppender() {
        super("com.github.hank9999.botconnector.BotConnector", null, null);
        start();
    }
    public void append(LogEvent event) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(event.getTimeMillis()));

        LogCollector.send(
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                c.get(Calendar.SECOND),
                event.getThreadName(),
                event.getLevel().toString(),
                event.getMessage().getFormattedMessage()
        );
    }
}
