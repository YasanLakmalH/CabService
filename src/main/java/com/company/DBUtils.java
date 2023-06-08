package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;
import static javafx.fxml.FXMLLoader.load;
public class DBUtils {
    static double x = 0;
    static double y = 0;

    public static void changeScene(ActionEvent event, String fxmlFile, int userId, String userType) {
        Parent root = null;
        if (userId != 0 && userType.equals("passenger")) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                CustomerDashboardController dashboardController = loader.getController();
                dashboardController.setUserInformation(userId);
                dashboardController.setUserBookings();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (userId != 0 && userType.equals("driver")) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                DriverDashboardController dashboardController = loader.getController();
                dashboardController.setUserInformation(userId);
                dashboardController.setUserBookings();
            } catch (IOException e) {
                throw new RuntimeException(e);

            }
        } else {
            try {
                root = load(DBUtils.class.getResource(fxmlFile));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.centerOnScreen();
        root.setOnMousePressed(MouseEvent -> {
            x = MouseEvent.getSceneX();
            y = MouseEvent.getSceneY();
        });
        root.setOnMouseClicked(MouseEvent -> {
            stage.setX(MouseEvent.getScreenX() - x);
            stage.setY(MouseEvent.getScreenY() - y);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void signUpUser(ActionEvent event, Long nicText, String firstNameText, String lastNameText, String mobileText, String userEmail, String cityText, String userName, String userPassword) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExist.setString(1, userName);
            resultSet = psCheckUserExist.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exist!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("User already exist!");
                alert.setContentText("you cannot use this username.");
                alert.show();
            } else {
                int newUserId = 1;
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT userId FROM users;");
                while (resultSet.next()) {
                    newUserId = resultSet.getInt("userId") + 1;
                }
                psInsert = connection.prepareStatement("INSERT INTO userdetails VALUES(?, ?, ?, ?, ?, ?, ?)");
                psInsert.setInt(1, newUserId);
                psInsert.setLong(2, nicText);
                psInsert.setString(3, firstNameText);
                psInsert.setString(4, lastNameText);
                psInsert.setString(5, mobileText);
                psInsert.setString(6, userEmail);
                psInsert.setString(7, cityText);
                psInsert.executeUpdate();

                psInsert = connection.prepareStatement("INSERT INTO users VALUES(?, ?, ?)");
                psInsert.setInt(1, newUserId);
                psInsert.setString(2, userName);
                psInsert.setString(3, userPassword);
                psInsert.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Successfully");
                alert.setContentText("Account created.");
                alert.show();

                changeScene(event, "CustomerLogin.fxml", 0, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {

                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExist != null) {
                try {
                    psCheckUserExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void login(ActionEvent event, String fxmlFile) {
        changeScene(event, fxmlFile, 0, null);

    }

    public static void logInVerify(ActionEvent event, String fxmlFile, String userName, String userPassword, String userType) {
        Connection connection = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;
        String receivedPassword = null;
        int userId = 0;

        try {
            if (userType.equals("passenger")) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
                psCheckUserExist = connection.prepareStatement("SELECT * FROM users WHERE username=?");
                psCheckUserExist.setString(1, userName);
                resultSet = psCheckUserExist.executeQuery();
            } else {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
                psCheckUserExist = connection.prepareStatement("SELECT * FROM drivers WHERE drivername=?");
                psCheckUserExist.setString(1, userName);
                resultSet = psCheckUserExist.executeQuery();
            }

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Account does not exist");
                alert.show();
            } else {
                if(userType.equals("passenger")){
                    while (resultSet.next()) {
                        receivedPassword = resultSet.getString("password");
                        userId = resultSet.getInt("userId");
                        System.out.println("userID:" + userId);
                    }
                }else{
                    while (resultSet.next()) {
                        receivedPassword = resultSet.getString("password");
                        userId = resultSet.getInt("driverId");
                        System.out.println("userID:" + userId);
                    }
                }
                assert receivedPassword != null;
                if (receivedPassword.equals(userPassword)) {
                    System.out.println("Success");
                    if (userType.equals("passenger")) {
                        changeScene(event, fxmlFile, userId, "passenger");
                    } else {
                        changeScene(event, fxmlFile, userId, "driver");
                    }
                } else {
                    System.out.println("Password doesn't match!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Password doesn't match");
                    alert.show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExist != null) {
                try {
                    psCheckUserExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void loadUserActiveBookingsData(int userId, TableView<ActiveBookings> tableName) {
        Connection connection = null;
        PreparedStatement psGetUserBookingsData = null;
        ResultSet userBookingsData = null;
        ObservableList<ActiveBookings> list = FXCollections.observableArrayList();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psGetUserBookingsData = connection.prepareStatement("SELECT  " +
                    "A.bookingId,  " +
                    "A.userId,  " +
                    "A.status, "+
                    "B.contactNo,  " +
                    "B.pickupLocation,  " +
                    "B.destination,  " +
                    "B.bookingDate,  " +
                    "B.bookingTime,  " +
                    "C.vehicleNo, " +
                    "C.vehicleType  " +
                    "FROM bookingmaster A  " +
                    "INNER JOIN userbookings B  " +
                    "ON A.bookingId = B.bookingId " +
                    "INNER JOIN vehicles C " +
                    "ON A.vehicleId = C.vehicleId " +
                    "WHERE A.userId=? and A.status=?;");
            psGetUserBookingsData.setInt(1, userId);
            psGetUserBookingsData.setString(2, "active");
            userBookingsData = psGetUserBookingsData.executeQuery();

            while (userBookingsData.next()) {

                int bookingId = userBookingsData.getInt("bookingId");
                String contactNo = userBookingsData.getString("contactNo");
                String pickupLocation = userBookingsData.getString("pickupLocation");
                String destination = userBookingsData.getString("destination");
                String bookingDate = userBookingsData.getString("bookingDate");
                String bookingTime = userBookingsData.getString("bookingTime");
                String vehicleNo = userBookingsData.getString("vehicleNo");
                String vehicleType = userBookingsData.getString("vehicleType");
                String status = userBookingsData.getString("status");
                list.add(
                        new ActiveBookings(
                                userId,
                                bookingId,
                                contactNo,
                                vehicleNo,
                                vehicleType,
                                pickupLocation,
                                destination,
                                bookingDate,
                                bookingTime,
                                status)
                );
                tableName.setItems(list);
            }

        } catch (SQLException e) {
            System.out.println("loadUserActiveBookingData SQL Exception:" + e.getMessage());
        }finally {
            if (userBookingsData != null) {
                try {
                    userBookingsData.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psGetUserBookingsData != null) {
                try {
                    psGetUserBookingsData.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void loadUserPendingBookingsData(int userId, TableView<Bookings> tableName) {
        Connection connection = null;
        PreparedStatement psGetUserBookingsData = null;
        ResultSet userPendingBookingsData = null;
        ObservableList<Bookings> list = FXCollections.observableArrayList();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psGetUserBookingsData = connection.prepareStatement("SELECT * From userbookings "+
                    "WHERE userId=? AND status=?");
            psGetUserBookingsData.setInt(1, userId);
            psGetUserBookingsData.setString(2, "pending");
            userPendingBookingsData = psGetUserBookingsData.executeQuery();

            while (userPendingBookingsData.next()) {

                int bookingId = userPendingBookingsData.getInt("bookingId");
                String contactNo = userPendingBookingsData.getString("contactNo");
                String pickupLocation = userPendingBookingsData.getString("pickupLocation");
                String destination = userPendingBookingsData.getString("destination");
                String vehicleType = userPendingBookingsData.getString("vehicleType");
                String bookingDate = userPendingBookingsData.getString("bookingDate");
                String bookingTime = userPendingBookingsData.getString("bookingTime");
                String status = userPendingBookingsData.getString("status");
                list.add(
                        new Bookings(
                                userId,
                                bookingId,
                                contactNo,
                                pickupLocation,
                                destination,
                                vehicleType,
                                bookingDate,
                                bookingTime,
                                status)
                );
                tableName.setItems(list);
            }

        } catch (SQLException e) {
            System.out.println("loadUserPendingBookingData SQL Exception:" + e.getMessage());
        }finally {
            if (userPendingBookingsData != null) {
                try {
                    userPendingBookingsData.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psGetUserBookingsData != null) {
                try {
                    psGetUserBookingsData.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean checkVehiclesAvailability(String vehicleType, String bookingDate){
        Connection connection = null;
        PreparedStatement psCheckVehicleAvailability = null;
        ResultSet resultSet = null;
        boolean vehicleAvailable = false;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psCheckVehicleAvailability = connection.prepareStatement(
                    "SELECT bookingmaster.vehicleId " +
                            "FROM bookingmaster " +
                            "INNER JOIN userbookings " +
                            "ON bookingmaster.bookingId = userbookings.bookingId "+
                            "INNER JOIN vehicles "+
                            "ON vehicles.vehicleId = bookingmaster.vehicleId "+
                            "WHERE userbookings.bookingDate <>? " +
                            "AND vehicles.vehicleType=? "+
                            "UNION " +
                            "SELECT vehicleId " +
                            "FROM vehicles " +
                            "WHERE vehicleAvailability <> 0 " +
                            "AND vehicleType=? "+
                            "LIMIT 1;");
            psCheckVehicleAvailability.setString(1, bookingDate);
            psCheckVehicleAvailability.setString(2,vehicleType);
            psCheckVehicleAvailability.setString(3,vehicleType);
            resultSet = psCheckVehicleAvailability.executeQuery();
            if(resultSet.next()){
                vehicleAvailable = true;
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Can't proceed");
                alert.setContentText("Sorry "+vehicleType+" unavailable this moment.");
                alert.show();

            }
        }
        catch(SQLException e){
            System.out.println("checkVehicleAvailability SQL Exception:"+e.getMessage());
        }finally {
            if (resultSet != null) {

                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckVehicleAvailability != null) {
                try {
                    psCheckVehicleAvailability.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return vehicleAvailable;
    }
    public static boolean checkDriversAvailability(String date) {
        Connection connection = null;
        PreparedStatement psCheckDriverAvailability = null;
        ResultSet resultSet = null;
        boolean driverAvailable = false;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psCheckDriverAvailability = connection.prepareStatement(
                    "SELECT driverId " +
                            "FROM bookingmaster " +
                            "INNER JOIN userbookings "+
                            "ON bookingmaster.bookingId = userbookings.bookingId "+
                            "WHERE userbookings.date <>? " +
                            "UNION " +
                            "SELECT driverId " +
                            "FROM drivers " +
                            "WHERE driverAvailability <> 0 " +
                            "LIMIT 1;");
            psCheckDriverAvailability.setString(1, date);
            resultSet = psCheckDriverAvailability.executeQuery();
            if (resultSet.next()) {
                driverAvailable = true;
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Can't proceed");
                alert.setContentText("Sorry drivers unavailable for that date.");
                alert.show();

            }
        } catch (SQLException e) {
            System.out.println("checkDriverAvailability SQL Exception:" + e.getMessage());
        }finally {
            if (resultSet != null) {

                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckDriverAvailability != null) {
                try {
                    psCheckDriverAvailability.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return driverAvailable;
    }

    public static int getBookingId() {
        Connection connection = null;
        Statement getLatestBookingId = null;
        ResultSet resultSet = null;
        int bookingId = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            getLatestBookingId = connection.createStatement();
            String sql = "SELECT bookingId FROM userBookings ORDER BY bookingId DESC LIMIT 1;";
            resultSet = getLatestBookingId.executeQuery(sql);
            while (resultSet.next()) {
                bookingId = resultSet.getInt("bookingId") + 1;
            }
        } catch (SQLException e) {
            System.out.println("getBookingId " + e.getMessage());
        }finally {
            if (resultSet != null) {

                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (getLatestBookingId != null) {
                try {
                    getLatestBookingId.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return bookingId;
    }

    public static void addNewBooking(
            Integer userId,
            Integer contactNo,
            String pickupLocation,
            String destination,
            String vehicleType,
            String bookingDate,
            String bookingTime,
            String status) {
        Connection connection = null;
        PreparedStatement psInsertUserBookingData = null;
        int bookingId = getBookingId();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psInsertUserBookingData = connection.prepareStatement("INSERT INTO userBookings VALUES(?,?,?,?,?,?,?,?,?)");
            psInsertUserBookingData.setInt(1, bookingId);
            psInsertUserBookingData.setInt(2, userId);
            psInsertUserBookingData.setInt(3, contactNo);
            psInsertUserBookingData.setString(4, pickupLocation);
            psInsertUserBookingData.setString(5, destination);
            psInsertUserBookingData.setString(6,vehicleType);
            psInsertUserBookingData.setString(7, bookingDate);
            psInsertUserBookingData.setString(8, bookingTime);
            psInsertUserBookingData.setString(9, status);
            psInsertUserBookingData.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Successful");
            alert.show();
        } catch (SQLException e) {
            System.out.println("addNewBooking SQL Exception:" + e.getMessage());
        }finally {
            if (psInsertUserBookingData != null) {

                try {
                    psInsertUserBookingData.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    //    Driver
    public static void loadUserActiveDriverRides(int driverId, TableView<ActiveBookings> tableName) {
        Connection connection = null;
        PreparedStatement psGetUserBookingsData = null;
        ResultSet userBookingsData = null;
        ObservableList<ActiveBookings> list = FXCollections.observableArrayList();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psGetUserBookingsData = connection.prepareStatement("SELECT  " +
                    "A.bookingId,  " +
                    "A.userId,  " +
                    "A.status, "+
                    "B.contactNo,  " +
                    "B.pickupLocation,  " +
                    "B.destination,  " +
                    "B.date,  " +
                    "B.time,  " +
                    "C.vehicleNo, " +
                    "C.vehicleType  " +
                    "FROM bookingmaster A  " +
                    "INNER JOIN userbookings B  " +
                    "ON A.bookingId = B.bookingId " +
                    "INNER JOIN vehicles C " +
                    "ON A.vehicleId = C.vehicleId " +
                    "WHERE A.driverId=? and A.status=?;");

            psGetUserBookingsData.setInt(1, driverId);
            psGetUserBookingsData.setString(2, "active");
            userBookingsData = psGetUserBookingsData.executeQuery();

            while (userBookingsData.next()) {
                int userId = userBookingsData.getInt("userId");
                int bookingId = userBookingsData.getInt("bookingId");
                String contactNo = userBookingsData.getString("contactNo");
                String pickupLocation = userBookingsData.getString("pickupLocation");
                String destination = userBookingsData.getString("destination");
                String bookingDate = userBookingsData.getString("bookingDate");
                String bookingTime = userBookingsData.getString("bookingTime");
                String vehicleNo = userBookingsData.getString("vehicleNo");
                String vehicleType = userBookingsData.getString("vehicleType");
                String status = userBookingsData.getString("status");
                list.add(
                        new ActiveBookings(
                                userId,
                                bookingId,
                                contactNo,
                                vehicleNo,
                                vehicleType,
                                pickupLocation,
                                destination,
                                bookingDate,
                                bookingTime,
                                status)
                );
                tableName.setItems(list);
            }

        } catch (SQLException e) {
            System.out.println("loadUserActiveBookingData SQL Exception:" + e.getMessage());
        }finally {
            if (userBookingsData != null) {
                try {
                    userBookingsData.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psGetUserBookingsData != null) {
                try {
                    psGetUserBookingsData.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void loadUserCompletedDriverRides(int driverId, TableView<ActiveBookings> tableName) {
        Connection connection = null;
        PreparedStatement psGetUserBookingsData = null;
        ResultSet userBookingsData = null;
        ObservableList<ActiveBookings> list = FXCollections.observableArrayList();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psGetUserBookingsData = connection.prepareStatement("SELECT  " +
                    "A.bookingId,  " +
                    "A.userId,  " +
                    "A.status, "+
                    "B.contactNo,  " +
                    "B.pickupLocation,  " +
                    "B.destination,  " +
                    "B.date,  " +
                    "B.time,  " +
                    "C.vehicleNo, " +
                    "C.vehicleType  " +
                    "FROM bookingmaster A  " +
                    "INNER JOIN userbookings B  " +
                    "ON A.bookingId = B.bookingId " +
                    "INNER JOIN vehicles C " +
                    "ON A.vehicleId = C.vehicleId " +
                    "WHERE A.driverId=? and A.status=?;");

            psGetUserBookingsData.setInt(1, driverId);
            psGetUserBookingsData.setString(2, "completed");
            userBookingsData = psGetUserBookingsData.executeQuery();

            while (userBookingsData.next()) {
                int userId = userBookingsData.getInt("userId");
                int bookingId = userBookingsData.getInt("bookingId");
                String contactNo = userBookingsData.getString("contactNo");
                String pickupLocation = userBookingsData.getString("pickupLocation");
                String destination = userBookingsData.getString("destination");
                String bookingDate = userBookingsData.getString("bookingDate");
                String bookingTime = userBookingsData.getString("bookingTime");
                String vehicleNo = userBookingsData.getString("vehicleNo");
                String vehicleType = userBookingsData.getString("vehicleType");
                String status = userBookingsData.getString("status");
                list.add(
                        new ActiveBookings(
                                userId,
                                bookingId,
                                contactNo,
                                vehicleNo,
                                vehicleType,
                                pickupLocation,
                                destination,
                                bookingDate,
                                bookingTime,
                                status)
                );
                tableName.setItems(list);
            }

        } catch (SQLException e) {
            System.out.println("loadUserActiveBookingData SQL Exception:" + e.getMessage());
        }finally {
            if (userBookingsData != null) {
                try {
                    userBookingsData.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psGetUserBookingsData != null) {
                try {
                    psGetUserBookingsData.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}




