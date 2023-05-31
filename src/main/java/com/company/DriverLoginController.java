package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DriverLoginController implements Initializable {
    @FXML
    private Button previousButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button signInButton;
    @FXML
    private TextField driverNameText;
    @FXML
    private TextField passwordText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!driverNameText.getText().trim().isEmpty() && !passwordText.getText().trim().isEmpty()){
                    DBUtils.logInVerify(event, "DriverDashboard.fxml", driverNameText.getText(), passwordText.getText(),"driver");
                }else{
                    System.out.println("Fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up!");
                    alert.show();
                }
            }
        });
        previousButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"Login.fxml",0,null);
            }
        });
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }
}
