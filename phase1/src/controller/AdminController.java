package controller;

import model.products.*;
import model.user.Admin;
import model.user.CreditIncreaseRequest;
import model.user.Customer;

import java.util.AbstractList;
import java.util.Objects;

public class AdminController {
    Admin admin = Admin.getAdmin();

    //Digital type of products adding function
    public boolean addFlashMemory(String productName, double productPrice, int numOfProduct, double weight, String dimensions, int capacity, String USBType) {
        FlashMemory newFlashMemory = new FlashMemory(productName, productPrice, numOfProduct, weight, dimensions, capacity, USBType);
        admin.getProductsList().add(newFlashMemory);
        return true;
    }

    public boolean addSSD(String productName, double productPrice, int numOfProduct, double weight, String dimensions, int capacity, int readSpeed, int writeSpeed) {
        SSD newSSD = new SSD(productName, productPrice, numOfProduct, weight, dimensions, capacity, readSpeed, writeSpeed);
        admin.getProductsList().add(newSSD);
        return true;
    }

    public boolean addPersonalComputer(String productName, double productPrice, int numOfProduct, double weight, String dimensions, String CPUModel, int RAMMemory) {
        PersonalComputer newPC = new PersonalComputer(productName, productPrice, numOfProduct, weight, dimensions, CPUModel, RAMMemory);
        admin.getProductsList().add(newPC);
        return true;
    }

    //Stationery type of products adding function
    public boolean addPencil(String productName, double productPrice, int numOfProduct, String manufactureCountry, String pencilType) {
        Pencil newPencil = new Pencil(productName, productPrice, numOfProduct, manufactureCountry, pencilType);
        admin.getProductsList().add(newPencil);
        return true;
    }

    public boolean addPen(String productName, double productPrice, int numOfProduct, String manufactureCountry, String penColor) {
        Pen newPen = new Pen(productName, productPrice, numOfProduct, manufactureCountry, penColor);
        admin.getProductsList().add(newPen);
        return true;
    }

    public boolean addNoteBook(String productName, double productPrice, int numOfProduct, String manufactureCountry, int numOfSheets, String paperType) {
        NoteBook newNoteBook = new NoteBook(productName, productPrice, numOfProduct, manufactureCountry, numOfSheets, paperType);
        admin.getProductsList().add(newNoteBook);
        return true;
    }

    //Vehicle type of products adding function
    public boolean addCar(String productName, double productPrice, int numOfProduct, String manufactureCompany, int engineCapacity, boolean isAutomatic) {
        Car newCar = new Car(productName, productPrice, numOfProduct, manufactureCompany, engineCapacity, isAutomatic);
        admin.getProductsList().add(newCar);
        return true;
    }

    public boolean addBicycle(String productName, double productPrice, int numOfProduct, String manufactureCompany, String bicycleType) {
        Bicycle newBicycle = new Bicycle(productName, productPrice, numOfProduct, manufactureCompany, bicycleType);
        admin.getProductsList().add(newBicycle);
        return true;
    }

    //Edible type of products adding function
    public boolean addEdible(String productName, double productPrice, int numOfProduct, String productionDate, String expirationDate) {
        Edible newEdible = new Edible(productName, productPrice, numOfProduct, productionDate, expirationDate);
        admin.getProductsList().add(newEdible);
        return true;
    }

    //search product by ID
    private int searchProductByID(String productID) {
        int productIndex = -1;
        for (Product element : admin.getProductsList()) {
            if (Objects.equals(element.getProductID(), productID)) {
                productIndex = admin.getProductsList().indexOf(element);
                break;
            }
        }
        return productIndex;
    }

    //products removing function
    public boolean removeProduct(String productID) {
        int productIndex = searchProductByID(productID);
        if (productIndex != -1) {
            admin.getProductsList().remove(productIndex);
            return true;
        } else
            return false;
    }

    //product name editing function
    public boolean editProductName(String productID, String newProductName) {
        int productIndex = searchProductByID(productID);
        if (productIndex != -1) {
            admin.getProductsList().get(productIndex).setProductName(newProductName);
            return true;
        } else
            return false;
    }

    //product price editing function
    public boolean editProductPrice(String productID, int newPrice) {
        int productIndex = searchProductByID(productID);
        if (productIndex != -1) {
            admin.getProductsList().get(productIndex).setProductPrice(newPrice);
            return true;
        } else
            return false;
    }

    //product stock editing function
    public boolean editProductStock(String productID, int newStock) {
        int productIndex = searchProductByID(productID);
        if (productIndex != -1) {
            admin.getProductsList().get(productIndex).setNumOfProduct(newStock);
            if (admin.getProductsList().get(productIndex).getNumOfProduct() > 0)
                admin.getProductsList().get(productIndex).setProductStatus("Available");
            else
                admin.getProductsList().get(productIndex).setProductStatus("Unavailable");
            return true;
        } else
            return false;
    }
    //show customers list
    public AbstractList<Customer> showCustomerInfo(){    //arraylist?
        return CustomerController.getCustomersList();
    }
    //show registration request
    public AbstractList<Customer> showRegistrationRequest(){return admin.getRegistrationRequest();}
    //show comment request
    public AbstractList<Comment> showCommentRequest(){return admin.getCommentRequest();}
    //show credit increase request
    public AbstractList<CreditIncreaseRequest> showCreditIncreaseRequest(){return admin.getCreditIncreaseRequests();}
    //show product list
    public AbstractList<Product> showProductList(){return admin.getProductsList();}
    //accept registration request
    public boolean acceptRegistrationRequest(String username){
        for(Customer element : admin.getRegistrationRequest()){
            if (Objects.equals(element.getUsername(), username)){
                CustomerController.getCustomersList().add(element);
                admin.getRegistrationRequest().remove(element);
                return true;
            }
        }
        return false;
    }
    //accept comment request
    public boolean acceptCommentRequest(String username){
        for(Comment element : admin.getCommentRequest()){
            if (Objects.equals(element.getCommentingUser().getUsername(), username)){
                int productIndex = searchProductByID(element.getProductID());
                element.setCommentStatus(CommentStatus.Accepted);
                admin.getProductsList().get(productIndex).getProductCommentList().add(element);
                admin.getCommentRequest().remove(element);
                return true;
            }
        }
        return false;
    }
    //accept credit increase request
    public boolean acceptCreditIncreaseRequest(String username){
        for(CreditIncreaseRequest element : admin.getCreditIncreaseRequests()){
            if (Objects.equals(element.getUsername() , username)){
                int indexCustomer = this.searchCustomerByUsername(username) ;
                int currentCredit = CustomerController.getCustomersList().get(indexCustomer).getAccountCredit();
                CustomerController.getCustomersList().get(indexCustomer).setAccountCredit(currentCredit + element.getCreditIncreaseAmount());
                admin.getCreditIncreaseRequests().remove(element);
                return true;
            }
        }
        return false;
    }
    //search customer by username
    private int searchCustomerByUsername(String username) {
        int customerIndex = -1;
        for (Customer element : CustomerController.getCustomersList()) {
            if (Objects.equals(element.getUsername(), username)) {
                customerIndex = CustomerController.getCustomersList().indexOf(element);
                break;
            }
        }
        return customerIndex;
    }
    //reject registration request
    public boolean rejectRegistrationRequest(String username){
        for(Customer element : admin.getRegistrationRequest()){
            if (Objects.equals(element.getUsername(), username)){
                admin.getRegistrationRequest().remove(element);
                return true;
            }
        }
        return false;
    }
    //reject comment request
    public boolean rejectCommentRequest(String username){
        for(Comment element : admin.getCommentRequest()){
            if (Objects.equals(element.getCommentingUser().getUsername(), username)){
                element.setCommentStatus(CommentStatus.Rejected);
                return true;
            }
        }
        return false;
    }
    //reject credit increase request
    public boolean rejectCreditIncreaseRequest(String username){
        for(CreditIncreaseRequest element : admin.getCreditIncreaseRequests()){
            if (Objects.equals(element.getUsername() , username)){
                admin.getCreditIncreaseRequests().remove(element);
                return true;
            }
        }
        return false;
    }
    //log in
    public boolean logIn(String username , String password){
        return Objects.equals(username, admin.getUsername()) && Objects.equals(password, admin.getPassword());
    }
    //registration page (add new customer to request list)
    public boolean addRegisteredCustomerToRequestList(Customer newCustomer){
        admin.getRegistrationRequest().add(newCustomer);
        return true;
    }
}

