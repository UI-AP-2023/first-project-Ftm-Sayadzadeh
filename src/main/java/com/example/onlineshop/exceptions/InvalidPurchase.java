package com.example.onlineshop.exceptions;

abstract public class InvalidPurchase extends Exception {
    public InvalidPurchase(String subClassMassage) {
        super("InvalidInputData" + " - " + subClassMassage);
    }
}
