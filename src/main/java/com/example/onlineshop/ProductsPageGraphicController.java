package com.example.onlineshop;

import com.example.onlineshop.controller.CustomerController;
import com.example.onlineshop.controller.ProductsPageController;
import com.example.onlineshop.model.processes.Comment;
import com.example.onlineshop.model.products.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ProductsPageGraphicController {
    public ArrayList<Product> products = new ArrayList<>();
    @FXML
    private Button applyFilters;

    @FXML
    private Button applyPriceRange;

    @FXML
    private RadioButton availabilityFilter;

    @FXML
    private ImageView backHome;

    @FXML
    private TextField commentBox;

    @FXML
    private TextField endOfRange;

    @FXML
    private ToggleGroup filters;

    @FXML
    private RadioButton priceFilter;

    @FXML
    private ListView<String> productsListView;

    @FXML
    private Label selectedProduct;

    @FXML
    private Button sendComment;

    @FXML
    private TextField startOfRange;
    @FXML
    private Pane rangeGetterPane;

    @FXML
    void backHome(MouseEvent event) throws Exception {
        new MainPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    void sendComment(MouseEvent event) {
        if(MainPageGraphicController.customer!= null) {
            CustomerController customerController = new CustomerController();
            Comment newComment = new Comment(MainPageGraphicController.customer , products.get(indexOfCurrentChoice).getProductID() , commentBox.getText() , customerController.isBoughtChecker(products.get(indexOfCurrentChoice).getProductID()));
            if (customerController.addNewCommentToCommentsRequest(newComment)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SEND SUCCESSFULLY");
                alert.setHeaderText("You comment successfully!");
                alert.setContentText("Your comment was added to comment requests :) ");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NO ACCESS");
            alert.setHeaderText("You can not do this!");
            alert.setContentText("You cannot commit without logging in :) ");
            alert.showAndWait();
        }
    }

    @FXML
    void applyFilters(MouseEvent event) throws Exception {
        if(priceFilter.isSelected()){
            rangeGetterPane.setVisible(true);
        }
        else if(availabilityFilter.isSelected()){
            ArrayList<Product> products = new ProductsPageController().filterByStatus(this.products);
            if(products.size() != 0) {
                new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("NOT FOUND");
                alert.setHeaderText("Can not find any product!");
                alert.setContentText("We do not have any available product :) ");
                alert.showAndWait();
            }
        }
    }
    @FXML
    void applyPriceRange(MouseEvent event) throws Exception {
        ArrayList<Product> products = new ProductsPageController().filterByPrice(this.products , Double.parseDouble(startOfRange.getText()) ,Double.parseDouble(endOfRange.getText()));
        rangeGetterPane.setVisible(false);
        if(products.size() != 0) {
            new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("Can not find any product!");
            alert.setContentText("We do not have any product in this price range :) ");
            alert.showAndWait();
        }
    }
    private String[] productsName;
    private String currentChoice;
    int indexOfCurrentChoice = 0;

    // I can put product name in list view instead of ID
    public void setList(){
        products.sort(Product::compareTo);
        productsName = new String[products.size()];
        for (int i = 0; i < products.size(); i++) {
            productsName[i] = products.get(i).getProductID();
        }
        productsListView.getItems().addAll(productsName);
        productsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String receipt1, String receipt2) {
                currentChoice = productsListView.getSelectionModel().getSelectedItem();
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