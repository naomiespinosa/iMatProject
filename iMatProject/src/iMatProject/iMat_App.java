package iMatProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class iMat_App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("iMatMainWindow.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("IMAT");
        stage.setScene(scene);
        stage.setMaxHeight(820);
        stage.setMinHeight(820);
        stage.setMaxWidth(1310);
        stage.setMinWidth(1310);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                Model.getInstance().shutDown();
            }
        }));
    }


}



