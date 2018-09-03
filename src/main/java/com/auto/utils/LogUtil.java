package com.auto.utils;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogUtil {

    private static LogUtil log4jUtil;
    private Logger logger;

    public LogUtil(String configPath) {
        PropertyConfigurator.configure(configPath);
        // this.logger = Logger.getRootLogger();
        this.logger = Logger.getLogger(LogUtil.class);
    }

    public static LogUtil getLog4jUtil() {
        if (log4jUtil == null) {
            log4jUtil = new LogUtil("log4j.properties");
        }
        return log4jUtil;
    }

    public static void debug(String str) {
        log4jUtil.logger.debug(str);
    }

    public static void info(String str) {
        log4jUtil.logger.info(str);
    }

    public static void warn(String str) {
        log4jUtil.logger.warn(str);
    }

    public static void error(String str) {
        log4jUtil.logger.error(str);
    }

    public static void fatal(String str) {
        log4jUtil.logger.fatal(str);
    }
}
