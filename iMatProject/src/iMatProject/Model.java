package iMatProject;



import java.util.Date;
import java.util.List;

import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;
import se.chalmers.cse.dat216.project.Order;


public class Model {

    private static Model instance = null;
    private IMatDataHandler iMatDataHandler;
    private Customer customer;
    private Product product;
    private Order order;
    private List<Order> orders;
    ShoppingCart shoppingCart;
    Date date;


    protected Model() {


    }


    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
            instance.init();
        }
        return instance;
    }

    private void init() {

        iMatDataHandler = IMatDataHandler.getInstance();


    }

    public Date getDate(){
        return  order.getDate();
    }
    public int getItems(){
        return order.getItems().size();
    }
    public int getOrderNumber() {
        return order.getOrderNumber();
    }






    public List<Product> getProducts() {
        return iMatDataHandler.getProducts();
    }
    public List<Product> getProductsByCat(ProductCategory cat) {return iMatDataHandler.getProducts(cat);}

    public Product getProduct(int idNbr) {
        return iMatDataHandler.getProduct(idNbr);
    }

    public List<Product> findProducts(java.lang.String s) {
        return iMatDataHandler.findProducts(s);
    }

    public javafx.scene.image.Image getImage(Product p) {
        return iMatDataHandler.getFXImage(p);
    }

    public Image getImage(Product p, double width, double height) {
        return iMatDataHandler.getFXImage(p, width, height);
    }

    public void addToShoppingCart(Product p) {
        ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();

        ShoppingItem item = new ShoppingItem(p);
        Model.getInstance().getShoppingCart().addItem(item);
        System.out.println(shoppingCart.getItems().size());
       // shoppingCart.addProduct(p);
    }
    public void removeFromShoppingCart(Product p) {

        for(ShoppingItem item : Model.getInstance().getShoppingCart().getItems()){
            if(item.getProduct().equals(p)){
                Model.getInstance().getShoppingCart().removeItem(item);

            }

        }

        /*  ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();

        ShoppingItem item = new ShoppingItem(p);
        Model.getInstance().getShoppingCart().removeItem(item);*/

       System.out.println(getShoppingCart().getItems().size());

    }
    public boolean isFavorite(Product p){
       return iMatDataHandler.isFavorite(p);

    }
    public  List<Product> getFavorites(){
       return iMatDataHandler.favorites();

    }


    public Product addFavorites(Product p) {
         iMatDataHandler.addFavorite(p);
         return p;
    }
    public Product removeFavorites(Product p) {
            iMatDataHandler.removeFavorite(p);
        return p;
    }

    public CreditCard getCreditCard() {
        return iMatDataHandler.getCreditCard();
    }

    public Customer getCustomer() {
        return iMatDataHandler.getCustomer();
    }

    public ShoppingCart getShoppingCart() {
        return iMatDataHandler.getShoppingCart();
    }
    public String getEmail() {
        return customer.getEmail();
    }
    public String getFirstName() {
        return customer.getFirstName();

    }
    public String getLastName() {
        return customer.getLastName();

    }
    public String getAddress() {
        return customer.getAddress();

    }
    public String getPostCode () {
        return customer.getPostCode();
    }
    public String getPhone () {
        return customer.getMobilePhoneNumber();
    }

    public void clearShoppingCart() {


        iMatDataHandler.getShoppingCart().clear();

    }

    public void placeOrder() {

        iMatDataHandler.placeOrder();

    }


    public int getNumberOfOrders() {

        return iMatDataHandler.getOrders().size();

    }
    public List<Order> getOrders() {
        return iMatDataHandler.getOrders();
    }




    public void shutDown() {
        iMatDataHandler.shutDown();
    }








}
