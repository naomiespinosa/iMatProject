package iMatProject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelpView extends AnchorPane {
@FXML
    Button closeHelpButton;


    public HelpView(iMatMainWindowController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HelpView.fxml"));
        fxmlLoader.setRoot(this);
       fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
       closeHelpButton.setOnAction(event -> controller.helpAnchorPane.toBack());
    }
}
