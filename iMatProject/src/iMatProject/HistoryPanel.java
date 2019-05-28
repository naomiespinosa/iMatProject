
package iMatProject;

import java.io.IOException;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;


public class HistoryPanel extends AnchorPane  {
    private Model model = Model.getInstance();
iMatMainWindowController parentController;


    @FXML
    Label orderNumber;
    @FXML
    Label orderDate;
    @FXML
    Label orderItems;
    @FXML
    AnchorPane historyAnchor;
    @FXML
    AnchorPane clickPane;
    @FXML
    FlowPane historyFlow;
    @FXML
    Button close;
    @FXML
    Button buy;

    private Product product;
    Order order;


    public HistoryPanel(Order order,iMatMainWindowController controller) {






        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HistoryPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.order = order;
        this.parentController = controller;
        orderNumber.setText("Beställningsnummer : "+String.valueOf(order.getOrderNumber()));
        orderItems.setText("Antal varor : "+ String.valueOf(order.getItems().size()));
        orderDate.setText("Beställningsdatum : "+ String.valueOf(order.getDate().toString().replace("Mon","Mån / ").replace("Tue", "Tis / ").replace("Wed","Ons / ").replace("Thu", "Tors / ").replace("Fri","Fre / ").replace("Sat","Lör / ").replace("Sun", "Sön / ").replace("January","Januari / ").replace("February", "Februari / ").replace("Marsh","Mars / ").replace("May","Maj / ").replace("June", "Juni / ").replace("July","Juli / ").replace("August", "Augusti / ").replace("October","Oktober").replace("CEST","")));
        close.setOnAction(event -> historyAnchor.toBack());
        buy.setOnAction(event -> addToCart());


    }
    public void addToCart(){
        for (ShoppingItem item : order.getItems()){
            model.addToShoppingCart(item.getProduct());
        }
    }

    public void onClick(List<ShoppingItem> items) {

        historyAnchor.toFront();

        for (ShoppingItem item : items) {


            historyFlow.getChildren().add(new SideItemPanel(item, parentController));

        }


        }
    @FXML
    public void onClickNavigation(){

            onClick(order.getItems());


    }









}