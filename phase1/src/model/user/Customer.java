package model.user;

import model.products.Product;

import java.util.ArrayList;

public class Customer extends User {
    private int accountCredit = 0 ;
    private final ArrayList<Product> shoppingCart = new ArrayList<>();
    private final ArrayList<Receipt> shoppingHistory = new ArrayList<>();
    public Customer (String username , String email ,String phoneNumber , String password ) {
        super(username, email, phoneNumber, password);
    }

    public int getAccountCredit() {
        return accountCredit;
    }

    public void setAccountCredit(int accountCredit) {
        this.accountCredit = accountCredit;
    }

    public ArrayList<Product> getShoppingCart(){
        return this.shoppingCart;
    }

    public ArrayList<Receipt> getPurchaseHistory(){
        return this.shoppingHistory;
    }
    @Override
    public String toString(){
        return super.toString() +
                "Account Credit : " + accountCredit + "\n" ;
    }
}
