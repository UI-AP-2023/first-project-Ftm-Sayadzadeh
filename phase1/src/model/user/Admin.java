package model.user;

import model.products.Product;

import java.util.ArrayList;

public class Admin extends User {
    private static Admin admin;
    private final ArrayList<Product> productsList = new ArrayList<>();
    private final ArrayList<Request> requestsList = new ArrayList<>();

    private Admin(String username, String email, String phoneNumber, String password) {
        super(username, email, phoneNumber, password);
    }

    public static Admin getAdmin(String username, String email, String phoneNumber, String password) {
        if (admin == null) {
            Admin admin = new Admin(username, email, phoneNumber, password);
        }
        return admin;
    }

    public static Admin getAdmin() {
        return admin;
    }

    public ArrayList<Product> getProductsList() {
        return this.productsList;
    }

    public ArrayList<Request> getRequestsList() {
        return this.requestsList;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
