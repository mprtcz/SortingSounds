package com.mprtcz.sortingSounds;

import com.mprtcz.sortingSounds.MyLogger.MyLogger;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Azet on 2016-03-31.
 */
public class Controller {
    private final static Logger logger = Logger.getLogger(MyLogger.class.getName());
    Level level = Level.CONFIG;

    public Canvas canvas;
    public Slider speedSlider;
    public Button startButton;
    public Label label;
    public ChoiceBox<Integer> sizeChooser;
    public ChoiceBox<Sorter.SortType> sortingChoiceBox;
    public BorderPane borderPane;
    public Label callsLabel;
    public CheckBox playSoundCheckbox;

    private int size = 10;
    private Integer[] array = new Integer[size];
    private RectangleDrawer rectangleDrawer;
    private Integer[] choiceArray = {10, 30, 50, 100, 150};
    private boolean isSorted = false;
    private int sleepingTime;
    private MyComparator myComparator;


    public void onStartButtonCLicked() {
        logger.log(level, "");
        startButton.setDisable(true);
        sizeChooser.setDisable(true);

        if (isSorted) {
            generateArrayAndDrawIt();
        }
        new Thread(this::sort).start();
        isSorted = true;
    }

    private void sort() {
        logger.log(level, "");
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLUE);

        myComparator.setPlaySound(playSoundCheckbox.isSelected());

        Sorter sorter = Sorter.getInstance(sortingChoiceBox.getValue());
        sorter.sort(array, myComparator);

        startButton.setDisable(false);
        sizeChooser.setDisable(false);
    }

    public void initialize() {
        logger.log(level, "");
        setUpGuiElements();
        generateArrayAndDrawIt();
        setUpListeners();
    }

    private void setUpListeners() {
        logger.log(level, "");
        sizeChooser.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            size = choiceArray[(int) newValue];
            generateArrayAndDrawIt();
        });

        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            sleepingTime = (int) (speedSlider.getValue());
            myComparator.setSleepingTime(sleepingTime);
        });
    }

    private void setUpGuiElements() {
        logger.log(level, "");
        if (sizeChooser.getItems().size() == 0) {
            sizeChooser.getItems().addAll(choiceArray);
        }
        if (sizeChooser.getValue() != null) {
            this.size = sizeChooser.getValue();
        }
        this.sleepingTime = (int) speedSlider.getValue();
    }

    private void generateArrayAndDrawIt() {
        logger.log(level, "");
        array = RandomArrayGenerator.generateRandomNumbersArray(size);
        isSorted = false;

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        rectangleDrawer = new RectangleDrawer(array);
        rectangleDrawer.setCanvasHeightAndWidth(canvas.getHeight(), canvas.getWidth());

        graphicsContext.setFill(Color.BLACK);

        myComparator = new MyComparator(graphicsContext, rectangleDrawer);
        myComparator.setSleepingTime(sleepingTime);
        myComparator.setArray(array);
        myComparator.setLabel(callsLabel);

        if (sortingChoiceBox.getItems().size() == 0) {
            this.sortingChoiceBox.getItems().addAll(Sorter.SortType.BUBBLE_SORT,
                    Sorter.SortType.QUICKSORT, Sorter.SortType.ARRAYSORT);
            this.sortingChoiceBox.getSelectionModel().select(Sorter.SortType.BUBBLE_SORT);
        }

        Platform.runLater(() -> rectangleDrawer.drawArrayWithComparedRectangles(graphicsContext));
    }
}