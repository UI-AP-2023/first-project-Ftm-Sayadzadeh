package com.example.onlineshop;

import com.example.onlineshop.controller.CustomerController;
import com.example.onlineshop.exceptions.InvalidCardCode;
import com.example.onlineshop.exceptions.InvalidCardCVV2;
import com.example.onlineshop.exceptions.InvalidCardPassword;
import com.example.onlineshop.view.CustomerPanel;
import com.example.onlineshop.model.processes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentPageGraphicController {
    private boolean cardCodeChecker;
    private boolean cvv2Checker;
    private boolean passwordChecker;
    @FXML
    private TextField CVV2;

    @FXML
    private ImageView backHome;

    @FXML
    private ImageView backPanel;

    @FXML
    private TextField cardCode;

    @FXML
    private Label cartCodeAlert;

    @FXML
    private TextField creditAmount;

    @FXML
    private Label cvv2Alert;

    @FXML
    private Button increaseCredit;

    @FXML
    private TextField password;

    @FXML
    private Label passwordAlert;

    @FXML
    void backHome(MouseEvent event) throws Exception {
        new MainPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());

    }

    @FXML
    void backPanel(MouseEvent event) throws Exception {
        new CustomerPanelPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    void cardCode(ActionEvent event) {
        try {
            Pattern cardNumberPattern = Pattern.compile("^\\d{16}$");
            Matcher cardNumberMather = cardNumberPattern.matcher(cardCode.getText());
            if (!cardNumberMather.find())
                throw new InvalidCardCode();
            cartCodeAlert.setText("");
            cardCodeChecker = true;
        } catch (InvalidCardCode e) {
            cartCodeAlert.setText("* invalid card code...");
        }
    }
    @FXML
    void CVV2(ActionEvent event) {
        try {
            Pattern cvv2Pattern = Pattern.compile("^\\d{3}$");
            Matcher cvv2Mather = cvv2Pattern.matcher(CVV2.getText());
            if(!cvv2Mather.find())
                throw new InvalidCardCVV2();
            cvv2Alert.setText("");
            cvv2Checker = true;
        } catch (InvalidCardCVV2 e) {
            cvv2Alert.setText("* should contain 3 digits...");
        }
    }

    @FXML
    void password(ActionEvent event) {
        try{
            Pattern passwordPattern = Pattern.compile("^\\d{8}$");
            Matcher passwordMather = passwordPattern.matcher(password.getText());
            if(!passwordMather.find())
                throw new InvalidCardPassword();
            passwordAlert.setText("");
            passwordChecker = true;
        } catch (InvalidCardPassword e) {
            passwordAlert.setText("* should contain 8 digits...");
        }
    }

    @FXML
    void increaseCredit(MouseEvent event) throws Exception {
        if(passwordChecker && cardCodeChecker && cvv2Checker ){
            CreditIncreaseRequest newRequest = new CreditIncreaseRequest(CustomerPanel.getCustomer().getUsername() , Double.parseDouble(creditAmount.getText()));
            CustomerController customerController = new CustomerController();
            if (customerController.addCreditIncreaseRequest(newRequest)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful operation");
                alert.setHeaderText("This operation was done successfully!");
                alert.setContentText("Your increase credit request has been sent to the admin :) ");
                alert.showAndWait();
                new CustomerPanelPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Not completed correctly!");
            alert.setContentText("Check form again :) ");
            alert.showAndWait();
        }
    }

}
