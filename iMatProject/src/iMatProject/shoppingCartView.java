package iMatProject;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class shoppingCartView extends AnchorPane {
    private Model model = Model.getInstance();

    private Product product;

   // private final static double kImageWidth = 100.0;
 //   private final static double kImageRatio = 0.75;

    public shoppingCartView(Product product) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingCart.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}