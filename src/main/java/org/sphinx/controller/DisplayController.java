package org.sphinx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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

    }

    @FXML
    public void removeCourse(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
