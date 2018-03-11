package org.sphinx;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;

import static org.sphinx.Main.instance;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/10/2018
 */
public class MenuController extends Controller {
    private final URL settingsLocation = getClass().getResource("/Settings.fxml");

    @FXML
    private Text versionText;

    @FXML
    public void openSettings() {
        try {
            Stage stage = Controller.toStage(new FXMLBundle(settingsLocation));
            stage.show();
        } catch (IOException e) {
            instance.getConsole().log(Level.SEVERE, e);
        }
    }
}
