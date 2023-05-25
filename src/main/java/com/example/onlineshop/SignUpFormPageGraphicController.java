package com.example.onlineshop;

import com.example.onlineshop.controller.AdminController;
import com.example.onlineshop.controller.CustomerController;
import com.example.onlineshop.exceptions.*;
import com.example.onlineshop.model.user.Customer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpFormPageGraphicController {
    @FXML
    private ImageView backHome;

    @FXML
    private TextField email_txt;

    @FXML
    private TextField password_txt;

    @FXML
    private TextField phone_txt;

    @FXML
    private Button submitRegistration;

    @FXML
    private TextField username_txt;

    @FXML
    void backHome(MouseEvent event) throws Exception {
        new MainPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    void submitRegistration(MouseEvent event) throws Exception {
        CustomerController customerController = new CustomerController();
        try {
            //check username
            customerController.checkUsername(username_txt.getText());
            //check email
            Pattern emailPattern = Pattern.compile("^[\\w-._%+0-9]+@[\\w0-9.-]+\\.com$");
            customerController.checkEmail(email_txt.getText());
            Matcher emailMatcher = emailPattern.matcher(email_txt.getText());
            if(!emailMatcher.find())
                throw new InvalidEmail();
            //check phone
            Pattern phoneNumberPattern = Pattern.compile("^09\\d{9}$");
            customerController.checkPhoneNumber(phone_txt.getText());
            Matcher phoneNumberMather = phoneNumberPattern.matcher(phone_txt.getText());
            if(!phoneNumberMather.find())
                throw new InvalidPhone();
            //check password
            Pattern passwordPattern = Pattern.compile("^\\S{8,}$");
            Matcher passwordMather = passwordPattern.matcher(password_txt.getText());
            if(!passwordMather.find())
                throw new InvalidPassword();
            //add this to request
            Customer newCustomer = new Customer(username_txt.getText(), email_txt.getText(), phone_txt.getText(), password_txt.getText());
            AdminController adminController = new AdminController();
            if (adminController.addRegisteredCustomerToRequestList(newCustomer)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful operation");
                alert.setHeaderText("This operation was done successfully!");
                alert.setContentText("Your registration request has been sent to the admin :) ");
                alert.showAndWait();
            }
            //go to mainPage
            new MainPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
        } catch (DuplicateUsername e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Duplicated name!");
            alert.setContentText("Another member use this username so please enter another one :)");
            alert.showAndWait();
        } catch (DuplicateEmail e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Duplicated email!");
            alert.setContentText("Another member use this email so please enter another one :)");
            alert.showAndWait();
        } catch (InvalidEmail e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid email!");
            alert.setContentText("You have entered an invalid email address so please enter another one :)");
            alert.showAndWait();
        } catch (DuplicatePhone e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Duplicate phone!");
            alert.setContentText("Another member use this phone number so please enter another one :)");
            alert.showAndWait();
        } catch (InvalidPhone e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid phone!");
            alert.setContentText("You have entered an invalid phone number so please enter another one :)");
            alert.showAndWait();
        } catch (InvalidPassword e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid password!");
            alert.setContentText("You have entered an invalid password. It should contain 8 char without space!");
            alert.showAndWait();
        }
    }

}
