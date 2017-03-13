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
    private Color[] colorsPalette;
    private Integer[] array;
    private GraphicsContext graphicsContext;
    private Canvas canvas;

    RectangleDrawer(Integer[] array, Canvas canvas) {
        logger.log(level, "");
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.array = array;
        calculateCanvasHeightAndWidth();
        generateColorPalette();
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
            graphicsContext.setFill(this.colorsPalette[array[i] - 1]);
            drawOneColumn(i);
        }
    }

    void drawArrayWithComparedRectangles(int index, int secondIndex) {
        logger.log(level, "");
        clearGraphicsContext();
        for (int i = 0; i < this.array.length; i++) {
            if (i == secondIndex || i == index) {
                graphicsContext.setFill(Color.BLACK);
            } else {
                graphicsContext.setFill(this.colorsPalette[array[i] - 1]);
            }
            drawOneColumn(i);
        }
    }

    void markSwappedRectangles(List<Integer> indexList) {
        logger.log(level, "");
        clearGraphicsContext();
        for (int i = 0; i < this.array.length; i++) {
            if (indexList.contains(i)) {
                graphicsContext.setFill(Color.GRAY);
            } else {
                graphicsContext.setFill(this.colorsPalette[array[i] - 1]);
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

    private void generateColorPalette() {
        this.colorsPalette = new Color[array.length];
        for (int i = 0; i < array.length; i++) {
            colorsPalette[i] = Color.hsb(((float) i / (float) array.length) * 360, 0.85f, 1.0f);
        }
    }
}
