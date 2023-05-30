package com.example.onlineshop;

import com.example.onlineshop.model.user.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerPanelPage extends Application {
    private Customer customer;

    public CustomerPanelPage(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomerPanelPage.class.getResource("customerPanelPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setTitle("Customer Panel Page");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
