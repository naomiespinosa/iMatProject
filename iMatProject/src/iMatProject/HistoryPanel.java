
package iMatProject;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;


public class HistoryPanel extends AnchorPane  {
    private Model model = Model.getInstance();
iMatMainWindowController parentController;
    private IMatDataHandler iMatDataHandler;

    @FXML
    Label orderNumber;
    @FXML
    Label orderDate;
    @FXML
    Label orderItems;
    @FXML
    Label prevOrders;

    private Product product;
    Order order;


    public HistoryPanel(Order order,iMatMainWindowController controller) {
        iMatDataHandler = IMatDataHandler.getInstance();




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
        orderNumber.setText("Beställnings nummer : "+String.valueOf(order.getOrderNumber()));
        orderItems.setText("Antal ITems"+ String.valueOf(order.getItems().size()));
        orderDate.setText("Beställnings datum : "+ String.valueOf(order.getDate()));
        prevOrders.setText("Antal tidigare köp: " + model.getNumberOfOrders());



    }









}