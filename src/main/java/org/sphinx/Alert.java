package org.sphinx;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/11/2018
 */
public class Alert {
    @FXML
    private Text messageText;

    @FXML
    private TextArea stackTraceArea;

    public void setMessage(String message) {
        messageText.setText(message);
    }

    public void setStackTrace(Exception e) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        stackTraceArea.setText(writer.toString());
    }
}
