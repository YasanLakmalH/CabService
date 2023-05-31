package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class driverDashboardController implements Initializable {
    @FXML
    private Label driverIdLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private TableView<customerBookings> tableDriver;
    @FXML
    private TableColumn<customerBookings,Integer> bookingIdColumnDriver,userIdColumnDriver,vehicleNoColumnDriver;
    @FXML
    private TableColumn <customerBookings,String>
            contactNoColumnDriver,
            pickupLocationColumnDriver,destinationColumnDriver,dateColumnDriver,
            timeColumnDriver,vehicleTypeColumnDriver;

    @FXML
    private TableView <customerBookings> tableDriverCompleted;
    @FXML
    private TableColumn <customerBookings,Integer> bookingIdColumnDriverCompleted,userIdColumnDriverCompleted,vehicleNoColumnDriverCompleted;
    @FXML
    private TableColumn <customerBookings,String>
            contactNoColumnDriverCompleted,pickupLocationColumnDriverCompleted,
            destinationColumnDriverCompleted,dateColumnDriverCompleted,timeColumnDriverCompleted,
            vehicleTypeColumnDriverCompleted;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookingIdColumnDriver.setCellValueFactory(new PropertyValueFactory<customerBookings,Integer>("bookingId"));
        userIdColumnDriver.setCellValueFactory(new PropertyValueFactory<customerBookings,Integer>("userId"));
        contactNoColumnDriver.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("contactNo"));
        pickupLocationColumnDriver.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("pickupLocation"));
        destinationColumnDriver.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("destination"));
        dateColumnDriver.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("date"));
        timeColumnDriver.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("time"));
        vehicleNoColumnDriver.setCellValueFactory(new PropertyValueFactory<customerBookings,Integer>("vehicleNo"));
        vehicleTypeColumnDriver.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("vehicleType"));

        bookingIdColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<customerBookings,Integer>("bookingId"));
        userIdColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<customerBookings,Integer>("userId"));
        contactNoColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("contactNo"));
        pickupLocationColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("pickupLocation"));
        destinationColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("destination"));
        dateColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("date"));
        timeColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("time"));
        vehicleNoColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<customerBookings,Integer>("vehicleNo"));
        vehicleTypeColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("vehicleType"));

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "login.fxml",0,null);
            }
        });

    }
    public void setUserBookings()
    {
        try
        {
            DBUtils.loadUserActiveDriverRides(Integer.parseInt(driverIdLabel.getText()),tableDriver);
            DBUtils.loadUserCompletedDriverRides(Integer.parseInt(driverIdLabel.getText()),tableDriverCompleted);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    public void setUserInformation(int userId){
        driverIdLabel.setText(String.valueOf(userId));
    }

}

