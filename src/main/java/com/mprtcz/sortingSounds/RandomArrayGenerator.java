package com.mprtcz.sortingSounds;

import com.mprtcz.sortingSounds.MyLogger.MyLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Azet on 2016-04-01.
 */
class RandomArrayGenerator {
    private final static Logger logger = Logger.getLogger(MyLogger.class.getName());
    static Level level = Level.CONFIG;

    static Integer[] generateRandomNumbersArray(int size) {

        logger.log(level, "");
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i < size + 1; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return fromListToArray(list);
    }

    static Integer[] fromListToArray(List<Integer> list) {
        logger.log(level, "");
        Integer[] array = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
