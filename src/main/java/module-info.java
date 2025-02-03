module org.example.stockverse {

    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;
    requires com.jfoenix;
    requires static lombok;
    requires java.sql;
    requires net.sf.jasperreports.core;
    requires java.desktop;
    requires javax.mail.api;
    requires javafx.graphics;

    opens org.example.stockverse to javafx.fxml;
    opens org.example.stockverse.controller to javafx.fxml;
    exports org.example.stockverse;
    exports org.example.stockverse.controller;
    opens org.example.stockverse.view.tdm;
}