package org.sphinx.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.sphinx.Lesson;
import org.sphinx.Main;
import org.sphinx.course.Course;
import org.sphinx.course.CourseFactory;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/10/2018
 */
public class DisplayController implements Initializable {
    private final HashMap<String, Course> courseHashMap = new HashMap<>();

    @FXML
    private AnchorPane graph;

    @FXML
    private ChoiceBox<String> courseOptions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courseOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Course course = courseHashMap.get(newValue);
                loadCourse(course);
            }
        });
    }

    private void loadCourse(Course course) {
        List<Lesson> lessons = course.getLessons();
        for (Lesson lesson : lessons) {
            int height = lesson.getHeight();
            int index = lesson.getIndex();


        }
    }

    @FXML
    public void addCourse(){
        DirectoryChooser chooser = new DirectoryChooser();
        Path directory = chooser.showDialog(new Stage()).toPath();
        Path courseXML = directory.resolve("course.xml");

        try {
            Course course = CourseFactory.load(courseXML);
            courseOptions.getItems().add(course.getName());

            courseHashMap.put(course.getName(), course);
        } catch (IOException | SAXException e) {
            Main.instance.getConsole().log(Level.WARNING, e);
        }
    }

    @FXML
    public void removeCourse(){
        courseOptions.getItems().remove(courseOptions.getSelectionModel().getSelectedItem());
    }
}
