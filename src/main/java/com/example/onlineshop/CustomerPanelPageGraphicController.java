package com.example.onlineshop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerPanelPageGraphicController implements Initializable {
    @FXML
    private ImageView backHome;

    @FXML
    private Label credit_lbl;

    @FXML
    private ImageView editInfo;

    @FXML
    private Label email_lbl;

    @FXML
    private ImageView increaseCredit;

    @FXML
    private ImageView logOut;

    @FXML
    private Label password_lbl;

    @FXML
    private Label phone_lbl;

    @FXML
    private ImageView shoppingHistory;

    @FXML
    private ImageView showDiscountsList;

    @FXML
    private ImageView showShoppingCart;

    @FXML
    private Label username_lbl;

    @FXML
    private Label welcome_lbl;

    @FXML
    void backHome(MouseEvent event) throws Exception {
        new MainPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    void editInfo(MouseEvent event) throws Exception {
        new EditInfoPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    void increaseCredit(MouseEvent event) {

    }

    @FXML
    void logOut(MouseEvent event) throws Exception {
        MainPageGraphicController.customer = null;
        new MainPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    void shoppingHistory(MouseEvent event) {

    }

    @FXML
    void showDiscountsList(MouseEvent event) {

    }

    @FXML
    void showShoppingCart(MouseEvent event) {

    }

    private void setInfo(){
        welcome_lbl.setText("Welcome Dear " + MainPageGraphicController.customer.getUsername());
        username_lbl.setText(MainPageGraphicController.customer.getUsername());
        password_lbl.setText(MainPageGraphicController.customer.getPassword());
        email_lbl.setText(MainPageGraphicController.customer.getEmail());
        phone_lbl.setText(MainPageGraphicController.customer.getPhoneNumber());
        String credit = Double.toString(MainPageGraphicController.customer.getAccountCredit());
        credit_lbl.setText(credit);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setInfo() ;
    }
}
