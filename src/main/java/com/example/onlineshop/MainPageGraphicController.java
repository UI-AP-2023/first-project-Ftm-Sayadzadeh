package com.example.onlineshop;

import com.example.onlineshop.controller.ProductsPageController;
import com.example.onlineshop.model.products.Product;
import com.example.onlineshop.model.user.Admin;
import com.example.onlineshop.model.user.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainPageGraphicController {
    public static Customer customer;
    @FXML
    private Pane CPU_pane;

    @FXML
    private TextField CPU_txt;

    @FXML
    private RadioButton PC_CPU_btn;

    @FXML
    private RadioButton PC_RAM_btn;

    @FXML
    private RadioButton PC_btn;

    @FXML
    private Pane RAM_Pane;

    @FXML
    private TextField RAM_txt;

    @FXML
    private RadioButton SSD_btn;

    @FXML
    private RadioButton SSD_read_btn;

    @FXML
    private RadioButton SSD_write_btn;

    @FXML
    private RadioButton USB_btn;

    @FXML
    private Pane USB_pane;

    @FXML
    private RadioButton USB_type_btn;

    @FXML
    private TextField USB_type_txt;

    @FXML
    private Button applyCPUFilter;

    @FXML
    private Button applyRAMFilter;

    @FXML
    private Button applyReedSpeedFilter;

    @FXML
    private Button applyUSBTypeFilter;

    @FXML
    private Button applyWriteSpeedFilter;

    @FXML
    private RadioButton bicycle_btn;

    @FXML
    private RadioButton car_btn;

    @FXML
    private ImageView digitalCategory;

    @FXML
    private ImageView edibleCategory;

    @FXML
    private TextField end_read_txt;

    @FXML
    private TextField end_write_txt;

    @FXML
    private ToggleGroup filters;

    @FXML
    private ImageView goPanel;

    @FXML
    private ImageView goShoppingCartPage;

    @FXML
    private RadioButton notebook_btn;

    @FXML
    private RadioButton pen_btn;

    @FXML
    private RadioButton pencil_btn;

    @FXML
    private Pane reedSpeed_pane;

    @FXML
    private ImageView searchByName;

    @FXML
    private TextField searchText;

    @FXML
    private TextField start_read_txt;

    @FXML
    private TextField start_write_txt;

    @FXML
    private ImageView stationeryCategory;

    @FXML
    private ImageView vehicleCategory;

    @FXML
    private Pane writeSpeed_pane;

    @FXML
    void goPanel(MouseEvent event) throws Exception {
//        customer = new Customer("ala" , "a@email.com" , "09137900843" ,
//                "12345678");
//        Pen pen1 = new Pen("pen1",1000,1,"iran","Black");
//        Pen pen2 = new Pen("pen2",2000,1,"iran","Black");
//        ArrayList<Product> products = new ArrayList<>();
//        products.add(pen1);
//        products.add(pen2);
//        Admin.getAdmin().getProductsList().add(pen1);
//        Admin.getAdmin().getProductsList().add(pen2);
//        Receipt receipt = new Receipt(products);
//        Discount welcomeDiscount = new Discount(30 , "12/12/2029" , 1 , 1000 , "WELCOME" );
//        customer.getDiscountsCode() .add(welcomeDiscount);
//        customer.getShoppingHistory().add(receipt);

        if(customer == null){
            new SignUpAndLogInPage(customer).start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
        else{
            new CustomerPanelPage(customer).start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
    }

    @FXML
    void goShoppingCartPAage(MouseEvent event) {
        if(customer == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("Log in first!");
            alert.setContentText("You can not see your shopping cart before log in :) ");
            alert.showAndWait();
        }
        else{
            //go shopping cart page
        }
    }

    @FXML
    void searchByName(MouseEvent event) throws Exception {
        ArrayList<Product> products = new ProductsPageController().searchProductByName(searchText.getText());
        if(products.size() != 0) {
            new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("Can not find product!");
            alert.setContentText("We do not have this product :) ");
            alert.showAndWait();
        }
    }
    public void applyFilter(ActionEvent event) throws Exception {
        if(car_btn.isSelected()){
            ArrayList<Product> products = new ProductsPageController().filterCarSubcategory(Admin.getAdmin().getProductsList());
            if(products.size() != 0) {
                new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("NOT FOUND");
                alert.setHeaderText("Can not find any product!");
                alert.setContentText("We do not have any car :) ");
                alert.showAndWait();
            }
        }
        else if(bicycle_btn.isSelected()){
            ArrayList<Product> products = new ProductsPageController().filterBicycleSubcategory(Admin.getAdmin().getProductsList());
            if(products.size() != 0) {
                new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("NOT FOUND");
                alert.setHeaderText("Can not find any product!");
                alert.setContentText("We do not have any bicycle :) ");
                alert.showAndWait();
            }
        }
        else if(pen_btn.isSelected()){
            ArrayList<Product> products = new ProductsPageController().filterPenSubcategory(Admin.getAdmin().getProductsList());
            if(products.size() != 0) {
                new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("NOT FOUND");
                alert.setHeaderText("Can not find any product!");
                alert.setContentText("We do not have any pen :) ");
                alert.showAndWait();
            }
        }
        else if(pencil_btn.isSelected()){
            ArrayList<Product> products = new ProductsPageController().filterPencilSubcategory(Admin.getAdmin().getProductsList());
            if(products.size() != 0) {
                new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("NOT FOUND");
                alert.setHeaderText("Can not find any product!");
                alert.setContentText("We do not have any pencil :) ");
                alert.showAndWait();
            }
        }
        else if(notebook_btn.isSelected()){
            ArrayList<Product> products = new ProductsPageController().filterNoteBookSubcategory(Admin.getAdmin().getProductsList());
            if(products.size() != 0) {
                new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("NOT FOUND");
                alert.setHeaderText("Can not find any product!");
                alert.setContentText("We do not have any note book :) ");
                alert.showAndWait();
            }
        }
        else if(PC_btn.isSelected()){
            ArrayList<Product> products = new ProductsPageController().filterPCSubcategory(Admin.getAdmin().getProductsList());
            if(products.size() != 0) {
                new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("NOT FOUND");
                alert.setHeaderText("Can not find any product!");
                alert.setContentText("We do not have any PC :) ");
                alert.showAndWait();
            }
        }
        else if(PC_CPU_btn.isSelected()){
            CPU_pane.setVisible(true);
        }
        else if(PC_RAM_btn.isSelected()){
            RAM_Pane.setVisible(true);
        }
        else if(SSD_btn.isSelected()){
            ArrayList<Product> products = new ProductsPageController().filterSSDSubcategory(Admin.getAdmin().getProductsList());
            if(products.size() != 0) {
                new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("NOT FOUND");
                alert.setHeaderText("Can not find any product!");
                alert.setContentText("We do not have any SSD :) ");
                alert.showAndWait();
            }
        }
        else if(SSD_read_btn.isSelected()){
            reedSpeed_pane.setVisible(true);
        }
        else if(SSD_write_btn.isSelected()){
            writeSpeed_pane.setVisible(true);
        }
        else if(USB_btn.isSelected()){
            ArrayList<Product> products = new ProductsPageController().filterFlashMemorySubcategory(Admin.getAdmin().getProductsList());
            if(products.size() != 0) {
                new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("NOT FOUND");
                alert.setHeaderText("Can not find any product!");
                alert.setContentText("We do not have any USB :) ");
                alert.showAndWait();
            }
        }
        else if(USB_type_btn.isSelected()){
            USB_pane.setVisible(true);
        }
    }
    @FXML
    void applyCPUFilter(MouseEvent event) throws Exception {
        ProductsPageController productsPageController = new ProductsPageController();
        ArrayList<Product> products = productsPageController.filterByCPUModel(productsPageController.filterPCSubcategory(Admin.getAdmin().getProductsList()) , CPU_txt.getText());
        CPU_pane.setVisible(false);
        if(products.size() != 0) {
            new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("Can not find any product!");
            alert.setContentText("We do not have any product with this CPU model :) ");
            alert.showAndWait();
        }
    }
    @FXML
    void applyRAMFilter(MouseEvent event) throws Exception {
        ProductsPageController productsPageController = new ProductsPageController();
        ArrayList<Product> products = productsPageController.filterByRAMMemory(productsPageController.filterPCSubcategory(Admin.getAdmin().getProductsList()) , Integer.parseInt(RAM_txt.getText()));
        RAM_Pane.setVisible(false);
        if(products.size() != 0) {
            new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("Can not find any product!");
            alert.setContentText("We do not have any product with this RAM amount :) ");
            alert.showAndWait();
        }
    }

    @FXML
    void applyReedSpeedFilter(MouseEvent event) throws Exception {
        ProductsPageController productsPageController = new ProductsPageController();
        ArrayList<Product> products = productsPageController.filterByReedSpeed(productsPageController.filterSSDSubcategory(Admin.getAdmin().getProductsList()) , Integer.parseInt(start_read_txt.getText()) , Integer.parseInt(end_read_txt.getText()));
        reedSpeed_pane.setVisible(false);
        if(products.size() != 0) {
            new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("Can not find any product!");
            alert.setContentText("We do not have any product in this reed speed range :) ");
            alert.showAndWait();
        }
    }

    @FXML
    void applyWriteSpeedFilter(MouseEvent event) throws Exception {
        ProductsPageController productsPageController = new ProductsPageController();
        ArrayList<Product> products = productsPageController.filterByWriteSpeed(productsPageController.filterSSDSubcategory(Admin.getAdmin().getProductsList()) , Integer.parseInt(start_write_txt.getText()) , Integer.parseInt(end_write_txt.getText()));
        writeSpeed_pane.setVisible(false);
        if(products.size() != 0) {
            new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("Can not find any product!");
            alert.setContentText("We do not have any product in this write speed range :) ");
            alert.showAndWait();
        }
    }
    @FXML
    void applyUSBTypeFilter(MouseEvent event) throws Exception {
        ProductsPageController productsPageController = new ProductsPageController();
        ArrayList<Product> products = productsPageController.filterByUSBType(productsPageController.filterFlashMemorySubcategory(Admin.getAdmin().getProductsList()) ,USB_type_txt.getText());
        USB_pane.setVisible(false);
        if(products.size() != 0) {
            new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("Can not find any product!");
            alert.setContentText("We do not have any product with this type :) ");
            alert.showAndWait();
        }
    }
    @FXML
    void digitalCategory(MouseEvent event) throws Exception {
        ArrayList<Product> products = new ProductsPageController().filterDigitalCategory(Admin.getAdmin().getProductsList());
        if(products.size() != 0) {
            new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("Can not find any product!");
            alert.setContentText("We do not have any digital product :) ");
            alert.showAndWait();
        }
    }

    @FXML
    void edibleCategory(MouseEvent event) throws Exception {
        ArrayList<Product> products = new ProductsPageController().filterEdibleCategory(Admin.getAdmin().getProductsList());
        if(products.size() != 0) {
            new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("Can not find any product!");
            alert.setContentText("We do not have any edible :) ");
            alert.showAndWait();
        }
    }
    @FXML
    void stationeryCategory(MouseEvent event) throws Exception {
        ArrayList<Product> products = new ProductsPageController().filterStationeryCategory(Admin.getAdmin().getProductsList());
        if(products.size() != 0) {
            new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("Can not find any product!");
            alert.setContentText("We do not have any stationery :) ");
            alert.showAndWait();
        }
    }

    @FXML
    void vehicleCategory(MouseEvent event) throws Exception {
        ArrayList<Product> products = new ProductsPageController().filterVehicleCategory(Admin.getAdmin().getProductsList());
        if(products.size() != 0) {
            new ProductsGraphicPage(products).start((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("Can not find any product!");
            alert.setContentText("We do not have any vehicle :) ");
            alert.showAndWait();
        }
    }
}
