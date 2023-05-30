package com.example.onlineshop.controller;

import com.example.onlineshop.MainPageGraphicController;
import com.example.onlineshop.exceptions.*;
import com.example.onlineshop.model.products.*;
import com.example.onlineshop.model.processes.*;
import com.example.onlineshop.model.user.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class CustomerController {
    private static final ArrayList<Customer> customersList = new ArrayList<>();
    public CustomerController(){}

    public static ArrayList<Customer> getCustomersList() {
        return customersList;
    }

    //registration page checking username cuz it should be new
    public void checkUsername(String username) throws DuplicateUsername {
        boolean find ;
        for (Customer customer : CustomerController.getCustomersList()) {
            find = customer.getUsername().equals(username);
            if (find) {
                throw new DuplicateUsername();
            }
        }
    }
    //registration page checking email cuz it should be new
    public void checkEmail(String email) throws DuplicateEmail {
        boolean find;
        for (Customer customer : CustomerController.getCustomersList()) {
            find = customer.getEmail().equals(email);
            if (find) {
                throw new DuplicateEmail();
            }
        }
    }
    //registration page checking phone number cuz it should be new
    public void checkPhoneNumber(String phoneNumber) throws DuplicatePhone {
        boolean find;
        for (Customer customer : CustomerController.getCustomersList()) {
            find = customer.getPhoneNumber().equals(phoneNumber);
            if (find) {
                throw new DuplicatePhone();
            }
        }
    }
    //log in
    public boolean logIn(String username, String password) {
        for (Customer element : customersList) {
            if (element.getUsername().equals(username)) {
                if (element.getPassword().equals(password)) {
                    MainPageGraphicController.customer = element;
                    return true;
                }
            }
        }
        return false;
    }

    //find customer index
    private int findCustomerIndex() {
        for (Customer element : customersList) {
            if (element == MainPageGraphicController.customer) {
                return customersList.indexOf(element);
            }
        }
        return 0; // we find the index all time so this will not happen
    }

    //edit email
    public void editEmail(String email) {
        customersList.get(findCustomerIndex()).setEmail(email);
    }

    //edit phone number
    public void editPhoneNumber(String phoneNumber) {
        customersList.get(findCustomerIndex()).setPhoneNumber(phoneNumber);
    }

    //edit password
    public void editPassword(String password) {
        customersList.get(findCustomerIndex()).setPassword(password);
    }

    //buy products
    public boolean buyProduct(Product product) {
        if (Objects.equals(product.getProductStatus(), "Available")) {
            customersList.get(customersList.indexOf(MainPageGraphicController.customer)).getShoppingCart().add(product);
            return true;
        } else
            return false;
    }

    //check the commenting user buy the product or not
    public boolean isBoughtChecker(String productID) {
        for (Receipt element : MainPageGraphicController.customer.getShoppingHistory()) {
            for (Product product : element.getReceiptProductList()) {
                if (Objects.equals(product.getProductID(), productID))
                    return true;
            }
        }
        return false;
    }

    //add comment to comment request list
    public boolean addNewCommentToCommentsRequest(Comment newComment) {
        Admin.getAdmin().getCommentRequest().add(newComment);
        return true;
    }

    //com.example.onlineshop.view customer info
    public Customer viewCustomer(Customer customer) {
        return customer;
    }

    //add credit increase request
    public boolean addCreditIncreaseRequest(CreditIncreaseRequest newRequest) {
        Admin.getAdmin().getCreditIncreaseRequests().add(newRequest);
        return true;
    }

    // com.example.onlineshop.view shopping history
    public ArrayList<Receipt> shoppingHistory() {
        return MainPageGraphicController.customer.getShoppingHistory();
    }

    //check the customer bought it or not
    public boolean checkBoughtForRating(String productID) {
        ProductsPageController productsPageController = new ProductsPageController();
        for (Receipt element : MainPageGraphicController.customer.getShoppingHistory()) {
            if (element.getReceiptProductList().contains(productsPageController.selectProductByID(productID)))
                return true;
        }
        return false;
    }

    //rate to products
    public boolean manageRating(Score newScore) {
        for (Product element : Admin.getAdmin().getProductsList()) {
            if (Objects.equals(element.getProductID(), newScore.getProduct().getProductID())) {
                element.setAverageScore((element.getAverageScore() + newScore.getScore()) / 2);
                return true;
            }
        }
        return false;
    }

    //show shopping cart
    public ArrayList<ProductInfoReceipt> showShoppingCart() {
        ArrayList<ProductInfoReceipt> productInfoReceipts = new ArrayList<>();
        if (MainPageGraphicController.customer.getShoppingCart().size() != 0) {
            String productName = MainPageGraphicController.customer.getShoppingCart().get(0).getProductName();
            String productID = MainPageGraphicController.customer.getShoppingCart().get(0).getProductID();
            double productPrice = MainPageGraphicController.customer.getShoppingCart().get(0).getProductPrice();
            ProductInfoReceipt productInfoReceipt1 = new ProductInfoReceipt(productName, productID, productPrice, 0);
            productInfoReceipts.add(productInfoReceipt1);
            for (Product element1 : MainPageGraphicController.customer.getShoppingCart()) {
                boolean find = false;
                for (ProductInfoReceipt element2 : productInfoReceipts) {
                    if (Objects.equals(element2.getProductName(), element1.getProductName())) {
                        find = true;
                        break;
                    }
                }
                if (!find) {
                    productName = element1.getProductName();
                    productID = element1.getProductID();
                    productPrice = element1.getProductPrice();
                    ProductInfoReceipt productInfoReceipt2 = new ProductInfoReceipt(productName, productID, productPrice, 1);
                    productInfoReceipts.add(productInfoReceipt2);
                } else {
                    for (ProductInfoReceipt element2 : productInfoReceipts) {
                        if (Objects.equals(element2.getProductName(), element1.getProductName())) {
                            element2.setProductCounter(element2.getProductCounter() + 1);
                            break;
                        }
                    }
                }
            }
        }
        return productInfoReceipts;
    }

    //find product in shopping cart by id
    private Product findInShoppingCartByID(String productID) {
        for (Product element : MainPageGraphicController.customer.getShoppingCart()) {
            if (Objects.equals(element.getProductID(), productID)) {
                return element;
            }
        }
        return null;
    }

    //remove product from shopping cart
    public boolean removeProductFromShoppingCart(String productID) {
        if (this.findInShoppingCartByID(productID) != null) {
            MainPageGraphicController.customer.getShoppingCart().remove(this.findInShoppingCartByID(productID));
            return true;
        }
        return false;
    }

    //find and add available product
    private void checkFinalProductList() throws UnavailableProduct {
        for (Product element : MainPageGraphicController.customer.getShoppingCart()) {
            if(Objects.equals(element.getProductStatus(), "Unavailable")) {
                throw new UnavailableProduct();
            }
        }
    }

    private ArrayList<Product> findFinalProductList() throws UnavailableProduct {
        ArrayList<Product> finalProductList = new ArrayList<>();
        for (Product element : MainPageGraphicController.customer.getShoppingCart()) {
            if(Objects.equals(element.getProductStatus(), "Available")) {
                finalProductList.add(element);
            }
        }
        return finalProductList;
    }

    //update products stock
    private void updateProductsStock(ArrayList<Product> finalList) {
        for (Product element : finalList) {
            int elementIndex = Admin.getAdmin().getProductsList().indexOf(element);
            Admin.getAdmin().getProductsList().get(elementIndex).setNumOfProduct(Admin.getAdmin().getProductsList().get(elementIndex).getNumOfProduct() - 1);
        }
    }
    //apply discount code
    public Discount applyDiscountCode(String discountCode , Receipt receipt) throws UnavailableCode, EndOfCodeCapacity, ExpiredDiscountCode {
        int index = this.checkDiscountCodeAvailability(discountCode);
        checkCodeExpiredTime(index);
        if( this.checkCodeCapacity(index)){
            if(MainPageGraphicController.customer.getDiscountsCode().get(index).getDiscountType() == DiscountType.WELCOME){
                receipt.setDiscounts(receipt.getDiscounts() + ((0.3) * receipt.getTotalPriceAfterDiscount()));
                receipt.setTotalPriceAfterDiscount((0.7) * receipt.getTotalPriceAfterDiscount());
                return MainPageGraphicController.customer.getDiscountsCode().get(index);
            }
            else if(MainPageGraphicController.customer.getDiscountsCode().get(index).getDiscountType() == DiscountType.PURCHASE ){
                if(MainPageGraphicController.customer.getShoppingCart().size() >= 3) {
                    applyPurchaseTypeCode(index, discountCode, receipt);
                    return MainPageGraphicController.customer.getDiscountsCode().get(index);
                }
            }
            else if(MainPageGraphicController.customer.getDiscountsCode().get(index).getDiscountType() ==  DiscountType.PRICE){
                if(receipt.getTotalAmountPaid() >= 10000){
                    double percent = MainPageGraphicController.customer.getDiscountsCode().get(index).getDiscountPercent();
                    receipt.setDiscounts(receipt.getDiscounts() + (percent/100 * receipt.getTotalPriceAfterDiscount()));
                    receipt.setTotalPriceAfterDiscount((100 - (percent/100)) * receipt.getTotalPriceAfterDiscount());
                    return MainPageGraphicController.customer.getDiscountsCode().get(index);
                }
            }
        }
        return null;
    }
    private void applyPurchaseTypeCode(int index , String discountCode , Receipt receipt){
        String[] divided = discountCode.split("-");
        if(Objects.equals(divided[0], "1001")){
            double priceOfDigital = 0;
            for(Product element : MainPageGraphicController.customer.getShoppingCart()){
                if(element instanceof Digital )
                    priceOfDigital = priceOfDigital + element.getProductPrice();
            }
            double discountPrice = (MainPageGraphicController.customer.getDiscountsCode().get(index).getDiscountPercent()*priceOfDigital / 100 );
            receipt.setDiscounts(receipt.getDiscounts() + discountPrice );
            receipt.setTotalPriceAfterDiscount(receipt.getTotalPriceAfterDiscount() - discountPrice );
        }
        if(Objects.equals(divided[0], "1002")){
            double priceOfStationery = 0;
            for(Product element : MainPageGraphicController.customer.getShoppingCart()){
                if(element instanceof Stationery )
                    priceOfStationery = priceOfStationery + element.getProductPrice();
            }
            double discountPrice = (MainPageGraphicController.customer.getDiscountsCode().get(index).getDiscountPercent()*priceOfStationery / 100 );
            receipt.setDiscounts(receipt.getDiscounts() + discountPrice );
            receipt.setTotalPriceAfterDiscount(receipt.getTotalPriceAfterDiscount() - discountPrice );
        }
        if(Objects.equals(divided[0], "1003")){
            double priceOfVehicle = 0;
            for(Product element : MainPageGraphicController.customer.getShoppingCart()){
                if(element instanceof Vehicle )
                    priceOfVehicle = priceOfVehicle + element.getProductPrice();
            }
            double discountPrice = (MainPageGraphicController.customer.getDiscountsCode().get(index).getDiscountPercent()*priceOfVehicle / 100 );
            receipt.setDiscounts(receipt.getDiscounts() + discountPrice );
            receipt.setTotalPriceAfterDiscount(receipt.getTotalPriceAfterDiscount() - discountPrice );
        }
        if(Objects.equals(divided[0], "1004")){
            double priceOfEdible = 0;
            for(Product element : MainPageGraphicController.customer.getShoppingCart()){
                if(element instanceof Edible )
                    priceOfEdible = priceOfEdible + element.getProductPrice();
            }
            double discountPrice = (MainPageGraphicController.customer.getDiscountsCode().get(index).getDiscountPercent()*priceOfEdible / 100 );
            receipt.setDiscounts(receipt.getDiscounts() + discountPrice );
            receipt.setTotalPriceAfterDiscount(receipt.getTotalPriceAfterDiscount() - discountPrice );
        }
    }
    //check unavailable or available code
    private int checkDiscountCodeAvailability(String discountCode) throws UnavailableCode {
        int index = -1;
        for(Discount element : MainPageGraphicController.customer.getDiscountsCode()){
            if(element.getDiscountCode().equals(discountCode)) {
                index = MainPageGraphicController.customer.getDiscountsCode().indexOf(element);
                break;
            }
        }
        if (index != -1)
            return index;
        else
            throw new UnavailableCode();
    }
    //check code capacity
    private boolean checkCodeCapacity(int indexOfCode) throws EndOfCodeCapacity {
        if(MainPageGraphicController.customer.getDiscountsCode().get(indexOfCode).getCapacity() == 0){
            throw new EndOfCodeCapacity();
        }
        return true;
    }
    //check code expired time
    private void checkCodeExpiredTime( int indexOfCode) throws ExpiredDiscountCode {
        if(MainPageGraphicController.customer.getDiscountsCode().get(indexOfCode).getCodeValidity().isBefore(LocalDate.now())){
            throw new ExpiredDiscountCode();
        }
    }

    //finalize shopping cart
    public boolean finalizeShoppingCart(String discounts) throws UnavailableProduct, InsufficientCredit, EndOfCodeCapacity, UnavailableCode, ExpiredDiscountCode {
        this.checkFinalProductList();
        Receipt newReceipt = new Receipt(this.findFinalProductList());
        String[] dividedDiscount = discounts.split(" ");
        ArrayList<Discount> applyDiscount = new ArrayList<>();
        if(!discounts.equals("")) {
            for (String element : dividedDiscount) {
                Discount discount = applyDiscountCode(element, newReceipt);
                if (discount != null)
                    applyDiscount.add(discount);
            }
        }
        if (newReceipt.getTotalPriceAfterDiscount() <= MainPageGraphicController.customer.getAccountCredit()) {
            this.updateProductsStock(MainPageGraphicController.customer.getShoppingCart());
            MainPageGraphicController.customer.setAccountCredit(MainPageGraphicController.customer.getAccountCredit() - newReceipt.getTotalPriceAfterDiscount());
            MainPageGraphicController.customer.getShoppingHistory().add(newReceipt);
            if(!discounts.equals("")) {
                for (Discount element : applyDiscount) {
                    element.setCapacity(element.getCapacity() - 1);
                }
            }
            MainPageGraphicController.customer.getShoppingCart().clear();
            return true;
        } else
            throw new InsufficientCredit();
    }
}