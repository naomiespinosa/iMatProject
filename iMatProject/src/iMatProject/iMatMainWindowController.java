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
import java.util.concurrent.Flow;

import static se.chalmers.cse.dat216.project.ProductCategory.*;

public class iMatMainWindowController implements Initializable, ShoppingCartListener, WizardListener{

    private final Model model = Model.getInstance();
    private Wizard wizard;
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
    public TextField SearchTextField;

    @FXML
     Button helpButton;

    @FXML
     Button logoButton;

    @FXML
    public Button searchButton;

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
    public AnchorPane wizardAnchorPane;

    @FXML
     AnchorPane shoppingCartPane;

    @FXML
     TreeView<String> catTreeView;
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
    @FXML
    ScrollPane favoritesScrollPane;
    @FXML
    ScrollPane prodcutScrollPane;


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

/*
        TreeItem<String> berry = new TreeItem(ProductCategory.BERRY);
        berry = new TreeItem<>("Bär");
        TreeItem<String> bread = new TreeItem(ProductCategory.BREAD);
        bread = new TreeItem<>("Bröd");
        TreeItem<String> cabbage = new TreeItem(ProductCategory.CABBAGE);
        cabbage = new TreeItem<>("Kål");
        TreeItem<String> citruses = new TreeItem(ProductCategory.CITRUS_FRUIT);
        TreeItem<String> colds = new TreeItem(ProductCategory.COLD_DRINKS);
        TreeItem<String> diaries = new TreeItem(ProductCategory.DAIRIES);
        TreeItem<String> exotics = new TreeItem(ProductCategory.EXOTIC_FRUIT);
        TreeItem<String> fish = new TreeItem(ProductCategory.FISH);
        TreeItem<String> flourAndsuggar = new TreeItem(ProductCategory.FLOUR_SUGAR_SALT);
        TreeItem<String> fruit = new TreeItem(ProductCategory.FRUIT);
        TreeItem<String> herbs = new TreeItem(ProductCategory.HERB);
        TreeItem<String> hots = new TreeItem(ProductCategory.HOT_DRINKS);
        TreeItem<String> meat = new TreeItem(ProductCategory.MEAT);
        TreeItem<String> melons = new TreeItem(ProductCategory.MELONS);


        TreeItem<String> nuts = new TreeItem(ProductCategory.NUTS_AND_SEEDS);
        TreeItem<String> pasta = new TreeItem(ProductCategory.PASTA);
        TreeItem<String> pod = new TreeItem(ProductCategory.POD);
        TreeItem<String> potato = new TreeItem(ProductCategory.POTATO_RICE);
        TreeItem<String> rootVegs = new TreeItem(ProductCategory.ROOT_VEGETABLE);
        TreeItem<String> sweets = new TreeItem(ProductCategory.SWEET);
        TreeItem<String> vegFruits = new TreeItem(ProductCategory.VEGETABLE_FRUIT); */


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
      /*  dryck.getChildren().addAll(colds, hots);
        frukt.getChildren().addAll(berry, citruses, exotics, fruit, melons);
        gronssaker.getChildren().addAll(cabbage, herbs, rootVegs, vegFruits);
        kott.getChildren().addAll(fish, meat);
        mejeri.getChildren().addAll(diaries);
        notter.getChildren().addAll(nuts, pod);
        skafferi.getChildren().addAll(bread, flourAndsuggar, pasta, potato);
        sotsaker.getChildren().addAll(sweets);*/

        catTreeView.getSelectionModel().selectedItemProperty().addListener(((vlaue, oldValue, newValue) -> {
            if (newValue != null)
                System.out.println(newValue);

        }));
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

            favoritesFlowPane.getChildren().add(new ProductPanel(product, this));
        }

    }
    public void cartUpdate(List<ShoppingItem> items) {
        cartView.getChildren().clear();
        sideCartFlowPane.getChildren().clear();

        for (ShoppingItem item : items) {

            cartView.getChildren().add(new ItemPanel(item, this));
            sideCartFlowPane.getChildren().add(new ItemPanel(item, this));
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
        wizard.updateWizardPane();
        SearchTextField.setVisible(false);
        searchButton.setVisible(false);
        cartAnchorPane.toBack();
        wizardAnchorPane.getChildren().clear();
        wizardAnchorPane.getChildren().add(wizard);
        wizardAnchorPane.toFront();

    }
    private void helpNavigation(){
        SearchTextField.setVisible(false);
        searchButton.setVisible(false);
        wizardAnchorPane.getChildren().clear();
        wizardAnchorPane.getChildren().add(new HelpView());
        wizardAnchorPane.toFront();
    }

    public  void logoButtonNavigation(){
        SearchTextField.setVisible(true);
        searchButton.setVisible(true);
        wizardAnchorPane.toBack();
        cartAnchorPane.toBack();
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
                products.addAll(model.getProductsByCat(FISH));
            break;

            case "TreeItem [ value: Mejeri ]":
                products.addAll(model.getProductsByCat(DAIRIES));
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

