package com.example.onlineshop;

import com.example.onlineshop.model.products.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ShoppingCartPage extends Application {
    private ArrayList<Product> productList;
    ShoppingCartPage(ArrayList<Product> products){
        this.productList = products;
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(SignUpAndLogInPage.class.getResource("shoppingCartPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        ((ShoppingCartPageGraphicController)fxmlLoader.getController()).products = productList;
        ((ShoppingCartPageGraphicController)fxmlLoader.getController()).setList();
        stage.setTitle("Shopping Cart Page");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
