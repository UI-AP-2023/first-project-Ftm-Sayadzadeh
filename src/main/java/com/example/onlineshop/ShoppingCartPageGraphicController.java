package com.example.onlineshop;

import com.example.onlineshop.controller.CustomerController;
import com.example.onlineshop.exceptions.*;
import com.example.onlineshop.model.products.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ShoppingCartPageGraphicController {
    public ArrayList<Product> products = new ArrayList<Product>();
    @FXML
    private ImageView backHome;

    @FXML
    private ImageView backPanel;

    @FXML
    private TextField discountsCode;

    @FXML
    private Button finalizeShoppingCart;

    @FXML
    private ListView<String> productListView;

    @FXML
    private Label selectedProduct;
    @FXML
    private Button removeProduct;
    @FXML
    void backHome(MouseEvent event) throws Exception {
        new MainPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());

    }

    @FXML
    void backPanel(MouseEvent event) throws Exception {
        new CustomerPanelPage(MainPageGraphicController.customer).start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
    @FXML
    void finalizeShoppingCart(MouseEvent event) throws Exception{
        try{
            CustomerController customerController = new CustomerController();
            customerController.finalizeShoppingCart(discountsCode.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful purchase!");
            alert.setHeaderText("Your purchase was successful!");
            alert.setContentText("You can see your receipt in your shopping history :) ");
            alert.showAndWait();
            discountsCode.clear();
            new CustomerPanelPage(MainPageGraphicController.customer).start((Stage) ((Node) event.getSource()).getScene().getWindow());

        } catch (UnavailableProduct e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Unavailable Product!");
            alert.setContentText("You have unavailable product(s) in your shopping cart :) ");
            alert.showAndWait();
        } catch (EndOfCodeCapacity e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("End of code capacity!");
            alert.setContentText("One of your entered codes capacity is 0 :) ");
            alert.showAndWait();
        } catch (UnavailableCode e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Unavailable Code!");
            alert.setContentText("One of your entered code is not for you :) ");
            alert.showAndWait();
        } catch (ExpiredDiscountCode e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Expired Discount Code!");
            alert.setContentText("One of your entered code has expired :) ");
            alert.showAndWait();
        } catch (InsufficientCredit e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Insufficient Credit!");
            alert.setContentText("You do not have enough credit :) ");
            alert.showAndWait();
        }
    }

    @FXML
    void removeProduct(MouseEvent event) throws Exception {
        MainPageGraphicController.customer.getShoppingCart().remove(indexOfCurrentChoice);
        new ShoppingCartPage(MainPageGraphicController.customer.getShoppingCart()).start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
    private String[] productsID;
    private String currentChoice;
    int indexOfCurrentChoice = 0;

    // I can put product name in list view instead of ID
    public void setList(){
        productsID = new String[products.size()];
        for (int i = 0; i < products.size(); i++) {
            productsID[i] = products.get(i).getProductID();
        }
        productListView.getItems().addAll(productsID);
        productListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String receipt1, String receipt2) {
                currentChoice = productListView.getSelectionModel().getSelectedItem();
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).getProductID().equals(currentChoice)) {
                        indexOfCurrentChoice = i;
                        break;
                    }
                }
                selectedProduct.setText(products.get(indexOfCurrentChoice).toString());
            }
        });
    }
}
