package view;

import controller.ProductsPageController;
import model.products.Product;
import model.user.Admin;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductsPage {
    private final Scanner input = new Scanner(System.in);
    private final ProductsPageController productsPageController = new ProductsPageController();

    public void productsPagePanel() {
        System.out.println("""
                What do you want to do?
                1) view all products \s
                2) search product by name
                3) filter products
                4) Main page
                5) EXIT
                """);
        int choice = input.nextInt();
        switch (choice) {
            case 1 -> this.viewProducts(Admin.getAdmin().getProductsList());
            case 2 -> searchProduct();
            case 4 -> {
                MainPage mainPage = new MainPage();
                mainPage.mainPage();
            }
            case 5 -> System.exit(0);
        }
    }

    public void viewProducts(ArrayList<Product> products) {
        ProductsPageController.setProductNumZero();
        if (productsPageController.showNextPageOfProduct(products) == null)
            System.out.println("No results found!");
        else {
            //print the first page
            ProductsPageController.setProductNumZero();
            printProductPage(productsPageController.showNextPageOfProduct(products));
            int choice;
            do {
                //you are at the last page
                if (ProductsPageController.getProductNum() == products.size() && products.size() > 5) {
                    System.out.println("""
                            What do you want to do?
                            1) BACK to product Page menu
                            2) previous page
                            3) select product by ID
                            """);
                    choice = input.nextInt();
                    switch (choice) {
                        case 2 -> printProductPage(productsPageController.showPreviousPageOfProduct(products));
                        case 3 -> {
                            System.out.println("Enter the product ID :");
                            input.nextLine();
                            String productID = input.nextLine();
                            if (productsPageController.selectProductByID(productID) != null) {
                                System.out.println(productsPageController.selectProductByID(productID).toString());
                                System.out.println("1) Back to product page panel");
                                if (input.nextInt() != 1)
                                    System.out.println("Although you did not enter the number one, I will return you to the main page :)");
                                choice = 1;
                            } else
                                System.out.println("This ID is wrong!");
                        }
                    }
                }
                //you are at the last and first page
                else if (ProductsPageController.getProductNum() == products.size()) {  //if products.size() <= 5
                    System.out.println("""
                            What do you want to do?
                            1) BACK to product Page menu
                            2) select product by ID
                            """);
                    choice = input.nextInt();
                    if (choice == 2) {
                        System.out.println("Enter the product ID :");
                        input.nextLine();
                        String productID = input.nextLine();
                        if (productsPageController.selectProductByID(productID) != null) {
                            System.out.println(productsPageController.selectProductByID(productID).toString());
                            System.out.println("1) Back to product page panel");
                            if (input.nextInt() != 1)
                                System.out.println("Although you did not enter the number one, I will return you to the main page :)");
                            choice = 1;
                        } else
                            System.out.println("This ID is wrong!");
                    }
                }
                //you are at the first page
                else if (ProductsPageController.getProductNum() == 5) {
                    System.out.println("""
                            What do you want to do?
                            1) BACK to product Page menu
                            2) next page
                            3) select product by ID
                            """);
                    choice = input.nextInt();
                    switch (choice) {
                        case 2 -> printProductPage(productsPageController.showNextPageOfProduct(products));
                        case 3 -> {
                            System.out.println("Enter the product ID :");
                            input.nextLine();
                            System.out.println(productsPageController.selectProductByID(input.nextLine()).toString());
                            System.out.println("1) Back to product page panel");
                            if (input.nextInt() != 1)
                                System.out.println("Although you did not enter the number one, I will return you to the main page :)");
                            choice = 1;
                        }
                    }
                }
                //you are not in last or first page
                else {
                    System.out.println("""
                            What do you want to do?
                            1) BACK to product Page menu
                            2) previous page
                            3) next page
                            4) select product by ID
                            """);
                    choice = input.nextInt();
                    switch (choice) {
                        case 2 -> printProductPage(productsPageController.showPreviousPageOfProduct(products));
                        case 3 -> printProductPage(productsPageController.showNextPageOfProduct(products));
                        case 4 -> {
                            System.out.println("Enter the product ID :");
                            input.nextLine();
                            System.out.println(productsPageController.selectProductByID(input.nextLine()).toString());
                            System.out.println("1) Back to product page panel");
                            if (input.nextInt() != 1)
                                System.out.println("Although you did not enter the number one, I will return you to the main page :)");
                            choice = 1;
                        }
                    }
                }
            } while (choice != 1);
        }
        productsPagePanel();
    }

    public void printProductPage(AbstractList<Product> products) {
        for (Product element : products) {
            System.out.println("-----" + element.getProductName() + "-----\n");
            System.out.println("*ID: " + element.getProductID() + "\n");
            System.out.println("*price: " + element.getProductPrice() + "\n");
            System.out.println("*status: " + element.getProductStatus() + "\n");
        }
    }

    public void searchProduct() {
        System.out.println("Enter the name of which product you want : ");
        input.nextLine();
        viewProducts(productsPageController.searchProductByName(input.nextLine()));
    }
}
//need to change for customer menu