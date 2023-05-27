package com.example.onlineshop;

import com.example.onlineshop.model.user.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignUpAndLogInPage extends Application {
    private Customer customer;

    public SignUpAndLogInPage(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(SignUpAndLogInPage.class.getResource("signUpAndLogInPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        ((SignUPAndLogInPageGraphicController)fxmlLoader.getController()).customer = customer;
        stage.setTitle("Sign Up Page");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
