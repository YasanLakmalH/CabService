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

public class DriverDashboardController implements Initializable {
    @FXML
    private Label driverIdLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private TableView<ActiveBookings> tableDriver;
    @FXML
    private TableColumn<ActiveBookings,Integer> bookingIdColumnDriver,userIdColumnDriver,vehicleNoColumnDriver;
    @FXML
    private TableColumn <ActiveBookings,String>
            contactNoColumnDriver,
            pickupLocationColumnDriver,destinationColumnDriver,dateColumnDriver,
            timeColumnDriver,vehicleTypeColumnDriver;

    @FXML
    private TableView <ActiveBookings> tableDriverCompleted;
    @FXML
    private TableColumn <ActiveBookings,Integer> bookingIdColumnDriverCompleted,userIdColumnDriverCompleted,vehicleNoColumnDriverCompleted;
    @FXML
    private TableColumn <ActiveBookings,String>
            contactNoColumnDriverCompleted,pickupLocationColumnDriverCompleted,
            destinationColumnDriverCompleted,dateColumnDriverCompleted,timeColumnDriverCompleted,
            vehicleTypeColumnDriverCompleted;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookingIdColumnDriver.setCellValueFactory(new PropertyValueFactory<ActiveBookings,Integer>("bookingId"));
        userIdColumnDriver.setCellValueFactory(new PropertyValueFactory<ActiveBookings,Integer>("userId"));
        contactNoColumnDriver.setCellValueFactory(new PropertyValueFactory<ActiveBookings,String>("contactNo"));
        pickupLocationColumnDriver.setCellValueFactory(new PropertyValueFactory<ActiveBookings,String>("pickupLocation"));
        destinationColumnDriver.setCellValueFactory(new PropertyValueFactory<ActiveBookings,String>("destination"));
        dateColumnDriver.setCellValueFactory(new PropertyValueFactory<ActiveBookings,String>("date"));
        timeColumnDriver.setCellValueFactory(new PropertyValueFactory<ActiveBookings,String>("time"));
        vehicleNoColumnDriver.setCellValueFactory(new PropertyValueFactory<ActiveBookings,Integer>("vehicleNo"));
        vehicleTypeColumnDriver.setCellValueFactory(new PropertyValueFactory<ActiveBookings,String>("vehicleType"));

        bookingIdColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<ActiveBookings,Integer>("bookingId"));
        userIdColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<ActiveBookings,Integer>("userId"));
        contactNoColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<ActiveBookings,String>("contactNo"));
        pickupLocationColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<ActiveBookings,String>("pickupLocation"));
        destinationColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<ActiveBookings,String>("destination"));
        dateColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<ActiveBookings,String>("date"));
        timeColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<ActiveBookings,String>("time"));
        vehicleNoColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<ActiveBookings,Integer>("vehicleNo"));
        vehicleTypeColumnDriverCompleted.setCellValueFactory(new PropertyValueFactory<ActiveBookings,String>("vehicleType"));

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "Login.fxml",0,null);
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

