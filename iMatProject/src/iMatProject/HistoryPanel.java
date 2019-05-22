
package iMatProject;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;


public class HistoryPanel extends AnchorPane  {
    private Model model = Model.getInstance();
iMatMainWindowController parentController;
    private IMatDataHandler iMatDataHandler;
    ShoppingItem item;

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
        orderItems.setText("Antal varor : "+ String.valueOf(order.getItems().size()));
        orderDate.setText("Beställnings datum : "+ String.valueOf(order.getDate()));











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