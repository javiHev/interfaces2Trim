module com.example.fitjourney {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;


    opens com.example.fitjourney to javafx.fxml;
    exports com.example.fitjourney;
}