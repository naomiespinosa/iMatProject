package iMatProject;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;
import iMatProject.iMatMainWindowController;

import java.io.IOException;

public class Wizard extends AnchorPane {


    @FXML
    private AnchorPane personalInfoPane;

    @FXML
    private RadioButton homeRadioButton;

    @FXML
    private RadioButton takeRadioButton;

    @FXML
    private ImageView backArrow2;

    @FXML
    private ImageView backArrow3;

    @FXML
    private AnchorPane buyMessagePane;

    @FXML
    private AnchorPane deliveryPane;

    @FXML
    private TextField firstNameText;

    @FXML
    private TextField postCodeText;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField lastNameTExt;

    @FXML
    private TextField addressText;

    @FXML
    private TextField eMailText;

    @FXML
    private ImageView backArrow32;

    @FXML
    private ImageView backArrow31;

    @FXML
    private AnchorPane payPane;

    @FXML
    private Label cardTypeText;

    @FXML
    public TextField cardNameText;

    @FXML
    public TextField ccvText;

    @FXML
    public TextField cardNumberText;

    @FXML
    public ComboBox<String> cardTypeCombo;

    @FXML
    public ComboBox<String> yearCombo;

    @FXML
    public ComboBox<String> monthCombo;

    @FXML
    private ImageView backArrow1;

    @FXML
    private ImageView frontArrow1;

    @FXML
    private AnchorPane confirmationPane;

    @FXML
    private Button backToMainButton;

    @FXML
    private Button confirmButton;

    @FXML
    private ImageView backArrow;



    private Model model = Model.getInstance();
    private iMatMainWindowController controller;

    public Wizard(){
        controller = new iMatMainWindowController();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Wizard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void navigateFront1(){
        updateWizardpane();
    personalInfoPane.toFront();
    }
    public void navigateFront2(){
        payPane.toFront();
    }
    public void navigateFront3(){
        updateWizard();

        confirmationPane.toFront();
    }
    public void navigateBack1(){

        deliveryPane.toFront();
    }
    public void navigateBack2(){
        deliveryPane.toFront();
    }
    public void navigateBack3(){

        payPane.toFront();
    }
    public void navigateToMessage(){

        buyMessagePane.toFront();
    }
    public void navigateToMain(){
        controller.wizardAnchorPane.toBack();
        controller.productFlowPane.toFront();
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
     public void updateWizard() {

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
    public void updateWizardpane() {

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

      //  purchasesLabel.setText(model.getNumberOfOrders()+ " tidigare inköp hos iMat");

    }

}
