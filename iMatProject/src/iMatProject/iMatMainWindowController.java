package iMatProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.*;


import java.net.URL;
import java.util.*;
//import java.util.concurrent.Flow;

import static se.chalmers.cse.dat216.project.ProductCategory.*;

public class iMatMainWindowController implements Initializable, ShoppingCartListener, WizardListener{

    private final Model model = Model.getInstance();
    @FXML
    public TextField SearchTextField;
    @FXML
    public Button searchButton;
    @FXML
    public AnchorPane wizardAnchorPane;
    @FXML
    public FlowPane productFlowPane;
    @FXML
    public AnchorPane startAnchorPane;
    @FXML
    public FlowPane sideCartFlowPane;
    @FXML
    public AnchorPane cartAnchorPane;
    @FXML
    public FlowPane cartView;
    @FXML
    public FlowPane favoritesFlowPane;
    Product product;
    @FXML

     Label priceLabel;   // Main View
    @FXML

     Label ItemCountLabel;  // Main View
    @FXML

     Label previousShopLabel;  //Cart view
    @FXML

     Label productCountLabel;  //Cart view
    @FXML

     Label totalPriceLabel;    //Cart view
    @FXML
     Button helpButton;
    @FXML
     Button logoButton;
    @FXML
    Button clearButton;  // Main View
    @FXML
    Button clearButton1;  // Cart View
    @FXML
     Button toCartButton;
    @FXML
     Button favoritsButton;
    @FXML
     Button historyButton;
    @FXML
     AnchorPane sortimentPane;
    @FXML
     AnchorPane shoppingCartPane;
    @FXML
     TreeView<String> catTreeView;
    @FXML
    ScrollPane favoritesScrollPane;
    @FXML
    ScrollPane prodcutScrollPane;
    @FXML
    AnchorPane helpAnchorPane;
    @FXML
    AnchorPane historyAnchorPane;
    @FXML
    FlowPane historyView;



    private Wizard wizard;
ProductPanel productPanel;




    //  @FXML
    //   private FlowPane wizardFlowPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        wizard = new Wizard();
        wizard.setListener(this);



        SearchTextField.setVisible(false);
        searchButton.setVisible(false);
        product = new Product();

        model.getShoppingCart().addShoppingCartListener(this);
        TreeItem<String> root = new TreeItem<>("Sortiment");
        root.setExpanded(true);




        TreeItem<String> dryck = new TreeItem("Dryck");
        TreeItem<String> frukt = new TreeItem("Frukt");
        TreeItem<String> gronssaker = new TreeItem("Grönsaker");
        TreeItem<String> kott = new TreeItem("Kött & Fisk");
        TreeItem<String> mejeri = new TreeItem("Mejeri");
        TreeItem<String> notter = new TreeItem("Nötter & Bönor");
        TreeItem<String> skafferi = new TreeItem("Skafferi");
        TreeItem<String> sotsaker = new TreeItem("Sötsaker");

        catTreeView.setRoot(root);
        root.getChildren().addAll(dryck, frukt, gronssaker, kott, mejeri, notter, skafferi, sotsaker);

        catTreeView.getSelectionModel().selectedItemProperty().addListener(((vlaue, oldValue, newValue) -> {
            if (newValue != null)
                System.out.println(newValue);

        }));
        historyButton.setOnAction(event -> historyUpdate(model.getOrders()));



        updateFavorite(model.getFavorites());
        System.out.println(model.getFavorites());
        updateProductList(model.getProducts());
        cartUpdate(model.getShoppingCart().getItems());

        updateShoppingCart();



    }

    @Override
    public void shoppingCartChanged(CartEvent evt) {
        updateShoppingCart();
        cartUpdate(model.getShoppingCart().getItems());


    }

     void updateProductList(List<Product> products) {
        productFlowPane.getChildren().clear();

        for (Product product : products) {

            productFlowPane.getChildren().add( new ProductPanel(product, this));
        }

    }

    public void updateFavorite(List<Product> favorites) {
        favoritesFlowPane.getChildren().clear();

        for (Product product : favorites) {

            favoritesFlowPane.getChildren().add( new ProductPanel(product, this));
        }

    }
    public void cartUpdate(List<ShoppingItem> items) {

        cartView.getChildren().clear();
        sideCartFlowPane.getChildren().clear();

        for (ShoppingItem item : items) {


            cartView.getChildren().add(new ItemPanel(item, this));
            sideCartFlowPane.getChildren().add(new SideItemPanel(item, this));
        }
    }

    public void historyUpdate(List<Order> orders) {

        historyAnchorPane.toFront();

        for (Order order : orders) {


            historyView.getChildren().add(new HistoryPanel(order,this));

        }
    }


    private void updateShoppingCart() {

        ShoppingCart shoppingCart = model.getShoppingCart();

        ItemCountLabel.setText("Antal varor: " + shoppingCart.getItems().size());
        priceLabel.setText("Kostnad: " + String.format("%.2f",shoppingCart.getTotal()));
        productCountLabel.setText("Antal varor: " + shoppingCart.getItems().size());
        totalPriceLabel.setText("Kostnad: " + String.format("%.2f",shoppingCart.getTotal()));
        previousShopLabel.setText("Antal tidigare köp: " + model.getNumberOfOrders());

    }


    private void toCartNavigation(){
        updateFavorite(model.getFavorites());
        SearchTextField.setVisible(false);
        searchButton.setVisible(false);
        cartAnchorPane.toFront();
    }


    private void toFavoritesNavigation(){
        updateFavorite(model.getFavorites());

        SearchTextField.setVisible(false);
        searchButton.setVisible(false);

        prodcutScrollPane.toBack();
        favoritesScrollPane.toFront();

    }
    private void wizardHandler(){
        wizard.checkName();
        wizard.updateWizardPane();
        SearchTextField.setVisible(false);
        searchButton.setVisible(false);
        cartAnchorPane.toBack();
        wizardAnchorPane.getChildren().clear();
        wizardAnchorPane.getChildren().add(wizard);
        wizardAnchorPane.toFront();
        wizard.deliveryPane.toFront();

    }
    private void helpNavigation(){
       SearchTextField.setVisible(false);
        searchButton.setVisible(false);
        helpAnchorPane.getChildren().add(new HelpView(this));
        helpAnchorPane.toFront();


    }



    public  void logoButtonNavigation(){
        SearchTextField.setVisible(true);
        searchButton.setVisible(true);
        helpAnchorPane.toBack();
        wizardAnchorPane.toBack();
        cartAnchorPane.toBack();
        historyAnchorPane.toBack();
        prodcutScrollPane.toFront();
    }

    public void startShoppingNavigation(){
        SearchTextField.setVisible(true);
        searchButton.setVisible(true);
        startAnchorPane.toBack();
        productFlowPane.toFront();

    }

    @FXML
    private void handleSearchAction(ActionEvent event) {
        wizardAnchorPane.toBack();
        productFlowPane.toFront();
        List<Product> matches = model.findProducts(SearchTextField.getText());
        updateProductList(matches);
        System.out.println("# matching products: " + matches.size());

    }
    @FXML
    public void logoButtonNavigationAction(ActionEvent event){
        logoButtonNavigation();
    }

    @FXML
public void showCategoryOnClick(MouseEvent mouseEvent){
        TreeItem<String> item = catTreeView.getSelectionModel().getSelectedItem();
}

    @FXML
    public void onCategoryClick(MouseEvent mouseEvent){
        prodcutScrollPane.toFront();
       TreeItem<String> item = catTreeView.getSelectionModel().getSelectedItem();
       String category = item.toString();
        List<Product> products = new ArrayList<>();
       switch (category){
            case "TreeItem [ value: Dryck ]":
                products.addAll(model.getProductsByCat(COLD_DRINKS));
                products.addAll(model.getProductsByCat(HOT_DRINKS));break;
            case "TreeItem [ value: Grönsaker ]":
                products.addAll(model.getProductsByCat(CABBAGE));
                products.addAll(model.getProductsByCat(ROOT_VEGETABLE));
                products.addAll(model.getProductsByCat(VEGETABLE_FRUIT));
                products.addAll(model.getProductsByCat(HERB));break;
            case "TreeItem [ value: Kött & Fisk ]":
                products.addAll(model.getProductsByCat(MEAT));
                products.addAll(model.getProductsByCat(FISH));break;

            case "TreeItem [ value: Mejeri ]":
                products.addAll(model.getProductsByCat(DAIRIES));break;
            case "TreeItem [ value: Frukt ]":
                products.addAll(model.getProductsByCat(BERRY));
                products.addAll(model.getProductsByCat(CITRUS_FRUIT));
                products.addAll(model.getProductsByCat(EXOTIC_FRUIT));
                products.addAll(model.getProductsByCat(FRUIT));
                products.addAll(model.getProductsByCat(MELONS));break;
           case "TreeItem [ value: Nötter & Bönor ]":
               products.addAll(model.getProductsByCat(NUTS_AND_SEEDS));
               products.addAll(model.getProductsByCat(POD));break;
           case "TreeItem [ value: Skafferi ]":
               products.addAll(model.getProductsByCat(BREAD));
               products.addAll(model.getProductsByCat(FLOUR_SUGAR_SALT));
               products.addAll(model.getProductsByCat(PASTA));
               products.addAll(model.getProductsByCat(POTATO_RICE));break;

           case "TreeItem [ value: Sötsaker ]":
               products.addAll(model.getProductsByCat(SWEET));break;

        }

        updateProductList(products);
    }

    @FXML
    public void toPaymentAction(ActionEvent event){
        wizardHandler();
}


    @FXML
    public void toCartNavigationAction(ActionEvent event){
        toCartNavigation();
    }
    @FXML
    public void toFavoritesNavigationAction(ActionEvent event){
        toFavoritesNavigation();
    }

    @FXML
    public void helpNavigationAction(ActionEvent event){
        helpNavigation();
    }
    @FXML
    public void startShoppingNavigationAction (ActionEvent event){
        startShoppingNavigation();
    }
    @FXML
    private void clearCartAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       // alert.setTitle("");
        alert.setHeaderText("Vill du Tömma varukorgen?");
      //  alert.setContentText("");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            model.clearShoppingCart();
        } else {

        }

    }


    @Override

    public void onWizardFinish() {

        model.placeOrder();
        wizard.deliveryPane.toFront();
        wizardAnchorPane.toBack();
        productFlowPane.toFront();
        SearchTextField.setVisible(true);
        searchButton.setVisible(true);
        
    }

    @Override
    public void backToCart() {
        wizardAnchorPane.toBack();
        cartAnchorPane.toFront();
    }



}


