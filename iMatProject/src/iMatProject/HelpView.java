package iMatProject;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelpView extends AnchorPane {

    public HelpView(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HelpView.fxml"));
        fxmlLoader.setRoot(this);
       fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
