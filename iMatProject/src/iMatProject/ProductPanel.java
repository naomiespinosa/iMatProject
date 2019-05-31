
package iMatProject;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;


public class ProductPanel extends AnchorPane  {
    private final static double kImageWidth = 75;
    private final static double kImageRatio = 0.75;
iMatMainWindowController parentController;
private Boolean fave;
    @FXML
    ImageView imageView;
    @FXML
    Label nameLabel;
    @FXML
    Label prizeLabel;
    @FXML
    Label ecoLabel;
    @FXML
    Button favoriteButton;
    @FXML
    Button minus;
    private Model model = Model.getInstance();
    private Product product;


    public ProductPanel(Product product, iMatMainWindowController controller, Boolean fave) {



        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        this.parentController = controller;
        nameLabel.setText(product.getName());
        prizeLabel.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit() + "");
        imageView.setImage(model.getImage(product));
        if (!product.isEcological()) {
            ecoLabel.setText("");
        }

        this.fave = fave;
        if (fave){
            favoriteButton.setStyle("-fx-background-color: #4c1036;");

        }
        else favoriteButton.setStyle("-fx-background-color: #ffffff;");


    }
    public void colorChangeControl(){

        if (model.isFavorite(product)){
            favoriteButton.setStyle("-fx-background-color: #4c1036;");
        }
        else favoriteButton.setStyle("-fx-background-color: #ffffff;");

    }




    @FXML
    private void handleAddAction(ActionEvent event) {
        ShoppingCart shoppingCart = model.getShoppingCart();
        if (shoppingCart.getItems().size() == 0){
            Notifications.create().text("Varan har lagts till varukorgen").darkStyle().hideAfter(Duration.seconds(2)).position(Pos.BOTTOM_CENTER).showInformation();
        }

        System.out.println("Add " + product.getName());
        model.addToShoppingCart(product);
    }

    @FXML
    private void handleRemoveAction(ActionEvent event) {
        System.out.println("Remove " + product.getName());
        model.removeFromShoppingCart(product);

    }

    @FXML
    public void addFavoritesAction() {

        if (!model.isFavorite(product)) {



            model.addFavorites(product);
            colorChangeControl();
            parentController.updateFavorite(model.getFavorites());

        } else {


            model.removeFavorites(product);

            colorChangeControl();

            parentController.updateFavorite(model.getFavorites());
            parentController.updateProductList(model.getProducts());
            parentController.cartUpdate(model.getShoppingCart().getItems());

            System.out.println("Remove fav:  " + product.getProductId());
        }
    }



}