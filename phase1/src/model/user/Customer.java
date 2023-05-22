package model.user;

import model.products.Discount;
import model.products.Product;

import java.util.ArrayList;

public class Customer extends User {
    private double accountCredit = 0 ;
    private final ArrayList<Product> shoppingCart = new ArrayList<>();
    private final ArrayList<Receipt> shoppingHistory = new ArrayList<>();
    private final ArrayList<Discount> discountsCode = new ArrayList<>();
    public Customer (String username , String email ,String phoneNumber , String password ) {
        super(username, email, phoneNumber, password);
    }

    public double getAccountCredit() {
        return accountCredit;
    }

    public void setAccountCredit(double accountCredit) {
        this.accountCredit = accountCredit;
    }

    public ArrayList<Product> getShoppingCart(){
        return this.shoppingCart;
    }

    public ArrayList<Receipt> getShoppingHistory(){
        return this.shoppingHistory;
    }
    public ArrayList<Discount> getDiscountsCode(){
        return this.discountsCode;
    }
    @Override
    public String toString(){
        return super.toString() +
                "Account Credit : " + accountCredit + "\n" ;
    }
}