package controller;

import model.products.*;
import model.user.Admin;
import model.user.CreditIncreaseRequest;
import model.user.Customer;

import java.util.AbstractList;
import java.util.Objects;

public class AdminController {
    Admin admin = Admin.getAdmin();
    public AdminController(){}

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
            return true;
        } else
            return false;
    }

    //show customers list
    public AbstractList<Customer> showCustomerInfo() {    //arraylist?
        return CustomerController.getCustomersList();
    }

    //show registration request
    public AbstractList<Customer> showRegistrationRequest() {
        return admin.getRegistrationRequest();
    }

    //show comment request
    public AbstractList<Comment> showCommentRequest() {
        return admin.getCommentRequest();
    }

    //show credit increase request
    public AbstractList<CreditIncreaseRequest> showCreditIncreaseRequest() {
        return admin.getCreditIncreaseRequests();
    }

    //show product list
    public AbstractList<Product> showProductList() {
        return admin.getProductsList();
    }

    //accept registration request
    public boolean acceptRegistrationRequest(int registrationRequestIndex) {
        if (admin.getCreditIncreaseRequests().size() - 1 >= registrationRequestIndex) {
            Customer newCustomer = admin.getRegistrationRequest().get(registrationRequestIndex);
            CustomerController.getCustomersList().add(newCustomer);
            admin.getRegistrationRequest().remove(newCustomer);
            return true;
        } else
            return false;
    }

    //accept comment request
    public boolean acceptCommentRequest(int indexOfComment) {
        if (admin.getCommentRequest().size() - 1 >= indexOfComment) {
            admin.getCommentRequest().get(indexOfComment).setCommentStatus(CommentStatus.Accepted);
            int productIndex = searchProductByID(admin.getCommentRequest().get(indexOfComment).getProductID());
            admin.getProductsList().get(productIndex).getProductCommentList().add(admin.getCommentRequest().get(indexOfComment));
            admin.getCommentRequest().remove(admin.getCommentRequest().get(indexOfComment));
            return true;
        } else
            return false;
    }

    //accept credit increase request
    public boolean acceptCreditIncreaseRequest(int indexOfCreditRequest) {
        if (admin.getCreditIncreaseRequests().size() - 1 >= indexOfCreditRequest) {
            int indexCustomer = this.searchCustomerByUsername(admin.getCreditIncreaseRequests().get(indexOfCreditRequest).getUsername());
            double currentCredit = CustomerController.getCustomersList().get(indexCustomer).getAccountCredit();
            CustomerController.getCustomersList().get(indexCustomer).setAccountCredit(currentCredit + admin.getCreditIncreaseRequests().get(indexOfCreditRequest).getCreditIncreaseAmount());
            admin.getCreditIncreaseRequests().remove(indexOfCreditRequest);
            return true;
        } else
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
    public boolean rejectRegistrationRequest(int registrationRequestIndex) {
        if (admin.getCreditIncreaseRequests().size() - 1 >= registrationRequestIndex) {
            Customer newCustomer = admin.getRegistrationRequest().get(registrationRequestIndex);
            admin.getRegistrationRequest().remove(newCustomer);
            return true;
        } else
            return false;
    }

    //reject comment request
    public boolean rejectCommentRequest(int indexOfComment) {
        if (admin.getCommentRequest().size() - 1 >= indexOfComment) {
            admin.getCommentRequest().get(indexOfComment).setCommentStatus(CommentStatus.Rejected);
            return true;
        } else
            return false;
    }

    //reject credit increase request
    public boolean rejectCreditIncreaseRequest(int indexOfCreditRequest) {
        if (admin.getCreditIncreaseRequests().size() - 1 >= indexOfCreditRequest) {
            admin.getCreditIncreaseRequests().remove(indexOfCreditRequest);
            return true;
        } else
            return false;
    }

    //log in
    public boolean logIn(String username, String password) {
        return Objects.equals(username, admin.getUsername()) && Objects.equals(password, admin.getPassword());
    }

    //registration page (add new customer to request list)
    public boolean addRegisteredCustomerToRequestList(Customer newCustomer) {
        admin.getRegistrationRequest().add(newCustomer);
        return true;
    }
}