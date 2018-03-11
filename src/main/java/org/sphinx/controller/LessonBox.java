package org.sphinx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import org.sphinx.FXMLBundle;
import org.sphinx.Lesson;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;

import static org.sphinx.Main.instance;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/11/2018
 */
public class LessonBox {
    @FXML
    private TitledPane namePane;

    private final URL lessonPaneURL = getClass().getResource("/LessonPane.fxml");

    @FXML
    private TextArea descriptionArea;

    private Lesson lesson;

    @FXML
    public void start() {
        try {
            FXMLBundle bundle = new FXMLBundle(lessonPaneURL);
            Stage stage = Controller.toStage(bundle);
            stage.show();

            Object controller = bundle.getController();
            if (controller instanceof LessonController) {
                ((LessonController) controller).load(lesson);
            }
        } catch (IOException e) {
            instance.getConsole().log(Level.WARNING, e);
        }
    }

    public void setLesson(Lesson lesson){
        this.namePane.setText(lesson.getName());
        this.descriptionArea.setText(lesson.getDescription());

        this.lesson = lesson;
    }
}
