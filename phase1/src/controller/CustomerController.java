package controller;

import model.user.Customer;

import java.util.ArrayList;

public class CustomerController {
    private static ArrayList<Customer> customersList = new ArrayList<>();     //seter?

    public static ArrayList<Customer> getCustomersList() {
        return customersList;
    }
}
