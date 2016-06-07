package com.mprtcz.sortingSounds.TestLogger;

import java.io.IOException;
import java.util.logging.*;

/**
 * Created by Azet on 2016-04-30.
 */
public class TestsLogger {
    private final static Logger logger = Logger.getLogger(TestsLogger.class.getName());
    private static Handler handler = null;

    public static void initializeLogger() {
        try {
            handler = new FileHandler("TestsLog.log", false);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not create file");
            e.printStackTrace();
        }
        Logger newLogger = Logger.getLogger("");
        handler.setFormatter(new SimpleFormatter());
        newLogger.addHandler(handler);
        newLogger.setLevel(Level.FINE);
    }
}
