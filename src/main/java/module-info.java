module com.cabservice.cabservice1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.company to javafx.fxml;
    exports com.company;
}