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
    void backHome(MouseEvent event) throws Exception {
        new MainPage().start((Stage) ((Node) event.getSource()).getScene().getWindow());

    }

    @FXML
    void backPanel(MouseEvent event) throws Exception {
        new CustomerPanelPage(MainPageGraphicController.customer).start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    private String[] receiptID = new String[MainPageGraphicController.customer.getShoppingHistory().size()];
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
