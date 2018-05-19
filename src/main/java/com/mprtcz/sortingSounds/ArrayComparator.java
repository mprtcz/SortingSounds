package com.mprtcz.sortingSounds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Azet on 2016-04-16.
 */
class ArrayComparator {

    private Integer[] previousArray = null;

    List<Integer> getChangedIndexes(Integer[] changedArray) {
        List<Integer> changedIndexes = new ArrayList<>();
        if (validateArrays(previousArray, changedArray)) {
            changedIndexes = IntStream.range(0, previousArray.length)
                    .filter(index -> !Objects.equals(previousArray[index], changedArray[index]))
                    .boxed().collect(Collectors.toList());
        }
        previousArray = Arrays.copyOf(changedArray, changedArray.length);

        return changedIndexes;
    }

    private boolean validateArrays(Integer[] previousArray, Integer[] changedArray) {
        return previousArray != null && (previousArray.length == changedArray.length);
    }
}
