package org.sphinx;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.sphinx.controller.Controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/10/2018
 */
public class Main extends Application {
    private final URL menuURL = getClass().getResource("/Menu.fxml");
    public static Main instance;
    private final Console console = new Console();
    private Path propertiesPath = Paths.get(".\\.properties");
    private Properties properties = new Properties();

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;

        loadProperties();

        FXMLBundle bundle = new FXMLBundle(menuURL);
        Scene scene = Controller.toScene(bundle);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Rectangle2D bounds = Screen.getPrimary().getBounds();
        AnchorPane pane = (AnchorPane) bundle.getParent();
        pane.setPrefSize(bounds.getWidth() / 2, bounds.getHeight() / 2);
    }

    private void loadProperties() throws IOException {
        if (Files.exists(propertiesPath)) {
            properties.load(Files.newInputStream(propertiesPath));
        }
        else{
            Files.createFile(propertiesPath);
        }
    }

    @Override
    public void stop(){
        try {
            properties.store(Files.newOutputStream(propertiesPath), "");
        } catch (IOException e) {
            console.log(Level.WARNING, e);
        }
    }

    public Console getConsole() {
        return console;
    }

    public Properties getProperties() {
        return properties;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
