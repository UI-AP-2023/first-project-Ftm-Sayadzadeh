package com.example.onlineshop.model.products;

public class Pen extends Stationery implements Discountable{
    private PenColor penColor;
    private double discountPercent;

    public Pen(String productName, double productPrice, int numOfProduct, String manufactureCountry, String penColor) {
        super("Pen", productName, productPrice, numOfProduct, manufactureCountry);
        this.penColor = PenColor.valueOf(penColor);
    }

    public PenColor getPenColor() {
        return penColor;
    }

    public void setPenColor(String penColor) {
        this.penColor = PenColor.valueOf(penColor);
    }
    public double getDiscountPercent() {
        return discountPercent;
    }

    public String toString() {
        return super.toString() +
                "pen color : " + penColor.toString() + "\n";
    }

    @Override
    public void addDiscountToProduct(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public void removeDiscountFromProduct() {
        this.discountPercent = 0;
    }
}
