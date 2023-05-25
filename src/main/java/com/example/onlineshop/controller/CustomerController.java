package com.example.onlineshop.controller;

import com.example.onlineshop.MainPageGraphicController;
import com.example.onlineshop.exceptions.*;
import com.example.onlineshop.model.products.*;
import com.example.onlineshop.model.processes.*;
import com.example.onlineshop.model.user.*;
import com.example.onlineshop.view.CustomerPanel;

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
                    CustomerPanel.setCustomer(element);    //comment it
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
            if (element == CustomerPanel.getCustomer()) {
                return customersList.indexOf(element);
            }
        }
        return 0; // we find the index all time so this will not happen
    }

    //edit email
    public boolean editEmail(String email) {
        customersList.get(findCustomerIndex()).setEmail(email);
        return true;
    }

    //edit phone number
    public boolean editPhoneNumber(String phoneNumber) {
        customersList.get(findCustomerIndex()).setPhoneNumber(phoneNumber);
        return true;
    }

    //edit password
    public boolean editPassword(String password) {
        customersList.get(findCustomerIndex()).setPassword(password);
        return true;
    }

    //buy products
    public boolean buyProduct(Product product) {
        if (Objects.equals(product.getProductStatus(), "Available")) {
            customersList.get(customersList.indexOf(CustomerPanel.getCustomer())).getShoppingCart().add(product);
            return true;
        } else
            return false;
    }

    //check the commenting user buy the product or not
    public boolean isBoughtChecker(String productID) {
        for (Receipt element : CustomerPanel.getCustomer().getShoppingHistory()) {
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
        return CustomerPanel.getCustomer().getShoppingHistory();
    }

    //check the customer bought it or not
    public boolean checkBoughtForRating(String productID) {
        ProductsPageController productsPageController = new ProductsPageController();
        for (Receipt element : CustomerPanel.getCustomer().getShoppingHistory()) {
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
        if (CustomerPanel.getCustomer().getShoppingCart().size() != 0) {
            String productName = CustomerPanel.getCustomer().getShoppingCart().get(0).getProductName();
            String productID = CustomerPanel.getCustomer().getShoppingCart().get(0).getProductID();
            double productPrice = CustomerPanel.getCustomer().getShoppingCart().get(0).getProductPrice();
            ProductInfoReceipt productInfoReceipt1 = new ProductInfoReceipt(productName, productID, productPrice, 0);
            productInfoReceipts.add(productInfoReceipt1);
            for (Product element1 : CustomerPanel.getCustomer().getShoppingCart()) {
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
        for (Product element : CustomerPanel.getCustomer().getShoppingCart()) {
            if (Objects.equals(element.getProductID(), productID)) {
                return element;
            }
        }
        return null;
    }

    //remove product from shopping cart
    public boolean removeProductFromShoppingCart(String productID) {
        if (this.findInShoppingCartByID(productID) != null) {
            CustomerPanel.getCustomer().getShoppingCart().remove(this.findInShoppingCartByID(productID));
            return true;
        }
        return false;
    }

    //find and add available product
    private ArrayList<Product> findFinalProductList() {
        ArrayList<Product> finalList = new ArrayList<>();
        for (Product element : CustomerPanel.getCustomer().getShoppingCart()) {
            if (Objects.equals(element.getProductStatus(), "Available")) {
                finalList.add(element);
            }
        }
        return finalList;
    }

    //update products stock
    private void updateProductsStock(ArrayList<Product> finalList) {
        for (Product element : finalList) {
            int elementIndex = Admin.getAdmin().getProductsList().indexOf(element);
            Admin.getAdmin().getProductsList().get(elementIndex).setNumOfProduct(Admin.getAdmin().getProductsList().get(elementIndex).getNumOfProduct() - 1);
        }
    }
    //apply discount code
//    public boolean applyDiscountCode(Discount discountCode) throws UnavailableCode, EndOfCodeCapacity {
//        int index = this.checkDiscountCodeAvailability(discountCode.getDiscountCode());
//        if( this.checkCodeCapacity(discountCode.getDiscountCode() , index)){
//            if(discountCode.getDiscountType() == DiscountType.WELCOME){
//            }
//            else if(discountCode.getDiscountType() == DiscountType.PURCHASE ){
//
//            }
//            else if(discountCode.getDiscountType() == DiscountType.PRICE){
//
//            }
//        }
//        return true;
//    }
    //check unavailable or available code
    private int checkDiscountCodeAvailability(String discountCode) throws UnavailableCode {
        int index = -1;
        for(Discount element : CustomerPanel.getCustomer().getDiscountsCode()){
            if(element.getDiscountCode().equals(discountCode)) {
                index = CustomerPanel.getCustomer().getDiscountsCode().indexOf(element);
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
        if(CustomerPanel.getCustomer().getDiscountsCode().get(indexOfCode).getCapacity() == 0){
            throw new EndOfCodeCapacity();
        }
        return true;
    }
    //check code expired time
    private boolean checkCodeExpiredTime( int indexOfCode) throws EndOfCodeCapacity {
        if(CustomerPanel.getCustomer().getDiscountsCode().get(indexOfCode).getCodeValidity().isBefore(LocalDate.now())){
            throw new EndOfCodeCapacity();
        }
        return true;
    }

    //finalize shopping cart
    public boolean finalizeShoppingCart() {
        ArrayList<Product> finalList = this.findFinalProductList();
        Receipt newReceipt = new Receipt(finalList);
        if (newReceipt.getTotalAmountPaid() <= CustomerPanel.getCustomer().getAccountCredit()) {
            this.updateProductsStock(finalList);
            CustomerPanel.getCustomer().setAccountCredit(CustomerPanel.getCustomer().getAccountCredit() - newReceipt.getTotalAmountPaid());
            CustomerPanel.getCustomer().getShoppingHistory().add(newReceipt);
            CustomerPanel.getCustomer().getShoppingCart().clear();
            return true;
        } else
            return false;
    }
}