package com.example.onlineshop.model.products;

public class Bicycle extends Vehicle {
    private BicycleType bicycleType;

    public Bicycle(String productName, double productPrice, int numOfProduct, String manufactureCompany, String bicycleType) {
        super("Bicycle", productName, productPrice, numOfProduct, manufactureCompany);
        this.bicycleType = BicycleType.valueOf(bicycleType);
    }

    public BicycleType getBicycleType() {
        return bicycleType;
    }

    public void setBicycleType(String bicycleType) {
        this.bicycleType = BicycleType.valueOf(bicycleType);
    }

    public String toString() {
        return super.toString() +
                "bicycle type : " + bicycleType.toString() + "\n";
    }
}
