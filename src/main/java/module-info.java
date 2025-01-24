module org.example.stockverse {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.stockverse to javafx.fxml;
    exports org.example.stockverse;
}