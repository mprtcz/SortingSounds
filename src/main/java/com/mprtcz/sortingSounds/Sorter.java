package com.mprtcz.sortingSounds;

import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Logger;

import static com.mprtcz.sortingSounds.RandomArrayGenerator.level;

/**
 * Created by Azet on 2016-04-02.
 */
public enum Sorter {

    BUBBLE_SORT("Bubble Sort") {
        @Override
        public void sort(Integer[] array, Comparator<Integer> comparator) {
            Logger logger = Logger.getLogger(Sorter.class.getName());
            logger.log(level, "Starting bubblesort");
            boolean changed;
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
    },
    QUICK_SORT("Quicksort") {
        @Override
        public void sort(Integer[] array, Comparator<Integer> comparator) {
            Logger logger = Logger.getLogger(Sorter.class.getName());
            logger.log(level, "Starting quicksort");
            quicksort(array, 0, array.length - 1, comparator);
        }

        private void quicksort(Integer table[], int x, int y, Comparator<Integer> comparator) {
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
    },
    INSERTION_SORT("InsertionSort") {
        @Override
        public void sort(Integer[] array, Comparator<Integer> comparator) {
            for (int i = 1; i < array.length; i++) {
                int index = array[i];
                int j = i;
                while (j > 0 && comparator.compare(array[j - 1], index) > 0) {
                    array[j] = array[j - 1];
                    j--;
                }
                array[j] = index;
            }
            comparator.compare(array[array.length - 1], array[array.length - 2]);
        }
    },
    ARRAY_SORT("Dual-Pivot Quicksort") {
        @Override
        public void sort(Integer[] array, Comparator<Integer> comparator) {
            Logger logger = Logger.getLogger(Sorter.class.getName());
            logger.log(level, "Starting Dual-Pivot Quicksort");
            Arrays.sort(array, comparator);
            comparator.compare(array.length - 1, array.length - 2);
        }
    },
    SELECTION_SORT("Selection Sort") {
        @Override
        public void sort(Integer[] array, Comparator<Integer> comparator) {
            Logger logger = Logger.getLogger(Sorter.class.getName());
            logger.log(level, "Starting Selection Sort");
            for (int i = 0; i < array.length - 1; i++) {
                int min = i;
                for (int j = i + 1; j < array.length; j++)
                    if (comparator.compare(array[j], array[min]) < 0) min = j;
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
    };

    private String value;

    Sorter(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    abstract void sort(Integer[] array, Comparator<Integer> comparator);
}

