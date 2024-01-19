module org.example.bibliogest_javihevia {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.bibliogest_javihevia to javafx.fxml;
    exports org.example.bibliogest_javihevia;
    exports controller;
    opens controller to javafx.fxml;
    exports model;
    opens model to javafx.fxml;
}