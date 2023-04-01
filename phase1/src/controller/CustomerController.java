package controller;

import model.user.Customer;

import java.util.ArrayList;

public class CustomerController {
    private static final ArrayList<Customer> customersList = new ArrayList<>();

    public static ArrayList<Customer> getCustomersList() {
        return customersList;
    }

    //log in
    public boolean logIn(String username, String password) {
        for (Customer element : customersList) {
            if (element.getUsername().equals(username)) {
                if (element.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
