package com.example.onlineshop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DiscountListPageGraphicController implements Initializable {
    @FXML
    private ImageView backHome;

    @FXML
    private ImageView backPanel;

    @FXML
    private ListView<String> discountListView;

    @FXML
    private Label selectedDiscount;
    @FXML
    void backHome(MouseEvent event) throws Exception {
        new MainPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    void backPanel(MouseEvent event) throws Exception {
        new CustomerPanelPage(MainPageGraphicController.customer).start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    private final String[] discountsCode = new String[MainPageGraphicController.customer.getDiscountsCode().size()];
    private String currentChoice;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < MainPageGraphicController.customer.getDiscountsCode().size(); i++) {
            discountsCode[i] = MainPageGraphicController.customer.getDiscountsCode().get(i).getDiscountCode();
        }
        discountListView.getItems().addAll(discountsCode);
        discountListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s1, String s2) {
                currentChoice = discountListView.getSelectionModel().getSelectedItem();
                int index = 0;
                for (int i = 0; i < MainPageGraphicController.customer.getDiscountsCode().size(); i++) {
                    if (MainPageGraphicController.customer.getDiscountsCode().get(i).getDiscountCode().equals(currentChoice)) {
                        index = i;
                        break;
                    }
                }
                selectedDiscount.setText(MainPageGraphicController.customer.getDiscountsCode().get(index).toString());
            }
        });
    }
}
