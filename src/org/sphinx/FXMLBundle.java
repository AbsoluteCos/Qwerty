package org.sphinx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/10/2018
 */
public class FXMLBundle<T> {
    private final Parent parent;
    private final T controller;
    private final Path location;

    public FXMLBundle(Path location) throws IOException {
        this.location = location;

        FXMLLoader loader = new FXMLLoader(location.toUri().toURL());
        this.parent = loader.load();
        this.controller = loader.getController();
    }

    public Parent getParent(){
        return parent;
    }

    public T getController(){
        return controller;
    }

    public Path getLocation(){
        return location;
    }
}
