
package iMatProject;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;


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
        imageView.setImage(model.getImage(product, kImageWidth, kImageWidth * kImageRatio));
        if (!product.isEcological()) {
            ecoLabel.setText("");
        }
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


           // favoriteButton.setStyle("-fx-background-color: #4c1036;");
            model.addFavorites(product);
            colorChangeControl();
            parentController.updateFavorite(model.getFavorites());

        } else {

            //favoriteButton.setStyle("-fx-background-color: #ffffff;");

            model.removeFavorites(product);

            colorChangeControl();

            parentController.updateFavorite(model.getFavorites());
            parentController.updateProductList(model.getProducts());

            System.out.println("Remove fav:  " + product.getProductId());
        }
    }


}