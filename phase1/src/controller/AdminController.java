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
    public boolean addFlashMemory(String productName, int productPrice, int numOfProduct, double weight, String dimensions, int capacity, String USBType) {
        FlashMemory newFlashMemory = new FlashMemory(productName, productPrice, numOfProduct, weight, dimensions, capacity, USBType);
        admin.getProductsList().add(newFlashMemory);
        return true;
    }

    public boolean addSSD(String productName, int productPrice, int numOfProduct, double weight, String dimensions, int capacity, int readSpeed, int writeSpeed) {
        SSD newSSD = new SSD(productName, productPrice, numOfProduct, weight, dimensions, capacity, readSpeed, writeSpeed);
        admin.getProductsList().add(newSSD);
        return true;
    }

    public boolean addPersonalComputer(String productName, int productPrice, int numOfProduct, double weight, String dimensions, String CPUModel, int RAMMemory) {
        PersonalComputer newPC = new PersonalComputer(productName, productPrice, numOfProduct, weight, dimensions, CPUModel, RAMMemory);
        admin.getProductsList().add(newPC);
        return true;
    }

    //Stationery type of products adding function
    public boolean addPencil(String productName, int productPrice, int numOfProduct, String manufactureCountry, String pencilType) {
        Pencil newPencil = new Pencil(productName, productPrice, numOfProduct, manufactureCountry, pencilType);
        admin.getProductsList().add(newPencil);
        return true;
    }

    public boolean addPen(String productName, int productPrice, int numOfProduct, String manufactureCountry, String penColor) {
        Pen newPen = new Pen(productName, productPrice, numOfProduct, manufactureCountry, penColor);
        admin.getProductsList().add(newPen);
        return true;
    }

    public boolean addNoteBook(String productName, int productPrice, int numOfProduct, String manufactureCountry, int numOfSheets, String paperType) {
        NoteBook newNoteBook = new NoteBook(productName, productPrice, numOfProduct, manufactureCountry, numOfSheets, paperType);
        admin.getProductsList().add(newNoteBook);
        return true;
    }

    //Vehicle type of products adding function
    public boolean addCar(String productName, int productPrice, int numOfProduct, String manufactureCompany, int engineCapacity, boolean isAutomatic) {
        Car newCar = new Car(productName, productPrice, numOfProduct, manufactureCompany, engineCapacity, isAutomatic);
        admin.getProductsList().add(newCar);
        return true;
    }

    public boolean addBicycle(String productName, int productPrice, int numOfProduct, String manufactureCompany, String bicycleType) {
        Bicycle newBicycle = new Bicycle(productName, productPrice, numOfProduct, manufactureCompany, bicycleType);
        admin.getProductsList().add(newBicycle);
        return true;
    }

    //Edible type of products adding function
    public boolean addEdible(String productName, int productPrice, int numOfProduct, String productionDate, String expirationDate) {
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

    ////products removing function
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


}

