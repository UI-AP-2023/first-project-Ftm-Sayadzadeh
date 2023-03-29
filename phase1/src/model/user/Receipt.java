package model.user;

import model.products.Product;

import java.util.ArrayList;

public class Receipt {
    private final String receiptID;
    private final String receiptDate;
    private final ArrayList<Product> receiptProductList;
    private final int totalAmountPaid;
    public Receipt(String receiptDate , ArrayList<Product> receiptProductList ){
        this.receiptDate = receiptDate;
        this.receiptProductList = receiptProductList;
        this.totalAmountPaid = this.totalAmountPaidCalculator(receiptProductList);
        this.receiptID = this.idMaker();          //input
    }
    private int totalAmountPaidCalculator(ArrayList<Product> receiptProductList){
        int totalAmountPaid = 0;
        for(Product product : receiptProductList){
            totalAmountPaid += product.getProductPrice();
        }
        return totalAmountPaid;
    }
    private String idMaker(){
        //code
        return "";
    }

    public String getReceiptID() {
        return receiptID;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public ArrayList<Product> getReceiptProductList() {
        return receiptProductList;
    }

    public int getTotalAmountPaid() {
        return totalAmountPaid;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("receipt ID : ").append(receiptID).append("\n");
        sb.append("receipt Date : ").append(receiptDate).append("\n");
        for(Product element : receiptProductList){
            sb.append("product name : ").append(element.getProductName()).append("\n");
            sb.append("product name : ").append(element.getProductPrice()).append("\n");
        }
        sb.append("total amount paid : ").append(receiptDate).append("\n");
        return sb.toString();
    }
}
