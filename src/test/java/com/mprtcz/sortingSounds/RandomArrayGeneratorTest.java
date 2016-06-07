package com.mprtcz.sortingSounds;

import com.mprtcz.sortingSounds.TestLogger.TestsLogger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Created by Azet on 2016-04-30.
 */
public class RandomArrayGeneratorTest {
    private final static Logger logger = Logger.getLogger(TestsLogger.class.getName());
    static {
        TestsLogger.initializeLogger();
    }

    private final int[] SIZES = new int[]{10, 30, 50, 100, 150};
    private Level level = Level.FINE;

    @Test
    public void generateRandomArray_checksum() {

        int[] results = new int[]{55, 465, 1275, 5050, 11325};
        for (int i = 0; i < SIZES.length; i++) {
            int size = SIZES[i];
            logger.log(level, "Size: " +size);
            Integer[] array = RandomArrayGenerator.generateRandomNumbersArray(size);
            int sum = 0;
            for(Integer j : array){
                sum+=j;
            }
            logger.log(level, "Array sum: " +sum +" expected sum: " +results[i]);
            assertEquals(sum, results[i]);
        }
    }

    @Test
    public void generateRandomArray_IsShuffled(){

        for (int SIZE : SIZES) {
            logger.log(level, "Size: " + SIZE);
            boolean isSorted = true;
            Integer[] array = RandomArrayGenerator.generateRandomNumbersArray(SIZE);
            logger.log(level, "Array: " + Arrays.toString(array));
            for (int j = 0; j < array.length - 1; j++) {
                if (!(array[j] < array[j + 1])) {
                    isSorted = false;
                }
            }
            logger.log(level, "Is sorted: " + isSorted);
            assertFalse(isSorted);
        }
    }

    @Test
    public void fromListToArrayTest_positive(){
        for(int size : SIZES) {
            logger.log(level, "Size: " +size);
            List<Integer> intList = new ArrayList<>();
            Integer[] intArray = new Integer[size];

            for(int i=0; i<size;i++){
                intList.add(i);
                intArray[i] = i;
            }

            Integer[] resultArray = RandomArrayGenerator.fromListToArray(intList);

            boolean isNumberEqual = true;

            for(int j=0; j<resultArray.length;j++){
                if(!Objects.equals(resultArray[j], intArray[j])){
                    isNumberEqual = false;
                    logger.log(level, "Numbers not equal at index " +j);
                }
            }
            logger.log(level, "All numbers equal");
            assertTrue(isNumberEqual);
        }
    }

    @Test
    public void fromListToArrayTest_negative(){
        for(int size : SIZES) {
            logger.log(level, "Size: " +size);
            List<Integer> intList = new ArrayList<>();
            Integer[] intArray = new Integer[size];

            for(int i=0; i<size;i++){
                intList.add(i);
                intArray[i] = i-1;
            }

            Integer[] resultArray = RandomArrayGenerator.fromListToArray(intList);

            boolean isNumberEqual = false;

            for(int j=0; j<resultArray.length;j++){
                if(Objects.equals(resultArray[j], intArray[j])){
                    isNumberEqual = true;
                    logger.log(level, "Numbers equal at index " +j);
                }
            }
            logger.log(level, "All numbers not equal");
            assertFalse(isNumberEqual);
        }
    }
}
