module com.example.fitjourney {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fitjourney to javafx.fxml;
    exports com.example.fitjourney;
}