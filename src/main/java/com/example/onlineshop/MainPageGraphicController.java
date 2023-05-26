package com.example.onlineshop;

import com.example.onlineshop.model.user.Customer;
import com.example.onlineshop.view.LogInPage;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainPageGraphicController {
    @FXML
    private ImageView accountIcon;
    public static Customer customer;

    @FXML
    void accountIcon(MouseEvent event) throws Exception {
        if(customer == null){
            new SignUpAndLogInPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
        else{
            new CustomerPanelPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
    }
}
