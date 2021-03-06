package org.sphinx;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.sphinx.controller.Controller;
import org.sphinx.course.Course;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;
import java.util.function.Consumer;
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
    private final Path propertiesPath = Paths.get(".\\.properties");
    private final Properties properties = new Properties();
    public static final HashMap<String, Course> courseHashMap = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;

        loadProperties();

        FXMLBundle bundle = new FXMLBundle(menuURL);
        Scene scene = Controller.toScene(bundle);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        ((Controller) bundle.getController()).setStage(stage);

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
            Collection<Course> values = courseHashMap.values();
            if (values.size() > 0) {
                StringBuilder builder = new StringBuilder();
                values.forEach(course -> {
                    builder.append(course.getLocation());
                    builder.append("\n");
                });
                properties.setProperty("loadedCourses", builder.toString().substring(0, builder.length() - 1));
            }
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
