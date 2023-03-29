package model.products;

import java.util.ArrayList;

abstract public class Product {
    private final String productID;
    private String productName;
    private int productPrice;
    private int numOfProduct;
    private String productStatus = "Unavailable";                          //string or boolean?
    private double averageScore = 0;
    private final ProductCategory productCategory;
    private final ArrayList<Comment> productCommentList = new ArrayList<>();

    public Product(String productCategory, String productName, int productPrice, int numOfProduct) {
        this.productCategory = ProductCategory.valueOf(productCategory);
        this.productName = productName;
        this.productPrice = productPrice;
        this.numOfProduct = numOfProduct;
        if (numOfProduct > 0)
            this.productStatus = "Available";
        this.productID = this.productIDMaker();
    }

    private String productIDMaker() {
        //code
        return "";
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public int getNumOfProduct() {
        return numOfProduct;
    }

    public void setNumOfProduct(int numOfProduct) {
        this.numOfProduct = numOfProduct;
    }

    public ArrayList<Comment> getProductCommentList() {
        return this.productCommentList;
    }

    @Override
    public String toString() {
        return "product name : " + productName + "\n" +
                "product ID : " + productID + "\n" +
                "category : " + productCategory.toString() + "\n" + //?
                "price : " + productPrice + "\n" +
                "average score : " + averageScore + "\n" +
                "status : " + productStatus + "\n";
    }
}
