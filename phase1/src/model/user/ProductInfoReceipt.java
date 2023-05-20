package model.user;

public class ProductInfoReceipt {
    private final String productName;   // they need to be final
    private final String productID;
    private final double productPrice;
    private int productCounter;
    public ProductInfoReceipt(String productName , String productID , double productPrice , int productCounter ){
        this.productName = productName;
        this.productID = productID;
        this.productPrice = productPrice;
        this.productCounter = productCounter;
    }

    public String getProductName() {
        return productName;
    }
    public String getProductID() {
        return productID;
    }
    public double getProductPrice() {
        return productPrice;
    }

    public int getProductCounter() {
        return productCounter;
    }

    public void setProductCounter(int productCounter) {
        this.productCounter = productCounter;
    }
    @Override
    public String toString(){
        return productName  + "(" + productID + ")" + "\t\t" + productCounter + "\t\t" + productPrice +"\n";
    }
}