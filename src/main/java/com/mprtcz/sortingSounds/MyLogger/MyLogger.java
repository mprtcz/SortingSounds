package com.mprtcz.sortingSounds.MyLogger;

import java.io.IOException;
import java.util.logging.*;

/**
 * Created by Azet on 2016-04-28.
 */
public class MyLogger {
    private final static Logger logger = Logger.getLogger(MyLogger.class.getName());
    private static Handler handler = null;

    public static void initializeLogger() {
        try {
            handler = new FileHandler("log.log", false);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not create file");
            e.printStackTrace();
        }
        Logger newLogger = Logger.getLogger("");
        handler.setFormatter(new SimpleFormatter());
        newLogger.addHandler(handler);
        newLogger.setLevel(Level.CONFIG);
    }
}
