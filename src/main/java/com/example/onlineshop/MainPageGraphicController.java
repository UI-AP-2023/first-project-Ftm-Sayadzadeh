package com.example.onlineshop;

import com.example.onlineshop.model.products.Pen;
import com.example.onlineshop.model.products.Product;
import com.example.onlineshop.model.user.Customer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.example.onlineshop.model.processes.* ;

import java.util.ArrayList;

public class MainPageGraphicController {
    @FXML
    private ImageView accountIcon;
    public static Customer customer;

    @FXML
    void accountIcon(MouseEvent event) throws Exception {
        MainPageGraphicController.customer = new Customer("ala" , "a@email.com" , "09137900843" , "12345678");
        Pen pen1 = new Pen("pen1",1000,1,"iran","Black");
        Pen pen2 = new Pen("pen2",2000,1,"iran","Black");
        ArrayList<Product> products = new ArrayList<>();
        products.add(pen1);
        products.add(pen2);
        Receipt receipt = new Receipt(products);
        MainPageGraphicController.customer.getShoppingHistory().add(receipt);
        if(customer == null){
            new SignUpAndLogInPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
        else{
            new CustomerPanelPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
    }
}
