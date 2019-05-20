
package iMatProject;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;


public class ProductPanel extends AnchorPane  {
iMatMainWindowController paretnCOntroller;

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
    private ShoppingItem item;


    private final static double kImageWidth = 75;
    private final static double kImageRatio = 0.75;

    public ProductPanel(Product product, iMatMainWindowController controller) {



        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        this.paretnCOntroller = controller;
        nameLabel.setText(product.getName());
        prizeLabel.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit() + "");
        imageView.setImage(model.getImage(product, kImageWidth, kImageWidth * kImageRatio));
        if (!product.isEcological()) {
            ecoLabel.setText("");
        }
        if (model.isFavorite(product)){
            favoriteButton.setStyle("-fx-background-color: #4c1036;");
        }
        else favoriteButton.setStyle("-fx-background-color: #ffffff;");

        if (!model.isFavorite(product)){
            favoriteButton.setStyle("-fx-background-color: #ffffff;");
        }
        else favoriteButton.setStyle("-fx-background-color: #4c1036;");


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

            favoriteButton.setStyle("-fx-background-color: #4c1036;");
            model.addFavorites(product);
            paretnCOntroller.updateFavorite(model.getFavorites());

        } else {

            model.removeFavorites(product);
            paretnCOntroller.updateFavorite(model.getFavorites());
            favoriteButton.setStyle("-fx-background-color: #ffffff;");
            System.out.println("Remove fav:  " + product.getProductId());
        }
    }


}