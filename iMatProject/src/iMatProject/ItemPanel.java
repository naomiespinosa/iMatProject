
package iMatProject;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingItem;


public class ItemPanel extends AnchorPane  {

iMatMainWindowController parentController;


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

    public ItemPanel( ShoppingItem item, iMatMainWindowController controller) {



        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.item = item;
        this.parentController = controller;
        nameLabel.setText(item.getProduct().getName());
        prizeLabel.setText(String.format("%.2f", item.getProduct().getPrice()) + " " + item.getProduct().getUnit() + "");
        imageView.setImage(model.getImage(item.getProduct()));
        if (!item.getProduct().isEcological()) {
            ecoLabel.setText("");
        }



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
        parentController.cartUpdate(model.getShoppingCart().getItems());
        parentController.updateShoppingCart();
        if (model.getShoppingCart().getTotal() == 0){
            parentController.payButton.setDisable(true);
        }
        else parentController.payButton.setDisable(false);
    }



}