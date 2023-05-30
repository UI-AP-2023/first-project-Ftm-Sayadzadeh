package com.example.onlineshop.model.products;

public class FlashMemory extends InformationStorageEquipment {
    private String USBType;

    public FlashMemory(String productName, double productPrice, int numOfProduct, double weight, String dimensions, int capacity, String USBType) {
        super("FlashMemory", productName, productPrice, numOfProduct, weight, dimensions, capacity);
        this.USBType = USBType;
    }

    public String getUSBType() {
        return USBType;
    }

    public void setUSBType(String USBType) {
        this.USBType = USBType;
    }

    @Override
    public String toString() {
        return super.toString() +
                "USB type : " + USBType + "\n";
    }
}