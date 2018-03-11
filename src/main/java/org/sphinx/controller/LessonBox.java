package org.sphinx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import org.sphinx.Lesson;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/11/2018
 */
public class LessonBox {
    @FXML
    private TitledPane namePane;

    public void setLesson(Lesson lesson){
        namePane.setText(lesson.getName());
    }
}
