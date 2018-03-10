package org.sphinx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/10/2018
 */
public class Main extends Application {
    private final URL menuURL = getClass().getResource("/Menu.fxml");

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
