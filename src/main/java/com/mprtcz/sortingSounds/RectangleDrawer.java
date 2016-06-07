package com.mprtcz.sortingSounds;

import com.mprtcz.sortingSounds.MyLogger.MyLogger;
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
    private int amount;
    private Integer[] array;

    RectangleDrawer(Integer[] array) {
        logger.log(level, "");
        this.array = array;
        this.amount = array.length;
        int greatestNumber = array[0];

        for (int i : array) {
            if (i > greatestNumber)
                greatestNumber = i;
        }
    }

    void setCanvasHeightAndWidth(double canvasHeight, double canvasWidth) {
        logger.log(level, "");
        this.height = canvasHeight / array.length;
        this.width = canvasWidth / array.length;
        this.yPosition = array.length * height;
        this.xPosition = width;
    }

    void drawArrayWithComparedRectangles(GraphicsContext graphicsContext) {
        logger.log(level, "");
        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
        for (int i = 0; i < amount; i++) {
            graphicsContext.fillRect(xPosition * i, yPosition - array[i] * height, width, height * array[i]);
        }
    }

    void drawArrayWithComparedRectangles(GraphicsContext graphicsContext, int index, int secondIndex) {
        logger.log(level, "");
        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
        for (int i = 0; i < amount; i++) {
            if (i == secondIndex) {
                graphicsContext.setFill(Color.BLUE);
            } else if (i == index) {
                graphicsContext.setFill(Color.BLUE);
            } else {
                graphicsContext.setFill(Color.BLACK);
            }
            graphicsContext.fillRect(xPosition * i, yPosition - array[i] * height, width, height * array[i]);
        }
    }

    void markSwappedRectangles(GraphicsContext graphicsContext, List<Integer> indexList) {
        logger.log(level, "");
        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
        for (int i = 0; i < amount; i++) {
            if (indexList.contains(i)) {
                graphicsContext.setFill(Color.GREEN);
            } else {
                graphicsContext.setFill(Color.BLACK);
            }
            graphicsContext.fillRect(xPosition * i, yPosition - array[i] * height, width, height * array[i]);
        }
    }
}
