package com.mprtcz.sortingSounds;

import com.mprtcz.sortingSounds.MyLogger.MyLogger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Azet on 2016-03-31.
 */
public class App extends javafx.application.Application {
    private final static Logger logger = Logger.getLogger(MyLogger.class.getName());
    Level level = Level.CONFIG;

    @Override
    public void start(Stage window) throws Exception{
        MyLogger.initializeLogger();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main1.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1024, 768);

        window.setOnCloseRequest(e -> {
            logger.log(Level.CONFIG, "Close Requested");
            Platform.exit();
            System.exit(0);
        });

        window.setTitle("Sorting Visualizer");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
