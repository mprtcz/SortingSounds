package com.mprtcz.sortingSounds;

import com.mprtcz.sortingSounds.MyLogger.MyLogger;
import com.mprtcz.sortingSounds.Sounds.MyBeeper;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Azet on 2016-04-02.
 */
class MyComparator implements Comparator<Integer> {
    private final static Logger logger = Logger.getLogger(MyLogger.class.getName());
    private Level level = Level.CONFIG;

    private GraphicsContext graphicsContext;
    private RectangleDrawer rectangleDrawer;
    private int sleepingTime;
    private int firstIndex;
    private int secondIndex;
    private Integer[] array;
    private int classCalls = 0;
    private Label label;
    private ArrayComparator arrayComparator;
    private MyBeeper myBeeper = new MyBeeper();
    private MyBeeper myBeeper1 = new MyBeeper();
    private boolean playSound = false;


    @Override
    public int compare(Integer firstNumberToCompare, Integer secondNumberToCompare) {

        markGreenRectangles();
        logger.log(level, "first number to compare: " + firstNumberToCompare +
                " second number: " + secondNumberToCompare);

        findIndexesOfComparedRectangles(firstNumberToCompare, secondNumberToCompare);
        markComparedRectangles(firstIndex, secondIndex);

        this.classCalls++;
        updateLabel();

        if (firstNumberToCompare > secondNumberToCompare) {
            return 1;
        } else if (firstNumberToCompare < secondNumberToCompare) {
            return -1;
        } else {
            return 0;
        }
    }

    MyComparator(GraphicsContext graphicsContext, RectangleDrawer rectangleDrawer) {
        logger.log(level, "");
        this.graphicsContext = graphicsContext;
        this.rectangleDrawer = rectangleDrawer;
        this.classCalls = 0;
        this.arrayComparator = new ArrayComparator();
    }

    private void markComparedRectangles(int firstIndex, int secondIndex) {
        logger.log(level, "Starting new thread to draw compared rectangles");
        Platform.runLater(() -> rectangleDrawer.drawArrayWithComparedRectangles(graphicsContext, firstIndex, secondIndex));
        try {
            logger.log(level, "playSound: " +playSound);
            if (playSound) {
                logger.log(level, "Playing sound for indexes: " + firstIndex + " " + secondIndex);
                playSound(firstIndex, secondIndex);
            }
            logger.log(level, "Sleeping for " + sleepingTime + " ms");
            Thread.sleep(sleepingTime);
        } catch (InterruptedException | NullPointerException e) {
            logger.log(Level.WARNING, e.toString());
            e.printStackTrace();
        } finally {
            if (playSound) {
                logger.log(level, "Stopping sound for indexes: " + firstIndex + " " + secondIndex);
                stopSound();
            }
        }
    }

    private void markGreenRectangles() {
        logger.log(level, "");
        List<Integer> indexList = arrayComparator.getChangedIndexes(array);
        Platform.runLater(() -> rectangleDrawer.markSwappedRectangles(graphicsContext, indexList));
        try {
            logger.log(level, "Sleeping for " + sleepingTime + " ms");
            Thread.sleep(sleepingTime);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Interrupted Exception");
            e.printStackTrace();
        }
    }

    void setSleepingTime(int sleepingTime) {
        logger.log(level, "");
        this.sleepingTime = sleepingTime;
    }

    void setArray(Integer[] array) {
        logger.log(level, "");
        this.array = array;
    }

    private void findIndexesOfComparedRectangles(Integer firstNumberToCompare, Integer secondNumberToCompare) {
        logger.log(level, "");
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], firstNumberToCompare)) {
                firstIndex = i;
            } else if (Objects.equals(array[i], secondNumberToCompare)) {
                secondIndex = i;
            }
        }
    }

    void setLabel(Label label) {
        logger.log(level, "");
        this.label = label;
    }

    private void updateLabel() {
        logger.log(level, "Updating calls label with number " + classCalls);
        Platform.runLater(() -> label.setText("Comparator calls: " + classCalls));
    }

    void setPlaySound(boolean playSound) {
        System.out.println("Setting playSound true");
        this.playSound = playSound;
    }

    private void playSound(int firstIndex, int secondIndex) {
        myBeeper = new MyBeeper();
        myBeeper1 = new MyBeeper();
        myBeeper.setUpSound(firstIndex + 10);
        myBeeper.loopSound(true);
        myBeeper1.setUpSound(secondIndex + 10);
        myBeeper1.loopSound(true);
    }

    private void stopSound() {
        myBeeper.loopSound(false);
        myBeeper1.loopSound(false);
    }

}