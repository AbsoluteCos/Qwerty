package org.sphinx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.sphinx.course.CourseFactory;

import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/10/2018
 */
public class DisplayController implements Initializable {
    @FXML
    private AnchorPane graph;

    @FXML
    private ChoiceBox<String> courseOptions;

    @FXML
    public void addCourse(){
        DirectoryChooser chooser = new DirectoryChooser();
        Path directory = chooser.showDialog(new Stage()).toPath();
        Path courseXML = directory.resolve("course.xml");

        CourseFactory factory = new CourseFactory();
    }

    @FXML
    public void removeCourse(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
