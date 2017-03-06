package com.mprtcz.sortingSounds;

import com.mprtcz.sortingSounds.MyLogger.MyLogger;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Azet on 2016-04-01.
 */
class RectangleDrawer {
    private final static Logger logger = Logger.getLogger(MyLogger.class.getName());
    Level level = Level.CONFIG;

    private double xPosition;
    private double yPosition;
    private double width;
    private double height;
    private Integer[] array;
    private GraphicsContext graphicsContext;
    private Canvas canvas;

    RectangleDrawer(Integer[] array, Canvas canvas) {
        logger.log(level, "");
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.array = array;
        calculateCanvasHeightAndWidth();
    }

    private void calculateCanvasHeightAndWidth() {
        logger.log(level, "");
        this.height = canvas.getHeight() / array.length;
        this.width = canvas.getWidth() / array.length;
        this.yPosition = array.length * height;
        this.xPosition = width;
    }

    void drawArray() {
        logger.log(level, "");
        clearGraphicsContext();
        for (int i = 0; i < this.array.length; i++) {
            drawOneColumn(i);
        }
    }

    void drawArrayWithComparedRectangles(int index, int secondIndex) {
        logger.log(level, "");
        clearGraphicsContext();
        for (int i = 0; i < this.array.length; i++) {
            if (i == secondIndex) {
                graphicsContext.setFill(Color.BLUE);
            } else if (i == index) {
                graphicsContext.setFill(Color.BLUE);
            } else {
                graphicsContext.setFill(Color.BLACK);
            }
            drawOneColumn(i);
        }
    }

    void markSwappedRectangles(List<Integer> indexList) {
        logger.log(level, "");
        clearGraphicsContext();
        for (int i = 0; i < this.array.length; i++) {
            if (indexList.contains(i)) {
                graphicsContext.setFill(Color.GREEN);
            } else {
                graphicsContext.setFill(Color.BLACK);
            }
            drawOneColumn(i);
        }
    }

    private void clearGraphicsContext() {
        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
    }

    private void drawOneColumn(int i) {
        graphicsContext.fillRect(xPosition * i, yPosition - array[i] * height, width, height * array[i]);
    }
}
