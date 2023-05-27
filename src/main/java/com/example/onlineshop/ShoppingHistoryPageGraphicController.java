package com.example.onlineshop;

import com.example.onlineshop.controller.CustomerController;
import com.example.onlineshop.controller.ProductsPageController;
import com.example.onlineshop.model.processes.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ShoppingHistoryPageGraphicController implements Initializable {
    @FXML
    private ImageView backHome;

    @FXML
    private ImageView backPanel;

    @FXML
    private Label lbl_selected;

    @FXML
    private ListView<String> receiptListView;
    @FXML
    private TextField productID;
    @FXML
    private TextField score;
    @FXML
    private Button rateProduct;

    @FXML
    void backHome(MouseEvent event) throws Exception {
        new MainPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());

    }

    @FXML
    void backPanel(MouseEvent event) throws Exception {
        new CustomerPanelPage(MainPageGraphicController.customer).start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
    @FXML
    void rateProduct(MouseEvent event) {
        ProductsPageController productsPageController = new ProductsPageController();
        Score newScore = new Score(MainPageGraphicController.customer, Integer.parseInt(score.getText()), productsPageController.selectProductByID(productID.getText()));
        CustomerController customerController = new CustomerController();
        if (customerController.checkBoughtForRating(productID.getText())){
            if(customerController.manageRating(newScore)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("DONE SUCCESSFULLY!");
                alert.setHeaderText("The operation was done successfully!");
                alert.setContentText("Your score for " + productID.getText() + " : " + score.getText());
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("You can not rate this product!");
            alert.setContentText("ERROR ... Only buyer can rate :) ");
            alert.showAndWait();
        }
    }
    private final String[] receiptID = new String[MainPageGraphicController.customer.getShoppingHistory().size()];
    private String currentChoice;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < MainPageGraphicController.customer.getShoppingHistory().size(); i++) {
            receiptID[i] = MainPageGraphicController.customer.getShoppingHistory().get(i).getReceiptID();
        }
        receiptListView.getItems().addAll(receiptID);
        receiptListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String receipt1, String receipt2) {
                currentChoice = receiptListView.getSelectionModel().getSelectedItem();
                int index = 0;
                for (int i = 0; i < MainPageGraphicController.customer.getShoppingHistory().size(); i++) {
                    if (MainPageGraphicController.customer.getShoppingHistory().get(i).getReceiptID().equals(currentChoice)) {
                        index = i;
                        break;
                    }
                }
                lbl_selected.setText(MainPageGraphicController.customer.getShoppingHistory().get(index).toString());
            }
        });
    }
}
