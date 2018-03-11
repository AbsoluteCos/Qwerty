package org.sphinx.controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import org.sphinx.Lesson;

import java.util.List;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/11/2018
 */
public class LessonController {
    @FXML
    private WebView webView;

    private int currentPage = 0;
    private List<String> pages;

    @FXML
    public void back() {
        String page = pages.get(--currentPage);
        loadPage(page);
    }

    public void loadPage(String content) {
        webView.getEngine().load(content);
    }

    @FXML
    public void next() {
        String page = pages.get(++currentPage);
        loadPage(page);
    }

    public void load(Lesson lesson) {
        pages = lesson.getPages();

        currentPage = 0;
    }
}
