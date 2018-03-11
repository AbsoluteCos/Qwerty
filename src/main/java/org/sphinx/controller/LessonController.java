package org.sphinx.controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import org.sphinx.Lesson;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/11/2018
 */
public class LessonController {
    @FXML
    private WebView webView;

    public void load(Lesson lesson) {
        String content = lesson.getRawContent();
        webView.getEngine().loadContent(content);
    }
}
