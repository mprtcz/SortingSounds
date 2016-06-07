package com.mprtcz.sortingSounds;

import com.mprtcz.sortingSounds.TestLogger.TestsLogger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

/**
 * Created by Azet on 2016-04-30.
 */
public class ArrayComparatorTest {
    private final static Logger logger = Logger.getLogger(TestsLogger.class.getName());
    private Level level = Level.FINE;

    private Integer[] firstArray = new Integer[]{0,1,2,3,4,5,6,7,8,9};
    private Integer[] changedFirstArray = new Integer[]{0,1,2,4,3,5,6,9,8,7};
    private Integer[] changedIndexesArray = new Integer[]{3,4,7,9};
    private List<Integer> changedIndexesList = new ArrayList<>(Arrays.asList(changedIndexesArray));

    static{
        TestsLogger.initializeLogger();
    }

    @Test
    public void getChangedIndexesTest_changed(){
        logger.log(level, "");
        boolean equals = true;
        ArrayComparator arrayComparator = new ArrayComparator();
        arrayComparator.getChangedIndexes(firstArray);
        List<Integer> changedIndexes = arrayComparator.getChangedIndexes(changedFirstArray);
        for(int i = 0; i < changedIndexes.size(); i++){
            if(!Objects.equals(changedIndexes.get(i), changedIndexesList.get(i))){
                equals = false;
            }
        }
        logger.log(level, "Changed indexes: " +changedIndexes.toString());
        assertTrue(equals);
    }

    @Test
    public void getChangedIndexesTest_notChanged(){
        logger.log(level, "");
        ArrayComparator arrayComparator = new ArrayComparator();
        arrayComparator.getChangedIndexes(firstArray);
        List<Integer> changedIndexes = arrayComparator.getChangedIndexes(firstArray);

        logger.log(level, "Changed indexes: " +changedIndexes.toString());
        assertTrue(changedIndexes.size() == 0);
    }
}
