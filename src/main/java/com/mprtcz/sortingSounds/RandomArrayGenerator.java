package com.mprtcz.sortingSounds;

import com.mprtcz.sortingSounds.MyLogger.MyLogger;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Azet on 2016-04-01.
 */
class RandomArrayGenerator {
    private final static Logger logger = Logger.getLogger(MyLogger.class.getName());
    static Level level = Level.CONFIG;

    static Integer[] generateRandomNumbersArray(int size) {
        logger.log(level, "");
        List<Integer> orderedInts = IntStream.rangeClosed(1, size).boxed().collect(Collectors.toList());
        Collections.shuffle(orderedInts);
        return orderedInts.toArray(new Integer[orderedInts.size()]);
    }
}
