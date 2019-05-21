package iMatProject;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;
import javafx.scene.control.RadioButton;

import java.io.IOException;

public class Wizard extends AnchorPane {


    @FXML
    public AnchorPane deliveryPane;
    @FXML
     AnchorPane personalInfoPane;
    @FXML
     RadioButton homeRadioButton;
    @FXML
      RadioButton takeRadioButton;
    @FXML
     ImageView backArrow2;
    @FXML
     ImageView backArrow3;
    @FXML
     AnchorPane buyMessagePane;
    @FXML
     TextField firstNameText;

    @FXML
     TextField postCodeText;

    @FXML
     TextField phoneText;

    @FXML
     TextField lastNameTExt;

    @FXML
     TextField addressText;

    @FXML
     TextField eMailText;

    @FXML
     ImageView backArrow32;

    @FXML
     ImageView backArrow31;

    @FXML
     AnchorPane payPane;

    @FXML
     Label cardTypeText;

    @FXML
     TextField cardNameText;

    @FXML
     TextField ccvText;

    @FXML
     TextField cardNumberText;

    @FXML
     ComboBox<String> cardTypeCombo;

    @FXML
     ComboBox<String> yearCombo;

    @FXML
     ComboBox<String> monthCombo;

    @FXML
     ImageView backArrow1;

    @FXML
     ImageView frontArrow1;

    @FXML
     AnchorPane confirmationPane;

    @FXML
     Button backToMainButton;

    @FXML
     Button confirmButton;

    @FXML
     ImageView backArrow;
    @FXML
     ProgressBar progressBar1;
    @FXML
     ProgressBar progressBar2;
    @FXML
     ProgressBar progressBar3;
    @FXML
    Text userText;
    @FXML
    Button userButton;

    private WizardListener listener;
    private Model model = Model.getInstance();
    private ToggleGroup deliveryToggleGroup;
    private iMatMainWindowController controller;
    public Wizard(){




        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Wizard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        Customer customer = model.getCustomer();



        deliveryToggleGroup = new ToggleGroup();
        homeRadioButton.setToggleGroup(deliveryToggleGroup);
        takeRadioButton.setToggleGroup(deliveryToggleGroup);


        progressBar1.setStyle("-fx-accent: #4c1036; ");
        progressBar2.setStyle("-fx-accent: #4c1036; ");
        progressBar3.setStyle("-fx-accent: #4c1036; ");

        monthCombo.getItems().addAll("1", "2","3", "4", "5", "6","7","8", "9","10", "11", "12");
        monthCombo.getSelectionModel().select("1");
        yearCombo.getItems().addAll("19", "20", "21", "22", "23", "24", "25","26","27" );
        yearCombo.getSelectionModel().select("19");
        cardTypeCombo.getItems().addAll("MasterCard", "Visa" );
        cardTypeCombo.getSelectionModel().select("välj");


        userButton.setOnAction(event -> existedUserNavigation());







    }
    public void checkName(){
        Customer customer =  model.getCustomer();
        if (customer != null){
            userButton.setText("Förtsätt som : "+customer.getFirstName()+ " " + customer.getLastName());
        }
        else {
            userButton.setText("Ingen registrearad anvädare");

        }
    }

    public void setListener(WizardListener listener) {
        this.listener = listener;
    }

    public void navigateFront1(){
        progressBar1.setProgress(0.33);
        personalInfoPane.toFront();
    }
    public void navigateFront2(){
        progressBar2.setProgress(0.66);
        payPane.toFront();
    }
    public void navigateFront3(){
        progressBar3.setProgress(0.99);
        updateWizardInfo();
        checkName();
        Customer customer = model.getCustomer();
        userText.setText("Du genomför köpet som: " + customer.getFirstName() +" " + customer.getLastName());

        confirmationPane.toFront();
    }
    public void navigateBack1(){
        updateWizardInfo();
        checkName();
        deliveryPane.toFront();
    }
    public void navigateBack2(){
        progressBar1.setProgress(0.33);
        personalInfoPane.toFront();
    }
    public void navigateBack3(){
        progressBar2.setProgress(0.66);

        payPane.toFront();
    }
    public void navigateToMessage(){

        buyMessagePane.toFront();
    }
    public void navigateToMain(){


        listener.onWizardFinish();
    }
    public void backToCartNavigation(){
        listener.backToCart();

    }
    public void existedUserNavigation(){
        Customer customer = model.getCustomer();
        userText.setText("Du genomför köpet som: " + customer.getFirstName() +" " + customer.getLastName());
        confirmationPane.toFront();
    }
    @FXML
    private void navigateFront1Action(MouseEvent event) {
        navigateFront1();
    }
    @FXML

    private void navigateFront2Action(MouseEvent event) {
        navigateFront2();
    }
    @FXML
    private void navigateFront3Action(MouseEvent event) {

        navigateFront3();
    }

    @FXML
    private void navigateBack1Action(MouseEvent event) {
        navigateBack1();
    }
    @FXML

    private void navigateBack2Action(MouseEvent event) {
        navigateBack2();
    }
    @FXML
    private void navigateBack3Action(MouseEvent event) {
        navigateBack3();
    }
    @FXML

    private void navigateToMessageAction(ActionEvent event) {
        navigateToMessage();
    }
    @FXML
    private void navigateToMainAction(ActionEvent event) {
        navigateToMain();
    }
    @FXML
    private void backToCartNavigationAction(MouseEvent event) {
        backToCartNavigation();
    }
     public void updateWizardInfo() {

        CreditCard card = model.getCreditCard();
        Customer customer = model.getCustomer();

        card.setCardNumber(cardNumberText.getText());
        card.setHoldersName(cardNameText.getText());
        customer.setFirstName(firstNameText.getText());
        customer.setLastName(lastNameTExt.getText());
        customer.setEmail(eMailText.getText());
        customer.setMobilePhoneNumber(phoneText.getText());
        customer.setPostAddress(addressText.getText());
        customer.setPostCode(postCodeText.getText());

        String selectedValue = cardTypeCombo.getSelectionModel().getSelectedItem();
        card.setCardType(selectedValue);

        selectedValue = monthCombo.getSelectionModel().getSelectedItem();
        card.setValidMonth(Integer.parseInt(selectedValue));

        selectedValue = yearCombo.getSelectionModel().getSelectedItem();
        card.setValidYear(Integer.parseInt(selectedValue));

        card.setVerificationCode(Integer.parseInt(ccvText.getText()));


    }
    public void updateWizardPane() {

        CreditCard card = model.getCreditCard();
        Customer customer = model.getCustomer();
        cardNumberText.setText(card.getCardNumber());
        cardNameText.setText(card.getHoldersName());
        firstNameText.setText(customer.getFirstName());
        lastNameTExt.setText(customer.getLastName());
        eMailText.setText(customer.getEmail());
        phoneText.setText(customer.getMobilePhoneNumber());
        addressText.setText(customer.getPostAddress());
        postCodeText.setText(customer.getPostCode());

        cardTypeCombo.getSelectionModel().select(card.getCardType());
        monthCombo.getSelectionModel().select(""+card.getValidMonth());
        yearCombo.getSelectionModel().select(""+card.getValidYear());

        ccvText.setText(""+card.getVerificationCode());

    }
    /*void setValidCardInfo() {


        cardTypeCombo.getItems().addAll(model.getCardTypes());

        monthCombo.getItems().addAll(model.getMonths());

        yearCombo.getItems().addAll(model.getYears());

    }


     */
    public boolean isInt(TextField input, String message){
try {
    int number = Integer.parseInt(input.getText());

    return true;
}
    catch (NumberFormatException e){
    System.out.println("ogiltg data");
    return false;
}
    }

}
