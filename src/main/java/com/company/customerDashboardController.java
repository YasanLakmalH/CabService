package com.company;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class customerDashboardController implements Initializable {
//    Active Tab
    @FXML
    private Button logoutButton;
    @FXML
    private TableView <customerBookings> table;
    @FXML
    private TableColumn <customerBookings,Integer> bookingIdColumn,vehicleNoColumn;
    @FXML
    private TableColumn <customerBookings,String>
            contactNoColumn,
            pickupLocationColumn,destinationColumn,dateColumn,
            timeColumn,vehicleTypeColumn,statusColumn;
    @FXML
    private TableView <customerBookings> tableP;
    @FXML
    private TableColumn <customerBookings,Integer> bookingIdColumnP;
    @FXML
    private TableColumn <customerBookings,String>
            contactNoColumnP,pickupLocationColumnP,
            destinationColumnP,vehicleTypeColumnP,dateColumnP,timeColumnP,
            statusColumnP;
    @FXML
    private Button proceedButton;
    @FXML
    private TextField destinationNameText,contactNoText;
    @FXML
    private ChoiceBox<String> vehicleCategoryText,cityFrom,cityTo,hoursText,minutesText;
    @FXML
    private DatePicker  bookingDateText;
    @FXML
    private Label userIdLabel,perKmChargeLabel,totalChargeLabel;
    private final String[] vehicles = {"Car","Bike","Van"};
    private final String[] cities = {"Colombo","Kandy","Galle","Kurunegala"};
    private final String[] hours = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24"};
    private final String[] minutes = {
            "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
            "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
            "51", "52", "53", "54", "55", "56", "57", "58", "59", "00"};
    int pricePerKm;
    int totalCharge;
    public void updatePerKm(){
        if (cityTo.getValue().equals(cityFrom.getValue())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Invalid Destination");
            alert.show();
        }
        else if (cityTo.getValue() == "Colombo" && cityFrom.getValue() == "Kandy" || cityTo.getValue() == "Kandy" && cityFrom.getValue() == "Colombo" && vehicleCategoryText.getValue()=="Van"){
            pricePerKm = 100;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*122;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Colombo" && cityFrom.getValue() == "Galle" || cityTo.getValue() == "Galle" && cityFrom.getValue() == "Colombo" && vehicleCategoryText.getValue()=="Van"){
            pricePerKm = 30;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*107;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Colombo" && cityFrom.getValue() == "Kurunegala" || cityTo.getValue() == "Kurunegala" && cityFrom.getValue() == "Colombo" && vehicleCategoryText.getValue()=="Van"){
            pricePerKm = 60;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*84;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Galle" && cityFrom.getValue() == "Kandy" || cityTo.getValue() == "Kandy" && cityFrom.getValue() == "Galle" && vehicleCategoryText.getValue()=="Van"){
            pricePerKm = 130;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*147;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Galle" && cityFrom.getValue() == "Kurunegala" || cityTo.getValue() == "Kurunegala" && cityFrom.getValue() == "Galle" && vehicleCategoryText.getValue()=="Van"){
            pricePerKm = 160;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*200;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Kurunegala" && cityFrom.getValue() == "Kandy" || cityTo.getValue() == "Kandy" && cityFrom.getValue() == "Kurunegala" && vehicleCategoryText.getValue()=="Van"){
            pricePerKm = 40;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*15;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Colombo" && cityFrom.getValue() == "Kandy" || cityTo.getValue() == "Kandy" && cityFrom.getValue() == "Colombo" && vehicleCategoryText.getValue()=="Car"){
            pricePerKm = 90;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*122;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Colombo" && cityFrom.getValue() == "Galle" || cityTo.getValue() == "Galle" && cityFrom.getValue() == "Colombo" && vehicleCategoryText.getValue() == "Car"){
            pricePerKm = 20;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*107;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Colombo" && cityFrom.getValue() == "Kurunegala" || cityTo.getValue() == "Kurunegala" && cityFrom.getValue() == "Colombo" && vehicleCategoryText.getValue() == "Car"){
            pricePerKm = 50;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*84;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Galle" && cityFrom.getValue() == "Kandy" || cityTo.getValue() == "Kandy" && cityFrom.getValue() == "Galle" && vehicleCategoryText.getValue() == "Car"){
            pricePerKm = 120;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*147;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Galle" && cityFrom.getValue() == "Kurunegala" || cityTo.getValue() == "Kurunegala" && cityFrom.getValue() == "Galle" && vehicleCategoryText.getValue() == "Car"){
            pricePerKm = 150;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*200;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Kurunegala" && cityFrom.getValue() == "Kandy" || cityTo.getValue() == "Kandy" && cityFrom.getValue() == "Kurunegala" && vehicleCategoryText.getValue()=="Car"){
            pricePerKm = 30;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*15;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Colombo" && cityFrom.getValue() == "Kandy" || cityTo.getValue() == "Kandy" && cityFrom.getValue() == "Colombo" && vehicleCategoryText.getValue()=="Bike"){
            pricePerKm = 80;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*122;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Colombo" && cityFrom.getValue() == "Galle" || cityTo.getValue() == "Galle" && cityFrom.getValue() == "Colombo" && vehicleCategoryText.getValue()=="Bike"){
            pricePerKm = 10;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*107;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Colombo" && cityFrom.getValue() == "Kurunegala" || cityTo.getValue() == "Kurunegala" && cityFrom.getValue() == "Colombo" && vehicleCategoryText.getValue()=="Bike"){
            pricePerKm = 40;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*84;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Galle" && cityFrom.getValue() == "Kandy" || cityTo.getValue() == "Kandy" && cityFrom.getValue() == "Galle" && vehicleCategoryText.getValue()=="Bike"){
            pricePerKm = 140;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*147;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Galle" && cityFrom.getValue() == "Kurunegala" || cityTo.getValue() == "Kurunegala" && cityFrom.getValue() == "Galle" && vehicleCategoryText.getValue()=="Bike"){
            pricePerKm = 20;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*200;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }
        else if (cityTo.getValue() == "Kurunegala" && cityFrom.getValue() == "Kandy" || cityTo.getValue() == "Kandy" && cityFrom.getValue() == "Kurunegala" && vehicleCategoryText.getValue()=="Bike"){
            pricePerKm = 70;
            perKmChargeLabel.setText(String.valueOf(pricePerKm));
            totalCharge = pricePerKm*15;
            totalChargeLabel.setText(String.valueOf(totalCharge));
        }else {
            ;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cityTo.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Update the value based on the new selection
            updatePerKm();
        });
        cityFrom.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Update the value based on the new selection
            updatePerKm();
        });
        vehicleCategoryText.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Update the value based on the new selection
            updatePerKm();
        });

        vehicleCategoryText.getItems().addAll(vehicles);
        cityFrom.getItems().addAll(cities);
        cityTo.getItems().addAll(cities);
        hoursText.getItems().addAll(hours);
        minutesText.getItems().addAll(minutes);

        bookingIdColumn.setCellValueFactory(new PropertyValueFactory<customerBookings,Integer>("bookingId"));
        contactNoColumn.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("contactNo"));
        pickupLocationColumn.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("pickupLocation"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("destination"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("time"));
        vehicleNoColumn.setCellValueFactory(new PropertyValueFactory<customerBookings,Integer>("vehicleNo"));
        vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("vehicleType"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("status"));

        bookingIdColumnP.setCellValueFactory(new PropertyValueFactory<customerBookings,Integer>("bookingId"));
        contactNoColumnP.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("contactNo"));
        pickupLocationColumnP.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("pickupLocation"));
        destinationColumnP.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("destination"));
        vehicleTypeColumnP.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("vehicleType"));
        dateColumnP.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("date"));
        timeColumnP.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("time"));
        statusColumnP.setCellValueFactory(new PropertyValueFactory<customerBookings,String>("status"));

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "login.fxml",0,null);
            }
        });
        proceedButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String time = hoursText.getValue()+":"+minutesText.getValue();
                if(DBUtils.checkVehiclesAvailability(vehicleCategoryText.getValue(),String.valueOf(bookingDateText.getValue())) && DBUtils.checkDriversAvailability(String.valueOf(bookingDateText.getValue()))){
                    DBUtils.addNewBooking(Integer.parseInt(userIdLabel.getText()),
                            Integer.parseInt(contactNoText.getText()),
                            cityFrom.getValue(),cityTo.getValue(),
                            vehicleCategoryText.getValue(),
                            String.valueOf(bookingDateText.getValue()),
                            time,
                            "pending");
                    DBUtils.changeScene(event,"customerDashboard.fxml",Integer.parseInt(userIdLabel.getText()),"passenger");
                }
                DBUtils.addNewBooking(Integer.parseInt(userIdLabel.getText()),
                        Integer.parseInt(contactNoText.getText()),
                        cityFrom.getValue(),cityTo.getValue(),
                        vehicleCategoryText.getValue(),
                        String.valueOf(bookingDateText.getValue()),
                        time,
                        "pending");
                DBUtils.changeScene(event,"customerDashboard.fxml",Integer.parseInt(userIdLabel.getText()),"passenger");
            }
        });
    }
    public void setUserBookings()
    {
        DBUtils.loadUserActiveBookingsData(Integer.parseInt(userIdLabel.getText()),table);
        DBUtils.loadUserPendingBookingsData(Integer.parseInt(userIdLabel.getText()),tableP);
    }
    public void setUserInformation(int userId){
        userIdLabel.setText(String.valueOf(userId));
    }

}
