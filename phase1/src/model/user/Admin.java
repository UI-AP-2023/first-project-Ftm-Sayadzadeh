package model.user;

import model.products.Comment;
import model.products.Product;

import java.util.ArrayList;

public class Admin extends User {
    private static Admin admin = new Admin("admin" , " " , " " , "admin");
    private final ArrayList<Product> productsList = new ArrayList<>();
    private final ArrayList<Customer> registrationRequest = new ArrayList<>();
    private final ArrayList<Comment> commentRequest = new ArrayList<>();
    private final ArrayList<CreditIncreaseRequest> creditIncreaseRequests = new ArrayList<>();
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

    public ArrayList<Customer> getRegistrationRequest() {
        return this.registrationRequest;
    }
    public ArrayList<Comment> getCommentRequest() {
        return this.commentRequest;
    }
    public ArrayList<CreditIncreaseRequest> getCreditIncreaseRequests() {
        return this.creditIncreaseRequests;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
