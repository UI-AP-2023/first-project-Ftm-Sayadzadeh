package com.example.onlineshop.model.processes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Discount {
    private double discountPercent;
    private LocalDate codeValidity;
    private int capacity;
    private int productCategoryCode;
    private final DiscountType discountType;
    private String discountCode;
    private static int discountCodeCounter = 1;
    public Discount(double discountPercent , String codeValidity , int capacity , int productCategoryCode , String discountType){
        this.discountPercent = discountPercent;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.codeValidity = LocalDate.parse(codeValidity, formatter);
        this.capacity = capacity;
        this.productCategoryCode = productCategoryCode;
        this.discountType = DiscountType.valueOf(discountType);
        this.discountCode = makeDiscountCode();
        discountCodeCounter++;
    }
    private String makeDiscountCode(){
        return this.productCategoryCode + "-" + this.discountType + "-" +
                "lia@00" + discountCodeCounter;
    }
    private void editDiscountCodeWithNewCategory(){
        String[] previousCode = getDiscountCode().split("-");
        this.discountCode = this.productCategoryCode + previousCode[1] + previousCode[2];
    }
    public static int getDiscountCodeCounter() {
        return discountCodeCounter;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public LocalDate getCodeValidity() {
        return codeValidity;
    }

    public void setCodeValidity(LocalDate codeValidity) {
        this.codeValidity = codeValidity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public int getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(int productCategory) {
        this.productCategoryCode = productCategory;
        this.editDiscountCodeWithNewCategory();
    }

    public DiscountType getDiscountType() {
        return discountType;
    }
}
