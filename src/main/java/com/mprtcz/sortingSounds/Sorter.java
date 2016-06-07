package com.mprtcz.sortingSounds;

import com.mprtcz.sortingSounds.MyLogger.MyLogger;

import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Azet on 2016-04-02.
 */
interface Sorter {
    void sort(Integer[] array, Comparator<Integer> comparator);

    enum SortType {
        BUBBLE_SORT("Bubble Sort"),
        QUICKSORT("Quicksort"),
        ARRAYSORT("Array Sort");

        private String value;

        SortType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    static Sorter getInstance(SortType sortType) {

        switch (sortType) {
            case BUBBLE_SORT:
                return new BubbleSorter();
            case QUICKSORT:
                return new QuickSorter();
            case ARRAYSORT:
                return new ArraySorter();
            default:
                return new BubbleSorter();
        }
    }
}

class BubbleSorter implements Sorter {
    private final static Logger logger = Logger.getLogger(MyLogger.class.getName());
    Level level = Level.CONFIG;

    @Override
    public void sort(Integer[] array, Comparator<Integer> comparator) {
        logger.log(level, "Starting bubblesort");
        boolean changed = false;
        do {
            changed = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (comparator.compare(array[i], array[i + 1]) > 0) {
                    int carrier = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = carrier;
                    changed = true;
                }
            }
        } while (changed);
    }
}

class QuickSorter implements Sorter {
    private final static Logger logger = Logger.getLogger(MyLogger.class.getName());
    Level level = Level.CONFIG;

    @Override
    public void sort(Integer[] array, Comparator<Integer> comparator) {
        logger.log(level, "Starting quicksort");
        quicksort(array, 0, array.length - 1, comparator);
    }

    private static void quicksort(Integer table[], int x, int y, Comparator<Integer> comparator) {
        int i, j, v, temp;

        i = x;
        j = y;
        v = table[(x + y) / 2];
        do {
            while (comparator.compare(table[i], v) < 0) {
                i++;
            }
            while (comparator.compare(table[j], v) > 0) {
                j--;
            }
            if (i <= j) {
                temp = table[i];
                table[i] = table[j];
                table[j] = temp;
                i++;
                j--;
            }
        }
        while (i <= j);
        if (x < j)
            quicksort(table, x, j, comparator);
        if (i < y)
            quicksort(table, i, y, comparator);

        comparator.compare(table[table.length - 1], table[table.length - 2]);
    }
}

class ArraySorter implements Sorter {
    private final static Logger logger = Logger.getLogger(MyLogger.class.getName());
    Level level = Level.CONFIG;

    @Override
    public void sort(Integer[] array, Comparator<Integer> comparator) {
        logger.log(level, "Starting ArraySort");
        Arrays.sort(array, comparator);
        comparator.compare(array.length - 1, array.length - 2);
    }
}

