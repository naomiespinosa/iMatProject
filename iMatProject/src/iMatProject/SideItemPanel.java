
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


public class SideItemPanel extends AnchorPane  {
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


    private ShoppingItem item;


    private final static double kImageWidth = 75;
    private final static double kImageRatio = 0.75;

    public SideItemPanel( ShoppingItem item, iMatMainWindowController controller) {



        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SideItemPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.item = item;
        this.paretnCOntroller = controller;
        nameLabel.setText(item.getProduct().getName());
        prizeLabel.setText(String.format("%.2f", item.getProduct().getPrice()) + " " + item.getProduct().getUnit() + "");
        imageView.setImage(model.getImage(item.getProduct(), kImageWidth, kImageWidth * kImageRatio));
        if (!item.getProduct().isEcological()) {
            ecoLabel.setText("");
        }



    }
    private void colorChangeControl(){
        if (model.isFavorite(item.getProduct())){
            favoriteButton.setStyle("-fx-background-color: #4c1036;");
        }
        else favoriteButton.setStyle("-fx-background-color: #ffffff;");

        if (!model.isFavorite(item.getProduct())){
            favoriteButton.setStyle("-fx-background-color: #ffffff;");
        }
        else favoriteButton.setStyle("-fx-background-color: #4c1036;");

    }




    @FXML
    private void handleAddAction(ActionEvent event) {
        System.out.println("Add " + item.getProduct().getName());
        model.addToShoppingCart(item.getProduct());
    }

    @FXML
    private void handleRemoveAction(ActionEvent event) {
        System.out.println("Remove " + item.getProduct().getName());
        model.removeFromShoppingCart(item.getProduct());
    }

    @FXML
    public void addFavoritesAction() {

        if (!model.isFavorite(item.getProduct())) {


           // favoriteButton.setStyle("-fx-background-color: #4c1036;");
            model.addFavorites(item.getProduct());
            colorChangeControl();
            paretnCOntroller.updateFavorite(model.getFavorites());

        } else {

            //favoriteButton.setStyle("-fx-background-color: #ffffff;");

            model.removeFavorites(item.getProduct());
            colorChangeControl();

            paretnCOntroller.updateFavorite(model.getFavorites());

            System.out.println("Remove fav:  " + item.getProduct().getProductId());
        }
    }


}