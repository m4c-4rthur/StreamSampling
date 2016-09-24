/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.researchgate.streamsampling.utils;

import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author hesham.ibrahim
 * Initializes and manages logging formats and Methods.
 */
public class LogUtils {

    private static Logger logger;

    public static void initialize(String log4jFile) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(log4jFile);
        if (url != null) {
            PropertyConfigurator.configure(url);
        } else {
            PropertyConfigurator.configure("log4j.properties");
        }

        logger = Logger.getRootLogger();

        info("***************** Loggers Initialized *********************");

    }

    public static Logger getLoggerInstance() {
        if (logger != null) {
            return logger;
        } else {
            initialize(null);
            return logger;
        }
    }

    private static StackTraceElement getStackTraceOfCaller() {
        StackTraceElement st[] = Thread.currentThread().getStackTrace();
        return st[3];
    }

    public static void debug(String msg) {

        StackTraceElement caller = getStackTraceOfCaller();
        try {
            logger.debug("[" + Thread.currentThread().getName() + "]" + ((Class) Class.forName(caller.getClassName())).getSimpleName() + "." + caller.getMethodName() + "(): " + msg);
        } catch (ClassNotFoundException ex) {
            logger.error(caller.getClassName() + " | " + caller.getMethodName() + "() |  " + msg);
        }
    }

    public static void info(String msg) {

        StackTraceElement caller = getStackTraceOfCaller();
        try {
            logger.info("[" + Thread.currentThread().getName() + "]" + ((Class) Class.forName(caller.getClassName())).getSimpleName() + "." + caller.getMethodName() + "(): " + msg);
        } catch (ClassNotFoundException ex) {
            logger.error(caller.getClassName() + "." + caller.getMethodName() + "() | " + msg);
        }
    }

    public static void error(String msg) {

        StackTraceElement caller = getStackTraceOfCaller();

        try {
            logger.debug("Exception in " + "[" + Thread.currentThread().getName() + "]" + ((Class) Class.forName(caller.getClassName())).getSimpleName() + "." + caller.getMethodName()
                    + "(): " + msg + " =>");

            logger.error("[" + Thread.currentThread().getName() + "]" + ((Class) Class.forName(caller.getClassName())).getSimpleName() + "." + caller.getMethodName() + "() error=>");
        } catch (ClassNotFoundException ex) {
            logger.debug(caller.getClassName() + " | " + caller.getMethodName() + "() | " + msg);
        }
    }

    public static void error(String msg, Throwable th) {

        StackTraceElement caller = getStackTraceOfCaller();

        try {
            logger.debug("Exception in " + "[" + Thread.currentThread().getName() + "]" + ((Class) Class.forName(caller.getClassName())).getSimpleName() + "." + caller.getMethodName()
                    + "(): " + msg + " | " + "EMsg[" + th.getMessage() + "] =>");

            logger.error("[" + Thread.currentThread().getName() + "]" + ((Class) Class.forName(caller.getClassName())).getSimpleName() + "."
                    + caller.getMethodName() + "() error=>", th);
        } catch (ClassNotFoundException ex) {
            logger.debug(caller.getClassName() + " | " + caller.getMethodName() + "() | " + msg + " | " + "EMsg["
                    + th.getMessage() + "] =>", th);
        }
    }

}
