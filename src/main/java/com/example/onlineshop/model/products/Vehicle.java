package com.example.onlineshop.model.products;

abstract public class Vehicle extends Product {
    private String manufactureCompany;
    private static int vehicleCounter = 1;

    public Vehicle(String subcategory, String productName, double productPrice, int numOfProduct, String manufactureCompany) {
        super(subcategory, ProductCategory.Vehicle, productName, productPrice, numOfProduct);
        this.manufactureCompany = manufactureCompany;
    }

    public static int getVehicleCounter() {
        return vehicleCounter;
    }

    public static void setVehicleCounter(int vehicleCounter) {
        Vehicle.vehicleCounter = vehicleCounter;
    }

    public String getManufactureCompany() {
        return manufactureCompany;
    }

    public void setManufactureCompany(String manufactureCompany) {
        this.manufactureCompany = manufactureCompany;
    }

    @Override
    public String toString() {
        return super.toString() +
                "manufacture company : " + manufactureCompany + "\n";
    }
}
