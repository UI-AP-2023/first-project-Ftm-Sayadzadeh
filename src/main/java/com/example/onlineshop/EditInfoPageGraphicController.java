package com.example.onlineshop;

import com.example.onlineshop.controller.CustomerController;
import com.example.onlineshop.exceptions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditInfoPageGraphicController implements Initializable {
    private boolean passwordChecker;
    private boolean emailChecker;
    private boolean phoneChecker;
    @FXML
    private ImageView backHome;

    @FXML
    private ImageView backPanel;

    @FXML
    private TextField editEmail;

    @FXML
    private TextField editPassword;

    @FXML
    private TextField editPhone;

    @FXML
    private Label emailAlert;

    @FXML
    private Label passwordAlert;

    @FXML
    private Label phoneAlert;

    @FXML
    private Button submitInfo;

    @FXML
    private Label username_lbl;

    @FXML
    void backHome(MouseEvent event) throws Exception {
        new MainPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    void backPanel(MouseEvent event) throws Exception {
        new CustomerPanelPage(MainPageGraphicController.customer).start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    void editEmail(ActionEvent event) {
        try {
            CustomerController customerController = new CustomerController();
            Pattern emailPattern = Pattern.compile("^[\\w-._%+0-9]+@[\\w0-9.-]+\\.com$");
            customerController.checkEmail(editEmail.getText());
            Matcher emailMatcher = emailPattern.matcher(editEmail.getText());
            if (!emailMatcher.find())
                throw new InvalidEmail();
            emailAlert.setText("");
            emailChecker = true;
        }
        catch (DuplicateEmail e) {
            if(MainPageGraphicController.customer.getEmail().equals(editEmail.getText())){
                emailAlert.setText("");
                emailChecker = true;
            }
            else {
                emailAlert.setText("* duplicate email...");
            }
        } catch (InvalidEmail e) {
            emailAlert.setText("* invalid email...");
        }
    }

    @FXML
    void editPassword(ActionEvent event) {
        try{
            Pattern passwordPattern = Pattern.compile("^\\S{8,}$");
            Matcher passwordMather = passwordPattern.matcher(editPassword.getText());
            if(!passwordMather.find())
                throw new InvalidPassword();
            passwordAlert.setText("");
            passwordChecker = true;
        } catch (InvalidPassword e) {
            passwordAlert.setText("* should contain 8 char without space!");
        }
    }

    @FXML
    void editPhone(ActionEvent event) {
        try{
            CustomerController customerController = new CustomerController();
            Pattern phoneNumberPattern = Pattern.compile("^09\\d{9}$");
            customerController.checkPhoneNumber(editPhone.getText());
            Matcher phoneNumberMather = phoneNumberPattern.matcher(editPhone.getText());
            if(!phoneNumberMather.find())
                throw new InvalidPhone();
            phoneAlert.setText("");
            phoneChecker = true;
        } catch (InvalidPhone e) {
            phoneAlert.setText("* invalid phone...");
        } catch (DuplicatePhone e) {
            if(MainPageGraphicController.customer.getPhoneNumber().equals(editPhone.getText())){
                phoneAlert.setText("");
                phoneChecker = true;
            }
            else {
                phoneAlert.setText("* duplicate phone...");
            }
        }
    }

    @FXML
    void submitInfo(MouseEvent event) throws Exception {
        if(phoneChecker && passwordChecker && emailChecker){
            CustomerController customerController = new CustomerController();
            customerController.editEmail(editEmail.getText());
            customerController.editPhoneNumber(editPhone.getText());
            customerController.editPassword(editPassword.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful operation");
            alert.setHeaderText("This operation was done successfully!");
            alert.setContentText("Your information edited successfully :) ");
            alert.showAndWait();
            new CustomerPanelPage(MainPageGraphicController.customer).start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Not completed correctly!");
            alert.setContentText("Check form and edited it again :) ");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username_lbl.setText(MainPageGraphicController.customer.getUsername());
        editEmail.setText(MainPageGraphicController.customer.getEmail());
        editPhone.setText(MainPageGraphicController.customer.getPhoneNumber());
        editPassword.setText(MainPageGraphicController.customer.getPassword());
    }
}
