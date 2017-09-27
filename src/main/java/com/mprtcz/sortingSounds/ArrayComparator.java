package com.mprtcz.sortingSounds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Azet on 2016-04-16.
 */
class ArrayComparator {

    private Integer[] previousArray = null;

    List<Integer> getChangedIndexes(Integer[] changedArray) {
        List<Integer> changedIndexes = new ArrayList<>();
        if (validateArrays(previousArray, changedArray)) {
            for (int i = 0; i < previousArray.length; i++) {
                if (!Objects.equals(previousArray[i], changedArray[i])) {
                    changedIndexes.add(i);
                }
            }
        }
        previousArray = Arrays.copyOf(changedArray, changedArray.length);

        return changedIndexes;
    }

    private boolean validateArrays(Integer[] previousArray, Integer[] changedArray) {
        return previousArray != null && (previousArray.length == changedArray.length);
    }
}
