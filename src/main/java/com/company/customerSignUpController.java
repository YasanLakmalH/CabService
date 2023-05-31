package com.company;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
public class customerSignUpController implements Initializable {
    @FXML
    private Button createAccountButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField nicText;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField mobileText;
    @FXML
    private TextField userEmailText;
    @FXML
    private TextField cityText;
    @FXML
    private TextField userNameText;
    @FXML
    private TextField userPasswordText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createAccountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.signUpUser(event, Long.parseLong(nicText.getText()),
                        firstNameText.getText(),
                        lastNameText.getText(),
                        mobileText.getText(),
                        userEmailText.getText(),
                        cityText.getText(),
                        userNameText.getText(),
                        userPasswordText.getText());
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                DBUtils.changeScene(event,"customerLogin.fxml",0,null);
            }
        });
    }
}
