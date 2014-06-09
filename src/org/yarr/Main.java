package org.yarr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.SimpleLogger;

public class Main {
    private static Logger log;
    private static Sessions ss = new Sessions();

    public static void main(String[] args) {
        System.setProperty(SimpleLogger.SHOW_DATE_TIME_KEY,"true");
        System.setProperty(SimpleLogger.WARN_LEVEL_STRING_KEY,"!!!");
        System.setProperty(SimpleLogger.DATE_TIME_FORMAT_KEY, "yyyy-MM-dd HH:mm:ss:SSS");
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "debug");

        log = LoggerFactory.getLogger(Main.class);
        log.debug("Creating server");
    }
}
