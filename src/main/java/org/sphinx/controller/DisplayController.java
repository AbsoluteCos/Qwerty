package org.sphinx.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.sphinx.FXMLBundle;
import org.sphinx.Lesson;
import org.sphinx.course.Course;
import org.sphinx.course.CourseFactory;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

import static org.sphinx.Main.instance;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/10/2018
 */
public class DisplayController extends Controller implements Initializable {
    private final HashMap<String, Course> courseHashMap = new HashMap<>();

    @FXML
    private AnchorPane graph;

    @FXML
    private ChoiceBox<String> courseOptions;
    private URL lessonBoxURL = getClass().getResource("/LessonBox.fxml");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courseOptions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Course course = courseHashMap.get(newValue);
            try {
                loadCourse(course);
            } catch (IOException e) {
                Main.instance.getConsole().log(Level.WARNING, e);
            }
        });
    }

    private void loadCourse(Course course) throws IOException {
        List<Lesson> lessons = course.getLessons();
        for (Lesson lesson : lessons) {
            int height = lesson.getHeight();
            int index = lesson.getIndex();

            FXMLLoader loader = new FXMLLoader(lessonBoxURL);
            Parent parent = loader.load();
            LessonBox box = loader.getController();
            box.setLesson(lesson);

            graph.getChildren().add(parent);
            parent.setTranslateX(100 + 100 * index);
            parent.setTranslateY(graph.getPrefWidth() / (height + 1));
        }
    }

    @FXML
    public void close() {
        Platform.exit();
    }

    @FXML
    public void addCourse(){
        DirectoryChooser chooser = new DirectoryChooser();
        File file = chooser.showDialog(new Stage());
        if (file != null) {
            Path directory = file.toPath();

            try {
                Course course = CourseFactory.load(directory);
                courseOptions.getItems().add(course.getName());

                courseHashMap.put(course.getName(), course);
            } catch (IOException | SAXException e) {
                instance.getConsole().log(Level.WARNING, e);
            }
        } else {
            instance.getConsole().log(Level.WARNING, "No file was selected.");
        }
    }

    private final URL settingsURL = getClass().getResource("/Settings.fxml");

    @FXML
    public void openSettings(){
        try {
            Stage stage = Controller.toStage(new FXMLBundle(settingsURL));
            stage.show();
        } catch (IOException e) {
            instance.getConsole().log(Level.WARNING, e);
        }
    }

    @FXML
    public void clearCourse(){
        courseHashMap.clear();

        courseOptions.getItems().clear();
    }

    @FXML
    public void removeCourse(){
        courseOptions.getItems().remove(courseOptions.getSelectionModel().getSelectedItem());
    }
}
