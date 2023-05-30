package com.example.onlineshop;

import com.example.onlineshop.model.user.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
    void increaseCredit(MouseEvent event) throws Exception {
        new PaymentPage(MainPageGraphicController.customer).start((Stage) ((Node) event.getSource()).getScene().getWindow());

    }
    @FXML
    void logOut(MouseEvent event) throws Exception {
        MainPageGraphicController.customer = null;
        new MainPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    void shoppingHistory(MouseEvent event) throws Exception {
        if(MainPageGraphicController.customer.getShoppingHistory().size() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("NOT Existed!");
            alert.setHeaderText("NO RECEIPT!");
            alert.setContentText("You have not made a purchase yet!");
            alert.showAndWait();
        }
        else {
            new ShoppingHistoryPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
    }

    @FXML
    void showDiscountsList(MouseEvent event) throws Exception {
        new DiscountListPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    void showShoppingCart(MouseEvent event) throws Exception {
        if(MainPageGraphicController.customer.getShoppingCart().size() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("Your shopping cart is empty!");
            alert.setContentText("You have not added any items to your cart yet :) ");
            alert.showAndWait();
        }
        else
            new ShoppingCartPage(MainPageGraphicController.customer.getShoppingCart()).start((Stage) ((Node) event.getSource()).getScene().getWindow());

    }

    private void setInfo(){
        welcome_lbl.setText("Welcome Dear " + MainPageGraphicController.customer.getUsername());
        username_lbl.setText(MainPageGraphicController.customer.getUsername());
        password_lbl.setText(MainPageGraphicController.customer.getPassword());
        email_lbl.setText(MainPageGraphicController.customer.getEmail());
        phone_lbl.setText(MainPageGraphicController.customer.getPhoneNumber());
        String credit = Double.toString(MainPageGraphicController.customer.getAccountCredit());
        credit_lbl.setText(credit + " $");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setInfo() ;
    }
}
