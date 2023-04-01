package controller;

import model.products.Product;
import model.user.Admin;

import java.util.ArrayList;
import java.util.Objects;

public class ProductsPageController {
    private final Admin admin = Admin.getAdmin();
    private static int productNum = 0;  // I need it in do while part of productPage.viewProduct cuz need to check when we are at the end of the list

    public static int getProductNum() {
        return productNum;
    }

    public static void setProductNumZero() {
        productNum = 0;
    }

    //show product list
    public ArrayList<Product> showNextPageOfProduct(ArrayList<Product> products) {
        if (products.size() - productNum >= 5) {
            ArrayList<Product> pageProducts = new ArrayList<>();
            for (int i = productNum; i < productNum + 5; i++) {
                pageProducts.add(products.get(i));
            }
            productNum = productNum + 5;
            return pageProducts;
        } else if (products.size() - productNum < 5 && products.size() - productNum > 0) {
            ArrayList<Product> pageProducts = new ArrayList<>();
            for (int i = productNum; i < products.size(); i++) {
                pageProducts.add(products.get(i));
            }
            productNum = products.size();
            return pageProducts;
        }
        return null;
    }

    public ArrayList<Product> showPreviousPageOfProduct(ArrayList<Product> products) {
        if (productNum >= 10) { //when you are in page two or more
            ArrayList<Product> pageProducts = new ArrayList<>();
            for (int i = productNum - 10; i < productNum - 5; i++) {
                pageProducts.add(products.get(i));
            }
            productNum = productNum - 5;
            return pageProducts;
        } else
            return null;
    }

    //search products by name
    public ArrayList<Product> searchProductByName(String productName) {
        ArrayList<Product> findProduct = new ArrayList<>();
        for (Product element : admin.getProductsList()) {
            if (Objects.equals(element.getProductName(), productName))
                findProduct.add(element);
        }
        return findProduct;
    }

    //select products by ID
    public Product selectProductByID(String productID) {
        for (Product element : admin.getProductsList()) {
            if (Objects.equals(element.getProductID(), productID))
                return element;
        }
        return null;
    }
}
