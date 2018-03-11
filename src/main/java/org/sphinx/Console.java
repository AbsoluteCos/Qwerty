package org.sphinx;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/10/2018
 */
public class Console {
    private final Logger logger = Logger.getLogger("Console");

    private final URL alertURL = getClass().getResource("/Alert.fxml");

    public String log(Level level, String message, Exception e){
        StringBuilder builder = new StringBuilder();
        if(message != null){
            builder.append(message);
        }

        if(message != null && e != null){
            builder.append("\n");
        }

        if(e != null){
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            builder.append(writer.toString());
        }

        logger.log(level, builder.toString());

        if (level.equals(Level.SEVERE)) {
            Platform.runLater(() -> {
                try {
                    FXMLLoader loader = new FXMLLoader(alertURL);
                    Parent parent = loader.load();
                    Alert alert = loader.getController();
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                    alert.setMessage(message);
                    alert.setStackTrace(e);
                } catch (IOException e1) {
                    //woops?
                    e1.printStackTrace();
                    System.exit(-1);
                }
            });
        }
        return builder.toString();
    }

    public String log(Level level, String message){
        return log(level, message, null);
    }

    public String log(Level level, Exception e){
        return log(level, null, e);
    }
}
