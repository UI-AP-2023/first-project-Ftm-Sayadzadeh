package model.products;

import java.util.ArrayList;
import java.util.Locale;

abstract public class Product {
    private String productID;
    private String productName;
    private double productPrice;
    private int numOfProduct;
    private String productStatus = "Unavailable";                          //string or boolean?
    private double averageScore = 0;
    private final ProductCategory productCategory;
    private final ArrayList<Comment> productCommentList = new ArrayList<>();

    public Product(ProductCategory productCategory, String productName, double productPrice, int numOfProduct) {
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
        if (this.productCategory.toString().length() >= 4) {
            for (int i = 0; i < 4; i++) {
                productID.append(this.productCategory.toString().toUpperCase(Locale.ROOT).charAt(i));
            }
        }
        else
            productID.append(this.productCategory.toString().toUpperCase(Locale.ROOT));
        productID.append(this.productCategory.getCategoryCode());
        productID.append("-");
        if (this.productName.length() >= 4) {
            for (int i = 0; i < 4; i++) {
                productID.append(this.productName.toUpperCase(Locale.ROOT).charAt(i));
            }
        }
        else
            productID.append(this.productName.toUpperCase(Locale.ROOT));
        productID.append("-");
        if(this.productCategory  == ProductCategory.Digital ){
            productID.append(Digital.getDigitalCounter());
            Digital.setDigitalCounter( Digital.getDigitalCounter() + 1);
        }
        else if(this.productCategory  == ProductCategory.Stationery ){
            productID.append(Stationery.getStationeryCounter() );
            Stationery.setStationeryCounter( Stationery.getStationeryCounter() + 1);
        }
        else if(this.productCategory  == ProductCategory.Vehicle ){
            productID.append(Vehicle.getVehicleCounter() );
            Vehicle.setVehicleCounter( Vehicle.getVehicleCounter() + 1);
        }
        else if(this.productCategory  == ProductCategory.Edible ){
            productID.append(Edible.getEdibleCounter());
            Edible.setEdibleCounter ( Edible.getEdibleCounter() + 1);
        }
        return productID.toString();
    }
    private void editIDWithNewName(){
        String[] id = getProductID().split("-");
        StringBuilder newID = new StringBuilder();
        newID.append(id[0]).append("-");
        if (this.productName.length() >= 4) {
            for (int i = 0; i < 4; i++) {
                newID.append(this.productName.toUpperCase(Locale.ROOT).charAt(i));
            }
        }
        else
            newID.append(this.productName.toUpperCase(Locale.ROOT));
        newID.append("-").append(id[2]);
        this.productID = newID.toString();
    }

    public String getProductID() {
        return productID;
    }

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
