package org.sphinx.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import org.sphinx.FXMLBundle;
import org.sphinx.Lesson;
import org.sphinx.Main;
import org.sphinx.course.Course;
import org.sphinx.course.CourseFactory;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;

import static org.sphinx.Main.courseHashMap;
import static org.sphinx.Main.instance;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/10/2018
 */
public class DisplayController extends Controller implements Initializable {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private AnchorPane graph;

    @FXML
    private ChoiceBox<String> courseOptions;
    private URL lessonBoxURL = getClass().getResource("/LessonBox.fxml");


    @FXML
    public void onScroll(ScrollEvent event)
    {
        Node zoomNode = new Group(scrollPane.getContent());
        double scaleValue = 0.7;
        double zoomFactor = Math.exp(event.getDeltaY()*.02);

        Bounds innerBounds = zoomNode.getLayoutBounds();
        Bounds viewportBounds = scrollPane.getViewportBounds();

        double valx = scrollPane.getHvalue() * (innerBounds.getWidth() - viewportBounds.getWidth());
        double valy = scrollPane.getVvalue() * (innerBounds.getHeight() - viewportBounds.getHeight());

        scaleValue = scaleValue * .02;
        scrollPane.setScaleX(scaleValue);
        scrollPane.setScaleY(scaleValue);
        scrollPane.layout();

        Point2D posInZoomTarget = scrollPane.parentToLocal(zoomNode.parentToLocal(new Point2D(event.getX(), event.getY())));

        Point2D adjustment = scrollPane.getLocalToParentTransform().deltaTransform(posInZoomTarget.multiply(zoomFactor - 1));

        Bounds updatedInnerBounds = zoomNode.getBoundsInLocal();
        scrollPane.setHvalue((valx + adjustment.getX())/(updatedInnerBounds.getWidth() - viewportBounds.getWidth()));
        scrollPane.setVvalue((valy + adjustment.getY())/(updatedInnerBounds.getHeight() - viewportBounds.getHeight()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courseOptions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Course course;
                if (courseHashMap.containsKey(newValue)) {
                    course = courseHashMap.get(newValue);
                } else {
                    course = CourseFactory.load(Paths.get(newValue));
                }

                loadCourse(course);
            } catch (Exception e) {
                instance.getConsole().log(Level.WARNING, e);
            }
        });

        Properties properties = Main.instance.getProperties();
        if (properties.containsKey("loadedCourses")) {
            String[] courses = properties.get("loadedCourses").toString().split(" ");
            for (int i = 0; i < courses.length; i++) {
                courseOptions.getItems().add(courses[i]);
            }
        }
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
            parent.setTranslateX(100 + 500 * height);
            parent.setTranslateY(graph.getMinWidth() / (index + 2));
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

                loadCourse(course);
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
