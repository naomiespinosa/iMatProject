package iMatProject;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    private TextField cardNameText;

    @FXML
    private TextField ccvText;

    @FXML
    private TextField cardNumberText;

    @FXML
    private ComboBox<String> cardTypeCombo;

    @FXML
    private ComboBox<Integer> yearCombo;

    @FXML
    private ComboBox<Integer> monthCombo;

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

    deliveryPane.toFront();
    }
    public void navigateFront2(){
        payPane.toFront();
    }
    public void navigateFront3(){

        confirmationPane.toFront();
    }
    public void navigateBack1(){

        personalInfoPane.toFront();
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
       // controller.productFlowPane.toFront();
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


}
