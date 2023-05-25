package com.example.onlineshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignUpAndLogInPage extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(SignUpAndLogInPage.class.getResource("signUpAndLogInPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setTitle("Sign Up Page");
        stage.setScene(scene);
        stage.show();
    }
}
