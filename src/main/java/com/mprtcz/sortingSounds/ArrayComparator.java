package com.mprtcz.sortingSounds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Azet on 2016-04-16.
 */
class ArrayComparator {

    private Integer[] firstArray = null;

    List<Integer> getChangedIndexes(Integer[] secondArray) {
        List<Integer> changedIndexes = new ArrayList<>();
        if (firstArray != null) {
            if (firstArray.length == secondArray.length) {
                for (int i = 0; i < firstArray.length; i++) {
                    if (!Objects.equals(firstArray[i], secondArray[i])) {
                        changedIndexes.add(i);
                    }
                }
            }
        }
        firstArray = Arrays.copyOf(secondArray, secondArray.length);

        return changedIndexes;
    }
}
