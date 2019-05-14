package iMatProject;


import iMatProject.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
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
    private AnchorPane shippingPane;

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
    private Button confirmButton;

    @FXML
    private ImageView backArrow;

    @FXML
    private ImageView frontArrow;


    private Model model = Model.getInstance();

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
}
