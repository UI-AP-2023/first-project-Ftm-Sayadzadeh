package com.example.onlineshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShoppingHistoryPage extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingHistoryPage.class.getResource("shoppingHistoryPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setTitle("shopping history Page");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
