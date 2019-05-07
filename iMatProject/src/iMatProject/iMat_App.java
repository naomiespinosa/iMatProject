package iMatProject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class iMat_App {

    //@Override
    public void start(Stage stage) throws Exception {

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("iMatProject/resources/iMatProject");

        Parent root = FXMLLoader.load(getClass().getResource(".fxml"), bundle);

        Scene scene = new Scene(root, 800, 500);

        stage.setTitle(bundle.getString("application.name"));
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}



