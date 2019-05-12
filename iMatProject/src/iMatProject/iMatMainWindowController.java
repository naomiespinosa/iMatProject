package iMatProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class iMatMainWindowController implements Initializable, ShoppingCartListener {
    private final Model model = Model.getInstance();

    @FXML
    private TextField SearchTextField;

    @FXML
    private Button helpButton;

    @FXML
    private Button logoButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button toCartButton;

    @FXML
    private Button favoritsButton;

    @FXML
    private Button historyButton;

    @FXML
    private AnchorPane sortimentPane;

    @FXML
    private AnchorPane shoppingCartPane;

    @FXML
    private FlowPane productFlowPane;
    @FXML
    private void handleSearchAction(ActionEvent event) {

        List<Product> matches = model.findProducts(SearchTextField.getText());
        updateProductList(matches);
        System.out.println("# matching products: " + matches.size());

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateProductList(model.getProducts());
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {

    }
    private void updateProductList(List<Product> products) {

        productFlowPane.getChildren().clear();

        for (Product product : products) {

            productFlowPane.getChildren().add(new ProductPanel(product));
        }

    }

}
