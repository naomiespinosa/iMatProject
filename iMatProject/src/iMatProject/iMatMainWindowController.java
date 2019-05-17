package iMatProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.*;
import iMatProject.Wizard;
import se.chalmers.cse.dat216.project.ProductCategory;



import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class iMatMainWindowController implements Initializable, ShoppingCartListener, WizardListener {
    private final Model model = Model.getInstance();
    private Wizard wizard;

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
    @FXML
    public AnchorPane cartAnchorPane;
  //  @FXML
 //   private FlowPane wizardFlowPane;
    @FXML
    private void handleSearchAction(ActionEvent event) {
        wizardAnchorPane.toBack();
        productFlowPane.toFront();
        List<Product> matches = model.findProducts(SearchTextField.getText());
        updateProductList(matches);
        System.out.println("# matching products: " + matches.size());

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        wizard = new Wizard();
        wizard.setListener(this);
       // Skafferi(Pasta, Flower, Potatoris, ), Frukt(Berry, Citrus, Exotic, Melons, Fruit),
        // Grönsaker(Vegetable fruit, cabbage, Root veg, Herb ), Nötter och Bönor(nuts seeds,Pod ), Mejrj(Diaries), Kött(meat, fish,) ,
        // Dryck(hot, cold,). Söttsaker(sweets)

        model.getShoppingCart().addShoppingCartListener(this);
        TreeItem<String> root = new TreeItem<>("Sortiment");
        root.setExpanded(true);


        TreeItem<String> berry = new TreeItem(ProductCategory.BERRY);
        TreeItem<String> bread = new TreeItem(ProductCategory.BREAD);
        TreeItem<String> cabbage = new TreeItem(ProductCategory.CABBAGE);
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
        TreeItem<String> vegFruits = new TreeItem(ProductCategory.VEGETABLE_FRUIT);


        TreeItem<String> dryck = new TreeItem("Dryck");
        TreeItem<String> frukt = new TreeItem("Frukt");
        TreeItem<String> gronssaker = new TreeItem("Grönsaker");
        TreeItem<String> kott = new TreeItem("Kött");
        TreeItem<String> mejeri = new TreeItem("Mejeri");
        TreeItem<String> notter = new TreeItem("Nötter & Bönor");
        TreeItem<String> skafferi = new TreeItem("Skafferi");
        TreeItem<String> sotsaker = new TreeItem("Sötsaker");

        catTreeView.setRoot(root);
        root.getChildren().addAll(dryck,frukt,gronssaker,kott,mejeri,notter,skafferi,sotsaker);
        dryck.getChildren().addAll(colds,hots);
        frukt.getChildren().addAll(berry,citruses,exotics, fruit, melons);
        gronssaker.getChildren().addAll(cabbage,herbs, rootVegs, vegFruits);
        kott.getChildren().addAll(fish,meat);
        mejeri.getChildren().addAll(diaries);
        notter.getChildren().addAll(nuts,pod);
        skafferi.getChildren().addAll(flourAndsuggar,pasta,potato);
        sotsaker.getChildren().addAll(sweets);

        updateProductList(model.getProducts());
        updateShoppingCart();
    }

    @Override
    public void shoppingCartChanged(CartEvent evt) { updateShoppingCart();

    }
    private void updateProductList(List<Product> products) {

        productFlowPane.getChildren().clear();

        for (Product product : products) {
            product.getCategory();
            productFlowPane.getChildren().add(new ProductPanel(product));
        }
    }
    private void updateShoppingCart() {

        ShoppingCart shoppingCart = model.getShoppingCart();

        ItemCountLabel.setText("Antal varor: " + shoppingCart.getItems().size());
        priceLabel.setText("Kostnad: " + String.format("%.2f",shoppingCart.getTotal()));


    }
    private void wizardDemo(){
        SearchTextField.setVisible(false);
        searchButton.setVisible(false);
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
        productFlowPane.toFront();
    }


    @FXML
    public void logoButtonNavigationAction(ActionEvent event){
        logoButtonNavigation();
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

    @FXML
    public void helpNavigationAction(ActionEvent event){
        helpNavigation();
    }
    @FXML
    private void clearCartAction(ActionEvent event) {
        model.clearShoppingCart();
    }

    @Override
    public void onWizardFinish() {
        wizardAnchorPane.toBack();
        productFlowPane.toFront();
    }
}

