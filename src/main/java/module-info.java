module com.example.comp350 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.comp350 to javafx.fxml;
    exports com.example.comp350;
}