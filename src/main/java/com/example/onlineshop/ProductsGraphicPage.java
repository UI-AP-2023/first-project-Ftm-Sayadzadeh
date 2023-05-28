package com.example.onlineshop;

import com.example.onlineshop.model.products.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ProductsGraphicPage extends Application {
    private ArrayList<Product> productList;
    ProductsGraphicPage(ArrayList<Product> products){
        this.productList = products;
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ProductsGraphicPage.class.getResource("productsPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        ((ProductsPageGraphicController)fxmlLoader.getController()).products = productList;
        ((ProductsPageGraphicController)fxmlLoader.getController()).setList();

        stage.setTitle("Products Page");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
