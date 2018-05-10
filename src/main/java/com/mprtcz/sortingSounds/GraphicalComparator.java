package com.mprtcz.sortingSounds;

import com.mprtcz.sortingSounds.MyLogger.MyLogger;
import com.mprtcz.sortingSounds.Sounds.SoundBeeper;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.signum;

/**
 * Created by Azet on 2016-04-02.
 */
class GraphicalComparator implements Comparator<Integer> {
    private final static Logger logger = Logger.getLogger(MyLogger.class.getName());
    private Level level = Level.CONFIG;

    private RectangleDrawer rectangleDrawer;
    private int sleepingTime;
    private Integer[] array;
    private int compareCalls = 0;
    private Label label;
    private ArrayComparator arrayComparator;
    private SoundBeeper firstBeeper = new SoundBeeper();
    private SoundBeeper secondBeeper = new SoundBeeper();
    private boolean playSound = false;


    @Override
    public int compare(Integer firstNumberToCompare, Integer secondNumberToCompare) {
        logger.log(level, "first number to compare: " + firstNumberToCompare +
                " second number: " + secondNumberToCompare);
        markSwappedRectangles();
        ComparedIndexes comparedIndexes = findIndexesOfComparedRectangles(firstNumberToCompare, secondNumberToCompare);
        markComparedRectangles(comparedIndexes.firstIndex, comparedIndexes.secondIndex);

        this.compareCalls++;
        updateLabel();
        return signum(firstNumberToCompare - secondNumberToCompare);
    }

    GraphicalComparator(RectangleDrawer rectangleDrawer) {
        logger.log(level, "");
        this.rectangleDrawer = rectangleDrawer;
        this.compareCalls = 0;
        this.arrayComparator = new ArrayComparator();
    }

    private void markComparedRectangles(int firstIndex, int secondIndex) {
        logger.log(level, "Starting new thread to draw compared rectangles");
        Platform.runLater(() -> rectangleDrawer.drawArrayWithComparedRectangles(firstIndex, secondIndex));
        try {
            logger.log(level, "playSound: " + playSound);
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

    private void markSwappedRectangles() {
        logger.log(level, "");
        List<Integer> indexList = arrayComparator.getChangedIndexes(array);
        Platform.runLater(() -> rectangleDrawer.markSwappedRectangles(indexList));
        putThreadToSleep();
    }

    private void putThreadToSleep() {
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

    private ComparedIndexes findIndexesOfComparedRectangles(Integer firstNumberToCompare, Integer secondNumberToCompare) {
        ComparedIndexes comparedIndexes = new ComparedIndexes();
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], firstNumberToCompare)) {
                comparedIndexes.firstIndex = i;
            } else if (Objects.equals(array[i], secondNumberToCompare)) {
                comparedIndexes.secondIndex = i;
            }
            if(comparedIndexes.hasBothValues()) {
                break;
            }
        }
        return comparedIndexes;
    }

    void setLabel(Label label) {
        logger.log(level, "");
        this.label = label;
    }

    private void updateLabel() {
        logger.log(level, "Updating calls label with number " + compareCalls);
        Platform.runLater(() -> label.setText("Comparator calls: " + compareCalls));
    }

    void setPlaySound(boolean playSound) {
        logger.log(level, "Setting playSound true");
        this.playSound = playSound;
    }

    private void playSound(int firstIndex, int secondIndex) {
        firstBeeper = new SoundBeeper();
        secondBeeper = new SoundBeeper();
        firstBeeper.setUpSound(firstIndex + 10);
        firstBeeper.loopSound(true);
        secondBeeper.setUpSound(secondIndex + 10);
        secondBeeper.loopSound(true);
    }

    private void stopSound() {
        firstBeeper.loopSound(false);
        secondBeeper.loopSound(false);
    }

    private class ComparedIndexes {
        private int firstIndex = -1;
        int secondIndex = -1;

        boolean hasBothValues() {
            return firstIndex != -1 && secondIndex != -1;
        }
    }
}