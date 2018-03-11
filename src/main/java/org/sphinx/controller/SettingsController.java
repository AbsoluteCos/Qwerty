package org.sphinx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.sphinx.Main;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/10/2018
 */
public class SettingsController extends Controller implements Initializable {
    @FXML
    private GridPane propertiesGridpane;

    @FXML
    private GridPane settingsGridpane;

    private Scene previousScene;

    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Properties properties = Main.instance.getProperties();
        
        BiConsumer<Object, Object> propertiesConsumer = new PropertiesConsumer();
        System.getenv().forEach(propertiesConsumer);

        BiConsumer<Object, Object> settingsConsumer = new SettingsConsumer();
        properties.forEach(settingsConsumer);

        propertiesGridpane.setVgap(20);
        settingsGridpane.setVgap(20);
    }
    
    private class PropertiesConsumer implements BiConsumer<Object, Object> {
        int counter = 0;
        
        @Override
        public void accept(Object o, Object o2) {
            Text text = new Text(o.toString());
            TextField field = new TextField(o2.toString());

            propertiesGridpane.add(text, 0, counter);
            propertiesGridpane.add(field, 1, counter);

            counter++;
        }
    }

    private class SettingsConsumer implements BiConsumer<Object, Object> {
        int counter = 0;

        @Override
        public void accept(Object o, Object o2) {
            Text text = new Text(o.toString());
            TextField field = new TextField(o2.toString());

            settingsGridpane.add(text, 0, counter);
            settingsGridpane.add(field, 1, counter);

            counter++;
        }
    }
}
