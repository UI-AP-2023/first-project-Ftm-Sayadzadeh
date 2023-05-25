package com.example.onlineshop.model.processes;

public class CreditIncreaseRequest {
    private final String username;
    private final double creditIncreaseAmount;
    public CreditIncreaseRequest(String username , double creditIncreaseAmount ){
        this.username = username;
        this.creditIncreaseAmount = creditIncreaseAmount;
    }

    public String getUsername() {
        return username;
    }

    public double getCreditIncreaseAmount() {
        return creditIncreaseAmount;
    }
    @Override
    public String toString(){
        return "Username : " + username + "\n" +
                "Credit Amount : " + creditIncreaseAmount + "\n";
    }
}
