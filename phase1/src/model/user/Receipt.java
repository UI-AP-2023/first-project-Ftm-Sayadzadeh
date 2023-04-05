package model.user;

import model.products.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Receipt {
    private final String receiptID;
    private final String receiptDate;
    private final ArrayList<Product> receiptProductList;
    private final int totalAmountPaid;
    private static int receiptCounter = 1;
    public Receipt(ArrayList<Product> receiptProductList ){
        this.receiptDate = LocalDate.now().toString();
        this.receiptProductList = receiptProductList;
        this.totalAmountPaid = this.totalAmountPaidCalculator(receiptProductList);
        this.receiptID = this.idMaker();
        receiptCounter ++;
    }
    private int totalAmountPaidCalculator(ArrayList<Product> receiptProductList){
        int totalAmountPaid = 0;
        for(Product product : receiptProductList){
            totalAmountPaid += product.getProductPrice();
        }
        return totalAmountPaid;
    }
    private String idMaker(){
        StringBuilder sb = new StringBuilder();
        String[] date = this.receiptDate.split("-");
        sb.append(date[0]).append(date[1]).append(date[2]);
        sb.append("000").append(receiptCounter);
        return sb.toString();
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
    public int getReceiptCounter(){return receiptCounter;} //we don't need it

    private ArrayList<ProductInfoReceipt> findProductNumInReceipt(){
        ArrayList<ProductInfoReceipt> productInfoReceipts = new ArrayList<>();
        String productName = receiptProductList.get(0).getProductName();
        String productID = receiptProductList.get(0).getProductID();
        double productPrice = receiptProductList.get(0).getProductPrice();
        ProductInfoReceipt productInfoReceipt1 = new ProductInfoReceipt(productName , productID , productPrice , 0);
        //counter is 0 cuz the first element should count once
        productInfoReceipts.add(productInfoReceipt1);
        for(Product element1 : receiptProductList){
            boolean find = false;
            for(ProductInfoReceipt element2 : productInfoReceipts){
                if(Objects.equals(element2.getProductName(), element1.getProductName())){
                    find = true;
                    break;
                }
            }
            if(!find){
                productName = element1.getProductName();
                productID = element1.getProductID();
                productPrice = element1.getProductPrice();
                ProductInfoReceipt productInfoReceipt2 = new ProductInfoReceipt(productName , productID , productPrice , 1);
                productInfoReceipts.add(productInfoReceipt2);
            }
            else{
                for(ProductInfoReceipt element2 : productInfoReceipts){
                    if(Objects.equals(element2.getProductName(), element1.getProductName())){
                        element2.setProductCounter(element2.getProductCounter() + 1);
                        break;
                    }
                }
            }
        }
        return productInfoReceipts;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("receipt ID : ").append(receiptID).append("\n");
        sb.append("receipt Date : ").append(receiptDate).append("\n");
        sb.append("-------------------------------------------" + "\n");
        for(ProductInfoReceipt element : this.findProductNumInReceipt()){
            sb.append(element.toString());
        }
        sb.append("-------------------------------------------" + "\n");
        sb.append("total amount paid : ").append(totalAmountPaid).append("\n");
        return sb.toString();
    }
}
