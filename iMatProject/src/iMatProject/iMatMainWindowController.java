package iMatProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.*;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class iMatMainWindowController implements Initializable, ShoppingCartListener {
    private final Model model = Model.getInstance();




public ProductCategory productCategory;


    @FXML
    private Label priceLabel;
    @FXML
    private Label ItemCountLabel;
    @FXML
    private TextField SearchTextField;
    @FXML
    private Button wizardDemoButton;
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
    public AnchorPane wizardAnchorPane;

    @FXML
    private AnchorPane shoppingCartPane;
    @FXML
    private TreeView <String> catTreeView;
    @FXML
    public FlowPane productFlowPane;
  //  @FXML
 //   private FlowPane wizardFlowPane;
    @FXML
    private void handleSearchAction(ActionEvent event) {

        List<Product> matches = model.findProducts(SearchTextField.getText());
        updateProductList(matches);
        System.out.println("# matching products: " + matches.size());

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model.getShoppingCart().addShoppingCartListener(this);
        TreeItem<String> root = new TreeItem<>("Sortiment");
        root.setExpanded(true);
        TreeItem<String> cat1 = new TreeItem(ProductCategory.BERRY);
        TreeItem<String> cat2 = new TreeItem(ProductCategory.BREAD);
        TreeItem<String> cat3 = new TreeItem(ProductCategory.CABBAGE);
        TreeItem<String> cat4 = new TreeItem(ProductCategory.CITRUS_FRUIT);
        TreeItem<String> cat5 = new TreeItem(ProductCategory.COLD_DRINKS);
        TreeItem<String> cat7 = new TreeItem(ProductCategory.DAIRIES);
        TreeItem<String> cat8 = new TreeItem(ProductCategory.EXOTIC_FRUIT);
        TreeItem<String> cat9 = new TreeItem(ProductCategory.FISH);
        TreeItem<String> cat10 = new TreeItem(ProductCategory.FLOUR_SUGAR_SALT);
        TreeItem<String> cat11 = new TreeItem(ProductCategory.FRUIT);
        TreeItem<String> cat12 = new TreeItem(ProductCategory.HERB);
        TreeItem<String> cat13 = new TreeItem(ProductCategory.HOT_DRINKS);
        TreeItem<String> cat14 = new TreeItem(ProductCategory.MEAT);
        TreeItem<String> cat15 = new TreeItem(ProductCategory.MELONS);
        TreeItem<String> cat16 = new TreeItem(ProductCategory.NUTS_AND_SEEDS);
        TreeItem<String> cat17 = new TreeItem(ProductCategory.PASTA);
        TreeItem<String> cat18 = new TreeItem(ProductCategory.POD);
        TreeItem<String> cat19 = new TreeItem(ProductCategory.POTATO_RICE);
        TreeItem<String> cat20 = new TreeItem(ProductCategory.ROOT_VEGETABLE);
        TreeItem<String> cat21 = new TreeItem(ProductCategory.SWEET);
//     cat6 is missning. Just forgot to add and was lazy to do editing


        root.getChildren().addAll(cat1,cat2,cat3,cat4,cat5,cat7,cat8,cat9,cat10,cat11,cat12,cat13,cat14,cat15,cat16,cat17,cat18,cat19,cat20,cat21);

        catTreeView.setRoot(root);
        updateProductList(model.getProducts());
        updateShoppingCart();
    }

    @Override
    public void shoppingCartChanged(CartEvent evt) { updateShoppingCart();

    }
    private void updateProductList(List<Product> products) {

        productFlowPane.getChildren().clear();

        for (Product product : products) {

            productFlowPane.getChildren().add(new ProductPanel(product));
        }
    }
    private void updateShoppingCart() {

        ShoppingCart shoppingCart = model.getShoppingCart();

        ItemCountLabel.setText("Antal varor: " + shoppingCart.getItems().size());
        priceLabel.setText("Kostnad: " + String.format("%.2f",shoppingCart.getTotal()));


    }
    private void wizardDemo(){
       // wizardAnchorPane.getChildren().clear();
        wizardAnchorPane.getChildren().add(new Wizard());
        wizardAnchorPane.toFront();
    }
    @FXML
public void showCategoryOnClick(MouseEvent mouseEvent){
        TreeItem<String> item = catTreeView.getSelectionModel().getSelectedItem();
 //   model.getCategory();
}
@FXML
    public void wizarDemoAction(ActionEvent event){
        wizardDemo();
}
}
