package com.example.onlineshop.model.products;
import com.example.onlineshop.model.processes.*;
import java.util.ArrayList;
import java.util.Locale;

abstract public class Product implements Comparable<Product> {
    private String productID;
    private String productName;
    private double productPrice;
    private int numOfProduct;
    private String productStatus = "Unavailable";
    private double averageScore = 2.5;
    private final ProductCategory productCategory;
    private final String subcategory;
    private final ArrayList<Comment> productCommentList = new ArrayList<>();

    public Product(String subcategory, ProductCategory productCategory, String productName, double productPrice, int numOfProduct) {
        this.subcategory = subcategory;
        this.productCategory = productCategory;
        this.productName = productName;
        this.productPrice = productPrice;
        this.numOfProduct = numOfProduct;
        if (numOfProduct > 0)
            this.productStatus = "Available";
        this.productID = this.productIDMaker();
    }

    private String productIDMaker() {
        StringBuilder productID = new StringBuilder();
        productID.append(this.productCategory.getCategoryCode());
        if (this.subcategory.length() >= 4) {
            for (int i = 0; i < 4; i++) {
                productID.append(this.subcategory.toUpperCase(Locale.ROOT).charAt(i));
            }
        } else
            productID.append(this.subcategory.toUpperCase(Locale.ROOT));
        productID.append("-");
        if (this.productName.length() >= 4) {
            for (int i = 0; i < 4; i++) {
                productID.append(this.productName.toUpperCase(Locale.ROOT).charAt(i));
            }
        } else
            productID.append(this.productName.toUpperCase(Locale.ROOT));
        productID.append("-");
        if (this.productCategory == ProductCategory.Digital) {
            productID.append(Digital.getDigitalCounter());
            Digital.setDigitalCounter(Digital.getDigitalCounter() + 1);
        } else if (this.productCategory == ProductCategory.Stationery) {
            productID.append(Stationery.getStationeryCounter());
            Stationery.setStationeryCounter(Stationery.getStationeryCounter() + 1);
        } else if (this.productCategory == ProductCategory.Vehicle) {
            productID.append(Vehicle.getVehicleCounter());
            Vehicle.setVehicleCounter(Vehicle.getVehicleCounter() + 1);
        } else if (this.productCategory == ProductCategory.Edible) {
            productID.append(Edible.getEdibleCounter());
            Edible.setEdibleCounter(Edible.getEdibleCounter() + 1);
        }
        return productID.toString();
    }

    private void editIDWithNewName() {
        String[] id = getProductID().split("-");
        StringBuilder newID = new StringBuilder();
        newID.append(id[0]).append("-");
        if (this.productName.length() >= 4) {
            for (int i = 0; i < 4; i++) {
                newID.append(this.productName.toUpperCase(Locale.ROOT).charAt(i));
            }
        } else
            newID.append(this.productName.toUpperCase(Locale.ROOT));
        newID.append("-").append(id[2]);
        this.productID = newID.toString();
    }

    public String getProductID() {
        return productID;
    }
//    public void setProductID(String productID){   //we don't need it
//        this.productID = productID;
//    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        editIDWithNewName();
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductStatus() {
        return productStatus;
    }
//
//    public void setProductStatus(String productStatus) {  //we don't need it
//        this.productStatus = productStatus;
//    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public int getNumOfProduct() {
        return numOfProduct;
    }

    public void setNumOfProduct(int numOfProduct) {
        this.numOfProduct = numOfProduct;
        if (this.numOfProduct > 0)
            this.productStatus = "Available";
        else
            this.productStatus = "Unavailable";
    }

    public ArrayList<Comment> getProductCommentList() {
        return this.productCommentList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("product name : ").append(productName).append("\n");
        sb.append("category : ").append(productCategory).append(" > ").append(subcategory).append("\n");
        sb.append("product ID : ").append(productID).append("\n");
        sb.append("price : ").append(productPrice).append("\n");
        sb.append("status : ").append(productStatus).append("\n");
        sb.append("average score : ").append(String.format("%.1f", averageScore)).append("\n");
        sb.append("---------------------Comments---------------------" + "\n");
        for (Comment element : productCommentList) {
            sb.append("*username: ").append(element.getCommentingUser().getUsername()).append("\n");
            sb.append(element.getCommentText()).append("\n");
            sb.append("*Has the commenter bought this product?").append(element.isBought()).append("\n");
        }
        sb.append("--------------------------------------------------" + "\n");
        return sb.toString();
    }
    public int compareTo(Product o) {
        int compareName = this.productName.compareTo(o.productName);
        if(compareName > 0){
            return -1;
        }
        else if(compareName < 0){
            return 1;
        }
        else{
            if(this.averageScore > o.averageScore){
                return -1;
            }
            else if(this.averageScore < o.averageScore){
                return 1;
            }
            else {
                if(this.productPrice > o.productPrice){
                    return -1;
                }
                else if(this.productPrice < o.productPrice){
                    return 1;
                }
                else{
                    return Integer.compare(this.numOfProduct, o.numOfProduct);
                }
            }
        }
    }
}