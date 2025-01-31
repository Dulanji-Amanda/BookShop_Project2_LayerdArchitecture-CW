package org.example.stockverse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Appinitializer extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("STOCKVERSE");
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/stockverse/LoginForm.fxml")))));
//        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/example/stockverse/icon.png"))));
        stage.show();

    }
}
