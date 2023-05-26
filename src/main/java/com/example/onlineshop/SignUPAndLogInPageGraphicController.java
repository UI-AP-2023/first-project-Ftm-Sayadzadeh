package com.example.onlineshop;

import com.example.onlineshop.controller.AdminController;
import com.example.onlineshop.controller.CustomerController;
import com.example.onlineshop.view.AdminPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignUPAndLogInPageGraphicController {
    @FXML
    private ImageView backHome;

    @FXML
    private Button goToSignUpPage;

    @FXML
    private Button logInBtn;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    void backHome(MouseEvent event) throws Exception {
        new MainPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    void goToSignUpPage(MouseEvent event) throws Exception {
        new SignUpFormPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    void logInToPanel(ActionEvent event) throws Exception {
        AdminController adminController = new AdminController();
        boolean isAdmin = adminController.logIn(usernameField.getText(), passwordField.getText());
        if (isAdmin) {
            AdminPanel adminPanel = new AdminPanel();
            adminPanel.adminCommandPage();
        } else {
            CustomerController customerController = new CustomerController();
            boolean existCustomer = customerController.logIn(usernameField.getText(), passwordField.getText());
            if (existCustomer) {
                new CustomerPanelPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("NOT FOUND!");
                alert.setHeaderText("NOT FOUND USER!");
                alert.setContentText("Enter your info correctly and if you don't have an account , you should sign up first!");
                alert.showAndWait();
            }
        }
    }
}
