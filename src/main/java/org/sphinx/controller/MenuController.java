package org.sphinx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.sphinx.FXMLBundle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import static org.sphinx.Main.instance;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/10/2018
 */
public class MenuController extends Controller implements Initializable {
    private final URL settingsLocation = getClass().getResource("/Settings.fxml");
    private final URL displayLocation = getClass().getResource("/Display.fxml");

    @FXML
    private Text versionText;

    @FXML
    public void resumeACourse(){
        try {
            FXMLBundle bundle = new FXMLBundle(displayLocation);
            stage.setScene(Controller.toScene(bundle));
            Controller.putIf(bundle, stage);
        } catch (IOException e) {
            instance.getConsole().log(Level.SEVERE, e);
        }
    }

    @FXML
    public void openSettings() {
        try {
            Stage stage = Controller.toStage(new FXMLBundle(settingsLocation));
            stage.show();
        } catch (IOException e) {
            instance.getConsole().log(Level.SEVERE, e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        versionText.setText(instance.getProperties().getProperty("version", "0.0"));
    }
}

