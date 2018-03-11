package org.sphinx;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

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

        Stage stage = Controller.toStage(new FXMLBundle(menuURL));
        stage.show();
    }

    public void loadProperties() throws IOException {
        if (Files.exists(propertiesPath)) {
            properties.load(Files.newInputStream(propertiesPath));
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
