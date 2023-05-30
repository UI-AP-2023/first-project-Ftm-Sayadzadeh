package com.example.onlineshop.model.products;

abstract public class InformationStorageEquipment extends Digital {
    private int capacity;

    public InformationStorageEquipment(String subcategory, String productName, double productPrice, int numOfProduct, double weight, String dimensions, int capacity) {
        super(subcategory, productName, productPrice, numOfProduct, weight, dimensions);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return super.toString() +
                "capacity : " + capacity + "\n";
    }
}