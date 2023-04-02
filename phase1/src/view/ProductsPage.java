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
            case 3 -> filterPage();
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

    //filter products
    public void filterPage() {
        System.out.println("""
                Filter BY Category:
                1) Digital
                2) Stationery
                3) Vehicle
                4) Edible
                5) skip
                """);
        int selectedCategory = input.nextInt();
        switch (selectedCategory) {
            case 1 -> filterDigitalSwitch(selectedCategory);
            case 2 -> filterStationerySwitch(selectedCategory);
            case 3 -> filterVehicleSwitch(selectedCategory);
            case 4 -> filterEdibleSwitch(selectedCategory);
            case 5 -> {
                System.out.println("""
                        Filter BY:
                        1) price (enter two num for this filter)
                        2) available status
                        """);
                int secondChoice = input.nextInt();
                switch (secondChoice) {
                    case 1 -> {
                        System.out.println("Enter start and the end of price : ");
                        viewFilterProducts(productsPageController.filterByPrice(Admin.getAdmin().getProductsList(), input.nextDouble(), input.nextDouble()) , selectedCategory );
                    }
                    case 2 -> viewFilterProducts(productsPageController.filterByStatus(Admin.getAdmin().getProductsList()) , selectedCategory );
                    default -> {
                        System.out.println("WRONG NUM");
                        MainPage mainPage = new MainPage();
                        mainPage.mainPage();
                    }
                }
            }
            default -> {
                MainPage mainPage = new MainPage();
                mainPage.mainPage();
            }
        }
    }

    public void filterDigitalSwitch(int selectedCategory) {
        System.out.println("""
                Filter BY Subcategory:
                1) Information storage
                2) Personal computer
                3) skip
                """);
        int secondChoice = input.nextInt();
        if (secondChoice == 1) {
            System.out.println("""
                    Filter BY Subcategory:
                    1) SSD
                    2) Flash Memory
                    3) skip
                    """);
            int thirdChoice = input.nextInt();
            int forthChoice;
            switch (thirdChoice) {
                case 1 -> {
                    System.out.println("""
                            Filter BY:
                            1) price (enter two num for this filter)
                            2) available status
                            """);
                    forthChoice = input.nextInt();
                    switch (forthChoice) {
                        case 1 -> {
                            System.out.println("Enter start and the end of price : ");
                            viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterSSDSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()) , selectedCategory);
                        }
                        case 2 ->
                                viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterSSDSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList()))) , selectedCategory);

                        default -> {
                            System.out.println("WRONG NUM");
                            MainPage mainPage = new MainPage();
                            mainPage.mainPage();
                        }
                    }
                }
                case 2 -> {
                    System.out.println("""
                            Filter BY:
                            1) price (enter two num for this filter)
                            2) available status
                            """);
                    forthChoice = input.nextInt();
                    switch (forthChoice) {
                        case 1 -> {
                            System.out.println("Enter start and the end of price : ");
                            viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterFlashMemorySubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()) , selectedCategory );
                        }
                        case 2 ->
                                viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterFlashMemorySubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList()))) , selectedCategory );
                        default -> {
                            System.out.println("WRONG NUM");
                            MainPage mainPage = new MainPage();
                            mainPage.mainPage();
                        }
                    }
                }
                case 3 -> {
                    System.out.println("""
                            Filter BY:
                            1) price (enter two num for this filter)
                            2) available status
                            """);
                    forthChoice = input.nextInt();
                    switch (forthChoice) {
                        case 1 -> {
                            System.out.println("Enter start and the end of price : ");
                            viewFilterProducts(productsPageController.filterByPrice((productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()) , selectedCategory );
                        }
                        case 2 ->
                                viewFilterProducts(productsPageController.filterByStatus((productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList()))) , selectedCategory);
                        default -> {
                            System.out.println("WRONG NUM");
                            MainPage mainPage = new MainPage();
                            mainPage.mainPage();
                        }
                    }
                }
                default -> {
                    System.out.println("WRONG NUM");
                    MainPage mainPage = new MainPage();
                    mainPage.mainPage();
                }
            }
        } else if (secondChoice == 2) {
            System.out.println("""
                    Filter BY:
                    1) price (enter two num for this filter)
                    2) available status
                    3) RAM Memory
                    4) CPU Model
                    """);
            int thirdChoice = input.nextInt();
            switch (thirdChoice) {
                case 1 -> {
                    System.out.println("Enter start and the end of price : ");
                    viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterPCSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()), selectedCategory );
                }
                case 2 ->
                        viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterPCSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList()))) , selectedCategory );
                case 3 -> {
                    System.out.println("Enter min for RAM Memory : ");
                    viewFilterProducts(productsPageController.filterByRAMMemory(productsPageController.filterPCSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextInt()) , selectedCategory);
                }
                case 4 -> {
                    System.out.println("Enter the CPU model : ");
                    input.nextLine();
                    viewFilterProducts(productsPageController.filterByCPUModel(productsPageController.filterPCSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextLine()) , selectedCategory );
                }
                default -> {
                    System.out.println("WRONG NUM");
                    MainPage mainPage = new MainPage();
                    mainPage.mainPage();
                }
            }
        } else if (secondChoice == 3) {
            System.out.println("""
                    Filter BY:
                    1) price (enter two num for this filter)
                    2) available status
                    """);
            int thirdChoice = input.nextInt();
            switch (thirdChoice) {
                case 1 -> {
                    System.out.println("Enter start and the end of price : ");
                    viewFilterProducts(productsPageController.filterByPrice((productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()) , selectedCategory );
                }
                case 2 ->
                        viewFilterProducts(productsPageController.filterByStatus((productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList()))) , selectedCategory );
                default -> {
                    System.out.println("WRONG NUM");
                    MainPage mainPage = new MainPage();
                    mainPage.mainPage();
                }
            }
        } else {
            System.out.println("WRONG NUM");
            MainPage mainPage = new MainPage();
            mainPage.mainPage();
        }
    }

    public void filterStationerySwitch(int selectedCategory) {
        System.out.println("""
                Filter BY Subcategory:
                1) Pencil
                2) Pen
                3) Note book
                4) skip
                """);
        int secondChoice = input.nextInt();
        int thirdChoice;
        switch (secondChoice) {
            case 1 -> {
                System.out.println("""
                        Filter BY:
                        1) price (enter two num for this filter)
                        2) available status
                        """);
                thirdChoice = input.nextInt();
                switch (thirdChoice) {
                    case 1 -> {
                        System.out.println("Enter start and the end of price : ");
                        viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterPencilSubcategory(productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()) , selectedCategory);
                    }
                    case 2 ->
                            viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterPencilSubcategory(productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList()))) , selectedCategory);
                    default -> {
                        System.out.println("WRONG NUM");
                        MainPage mainPage = new MainPage();
                        mainPage.mainPage();
                    }
                }
            }
            case 2 -> {
                System.out.println("""
                        Filter BY:
                        1) price (enter two num for this filter)
                        2) available status
                        """);
                thirdChoice = input.nextInt();
                switch (thirdChoice) {
                    case 1 -> {
                        System.out.println("Enter start and the end of price : ");
                        viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterPenSubcategory(productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()) , selectedCategory );
                    }
                    case 2 ->
                            viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterPenSubcategory(productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList()))) , selectedCategory );
                    default -> {
                        System.out.println("WRONG NUM");
                        MainPage mainPage = new MainPage();
                        mainPage.mainPage();
                    }
                }
            }
            case 3 -> {
                System.out.println("""
                        Filter BY:
                        1) price (enter two num for this filter)
                        2) available status
                        """);
                thirdChoice = input.nextInt();
                switch (thirdChoice) {
                    case 1 -> {
                        System.out.println("Enter start and the end of price : ");
                        viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterNoteBookSubcategory(productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()) , selectedCategory );
                    }
                    case 2 ->
                            viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterNoteBookSubcategory(productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList()))) , selectedCategory );
                    default -> {
                        System.out.println("WRONG NUM");
                        MainPage mainPage = new MainPage();
                        mainPage.mainPage();
                    }
                }
            }
            case 4 -> {
                System.out.println("""
                        Filter BY:
                        1) price (enter two num for this filter)
                        2) available status
                        """);
                thirdChoice = input.nextInt();
                switch (thirdChoice) {
                    case 1 -> {
                        System.out.println("Enter start and the end of price : ");
                        viewFilterProducts(productsPageController.filterByPrice((productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()) , selectedCategory );
                    }
                    case 2 ->
                            viewFilterProducts(productsPageController.filterByStatus((productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList()))) , selectedCategory );
                    default -> {
                        System.out.println("WRONG NUM");
                        MainPage mainPage = new MainPage();
                        mainPage.mainPage();
                    }
                }
            }
        }
    }

    public void filterVehicleSwitch(int selectedCategory) {
        System.out.println("""
                Filter BY Subcategory:
                1) Car
                2) Bicycle
                3) skip
                """);
        int secondChoice = input.nextInt();
        int thirdChoice;
        switch (secondChoice) {
            case 1 -> {
                System.out.println("""
                        Filter BY:
                        1) price (enter two num for this filter)
                        2) available status
                        """);
                thirdChoice = input.nextInt();
                switch (thirdChoice) {
                    case 1 -> {
                        System.out.println("Enter start and the end of price : ");
                        viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterCarSubcategory(productsPageController.filterVehicleCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()) , selectedCategory );
                    }
                    case 2 ->
                            viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterCarSubcategory(productsPageController.filterVehicleCategory(Admin.getAdmin().getProductsList()))) , selectedCategory );
                    default -> {
                        System.out.println("WRONG NUM");
                        MainPage mainPage = new MainPage();
                        mainPage.mainPage();
                    }
                }
            }
            case 2 -> {
                System.out.println("""
                        Filter BY:
                        1) price (enter two num for this filter)
                        2) available status
                        """);
                thirdChoice = input.nextInt();
                switch (thirdChoice) {
                    case 1 -> {
                        System.out.println("Enter start and the end of price : ");
                        viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterBicycleSubcategory(productsPageController.filterVehicleCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()) , selectedCategory );
                    }
                    case 2 ->
                            viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterBicycleSubcategory(productsPageController.filterVehicleCategory(Admin.getAdmin().getProductsList()))) , selectedCategory );
                    default -> {
                        System.out.println("WRONG NUM");
                        MainPage mainPage = new MainPage();
                        mainPage.mainPage();
                    }
                }
            }
            case 3 -> {
                System.out.println("""
                        Filter BY:
                        1) price (enter two num for this filter)
                        2) available status
                        """);
                thirdChoice = input.nextInt();
                switch (thirdChoice) {
                    case 1 -> {
                        System.out.println("Enter start and the end of price : ");
                        viewFilterProducts(productsPageController.filterByPrice((productsPageController.filterVehicleCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()) , selectedCategory );
                    }
                    case 2 ->
                            viewFilterProducts(productsPageController.filterByStatus((productsPageController.filterVehicleCategory(Admin.getAdmin().getProductsList()))) , selectedCategory );
                    default -> {
                        System.out.println("WRONG NUM");
                        MainPage mainPage = new MainPage();
                        mainPage.mainPage();
                    }
                }
            }
        }
    }

    public void filterEdibleSwitch(int selectedCategory) {
        System.out.println("""
                Filter BY:
                1) price (enter two num for this filter)
                2) available status
                """);
        int secondChoice = input.nextInt();
        switch (secondChoice) {
            case 1 -> {
                System.out.println("Enter start and the end of price : ");
                viewFilterProducts(productsPageController.filterByPrice((productsPageController.filterEdibleCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()) , selectedCategory);
            }
            case 2 ->
                    viewFilterProducts(productsPageController.filterByStatus((productsPageController.filterEdibleCategory(Admin.getAdmin().getProductsList()))) , selectedCategory );
            default -> {
                System.out.println("WRONG NUM");
                MainPage mainPage = new MainPage();
                mainPage.mainPage();
            }
        }
    }
    //view function for filter page
    public void viewFilterProducts(ArrayList<Product> products , int selectedCategory) {
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
                            4) back to first filter
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
                        case 4 -> backToFirstFilter(selectedCategory);
                    }
                }
                //you are at the last and first page
                else if (ProductsPageController.getProductNum() == products.size()) {  //if products.size() <= 5
                    System.out.println("""
                            What do you want to do?
                            1) BACK to product Page menu
                            2) select product by ID
                            3) back to first filter
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
                    else if(choice == 3)
                        backToFirstFilter(selectedCategory);
                }
                //you are at the first page
                else if (ProductsPageController.getProductNum() == 5) {
                    System.out.println("""
                            What do you want to do?
                            1) BACK to product Page menu
                            2) next page
                            3) select product by ID
                            4) back to first filter
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
                        case 4 -> backToFirstFilter(selectedCategory);
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
                            5) back to first filter
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
                        case 5 -> backToFirstFilter(selectedCategory);
                    }
                }
            } while (choice != 1);
        }
        productsPagePanel();
    }
    public void backToFirstFilter(int selectedCategory){
        switch (selectedCategory){
            case 1 -> filterDigitalSwitch(selectedCategory);
            case 2 -> filterStationerySwitch(selectedCategory);
            case 3 -> filterVehicleSwitch(selectedCategory);
            case 4 -> filterEdibleSwitch(selectedCategory);
            case 5 -> {
                System.out.println("""
                        Filter BY:
                        1) price (enter two num for this filter)
                        2) available status
                        """);
                int secondChoice = input.nextInt();
                switch (secondChoice) {
                    case 1 -> {
                        System.out.println("Enter start and the end of price : ");
                        viewProducts(productsPageController.filterByPrice(Admin.getAdmin().getProductsList(), input.nextDouble(), input.nextDouble()));
                    }
                    case 2 -> viewProducts(productsPageController.filterByStatus(Admin.getAdmin().getProductsList()));
                    default -> {
                        System.out.println("WRONG NUM");
                        MainPage mainPage = new MainPage();
                        mainPage.mainPage();
                    }
                }
            }
            default -> {
                MainPage mainPage = new MainPage();
                mainPage.mainPage();
            }
        }
    }
}

//need to change for customer menu