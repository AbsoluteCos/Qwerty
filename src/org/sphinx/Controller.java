package org.sphinx;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/10/2018
 */
public class Controller {
    protected Stage stage;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public static Scene toScene(FXMLBundle bundle){
        return new Scene(bundle.getParent());
    }

    public static Stage toStage(FXMLBundle bundle){
        Stage stage = new Stage();
        stage.setScene(toScene(bundle));

        if(bundle.getController() instanceof Controller){
            ((Controller) bundle.getController()).setStage(stage);
        }
        return stage;
    }
}
