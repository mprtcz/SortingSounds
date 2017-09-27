package com.mprtcz.sortingSounds;

import com.mprtcz.sortingSounds.TestLogger.TestsLogger;
import com.sun.javafx.application.PlatformImpl;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

/**
 * Created by Azet on 2016-04-30.
 */
public class SorterTest {
    private final static Logger logger = Logger.getLogger(TestsLogger.class.getName());
    private Level level = Level.FINE;

    private Integer[] arrayToSort = new Integer[]{9,8,7,6,5,4,3,2,1,0};
    private List<Integer> arrayAsList = Arrays.asList(arrayToSort);
    private Canvas canvas = new Canvas();
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
    private RectangleDrawer rectangleDrawer = new RectangleDrawer(arrayToSort, canvas);
    private GraphicalComparator graphicalComparator = new GraphicalComparator(rectangleDrawer);


    static{
        TestsLogger.initializeLogger();
    }

    @Test
    public void bubbleSorterTest(){
        PlatformImpl.startup(() -> {}); //starting the FXApplication thread
        graphicalComparator.setArray(arrayToSort);
        graphicalComparator.setLabel(new Label());
        Sorter sorter = Sorter.BUBBLE_SORT;
        logger.log(level, "Sorter: " +sorter.getClass());
        logger.log(level, "Array to sort: " +arrayAsList.toString());
        sorter.sort(arrayToSort, graphicalComparator);
        boolean isSorted = true;
        for(int i = 0; i < arrayToSort.length-1; i++){
            if(!(arrayToSort[i] <= arrayToSort[i+1])){
                isSorted = false;
            }
        }
        logger.log(level, "Array after sort: " +Arrays.asList(arrayToSort).toString());
        assertTrue(isSorted);
    }

    @Test
    public void QuickSorterTest(){
        PlatformImpl.startup(() -> {}); //starting the FXApplication thread
        graphicalComparator.setArray(arrayToSort);
        graphicalComparator.setLabel(new Label());
        Sorter sorter = Sorter.QUICK_SORT;
        logger.log(level, "Array to sort: " +arrayAsList.toString());
        logger.log(level, "Sorter: " +sorter.getClass());
        sorter.sort(arrayToSort, graphicalComparator);
        boolean isSorted = true;
        for(int i = 0; i < arrayToSort.length-1; i++){
            if(!(arrayToSort[i] <= arrayToSort[i+1])){
                isSorted = false;
            }
        }
        logger.log(level, "Array after sort: " +Arrays.asList(arrayToSort).toString());
        assertTrue(isSorted);
    }

    @Test
    public void ArraySorterTest(){
        PlatformImpl.startup(() -> {}); //starting the FXApplication thread
        graphicalComparator.setArray(arrayToSort);
        graphicalComparator.setLabel(new Label());
        Sorter sorter = Sorter.ARRAY_SORT;
        logger.log(level, "Array to sort: " +arrayAsList.toString());
        logger.log(level, "Sorter: " +sorter.getClass());
        sorter.sort(arrayToSort, graphicalComparator);
        boolean isSorted = true;
        for(int i = 0; i < arrayToSort.length-1; i++){
            if(!(arrayToSort[i] <= arrayToSort[i+1])){
                isSorted = false;
            }
        }
        logger.log(level, "Array after sort: " +Arrays.asList(arrayToSort).toString());
        assertTrue(isSorted);
    }
}
