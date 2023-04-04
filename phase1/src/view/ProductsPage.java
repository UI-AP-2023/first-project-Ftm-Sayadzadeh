package view;

import controller.CustomerController;
import controller.ProductsPageController;
import model.products.Product;
import model.user.Admin;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductsPage {
    private final Scanner input = new Scanner(System.in);
    private final ProductsPageController productsPageController = new ProductsPageController();
    private static int customerIndex = -1;
    public ProductsPage(){}

    public static int getCustomerIndex() { //I don't use it in my program
        return customerIndex;
    }

    public static void setCustomerIndex(int index) {
        customerIndex = index;
    }

    public void productsPagePanel() {
        System.out.println("""
                What do you want to do?
                1) view all products \s
                2) search product by name
                3) filter products
                4) Back (Main page or your panel)
                5) EXIT
                """);
        int choice = input.nextInt();
        switch (choice) {
            case 1 -> this.viewProducts(Admin.getAdmin().getProductsList());
            case 2 -> searchProduct();
            case 3 -> manageFilter();
            case 4 -> {
                if (customerIndex == -1) { //when you did not log in
                    MainPage mainPage = new MainPage();
                    mainPage.mainPage();
                } else {//when you log in
                    customerIndex = -1;
                    CustomerPanel customerPanel = new CustomerPanel();
                    customerPanel.customerMenu();
                }
            }
            case 5 -> System.exit(0);
        }
    }

    private void viewProducts(ArrayList<Product> products) {
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
                                if (customerIndex != -1)
                                    this.manageCustomerProductChoice(productsPageController.selectProductByID(productID)); //when select by customer
                                else //if you are not logIn
                                    this.manageUnknownProductChoice();
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
                            if (customerIndex != -1)
                                this.manageCustomerProductChoice(productsPageController.selectProductByID(productID)); //when select by customer
                            else //if you are not logIn
                                this.manageUnknownProductChoice();
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
                            String productID = input.nextLine();
                            if (productsPageController.selectProductByID(productID) != null) {
                                System.out.println(productsPageController.selectProductByID(productID).toString());
                                if (customerIndex != -1)
                                    this.manageCustomerProductChoice(productsPageController.selectProductByID(productID)); //when select by customer
                                else //if you are not logIn
                                    this.manageUnknownProductChoice();
                                choice = 1;
                            } else
                                System.out.println("This ID is wrong!");
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
                            String productID = input.nextLine();
                            if (productsPageController.selectProductByID(productID) != null) {
                                System.out.println(productsPageController.selectProductByID(productID).toString());
                                if (customerIndex != -1)
                                    this.manageCustomerProductChoice(productsPageController.selectProductByID(productID)); //when select by customer
                                else //if you are not logIn
                                    this.manageUnknownProductChoice();
                                choice = 1;
                            } else
                                System.out.println("This ID is wrong!");
                        }
                    }
                }
            } while (choice != 1);
        }
        productsPagePanel();
    }

    //for printing product in products page
    private void printProductPage(AbstractList<Product> products) {
        for (Product element : products) {
            System.out.println("-----" + element.getProductName() + "-----\n");
            System.out.println("*ID: " + element.getProductID() + "\n");
            System.out.println("*price: " + element.getProductPrice() + "\n");
            System.out.println("*status: " + element.getProductStatus() + "\n");
        }
    }

    //search product by name
    private void searchProduct() {
        System.out.println("Enter the name of which product you want : ");
        input.nextLine();
        viewProducts(productsPageController.searchProductByName(input.nextLine()));
    }

    //filter products
    private void manageFilter() {
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
            case 1 -> manageDigitalFilterSwitch(selectedCategory);
            case 2 -> manageStationeryFilterSwitch(selectedCategory);
            case 3 -> manageVehicleFilterSwitch(selectedCategory);
            case 4 -> manageEdibleFilterSwitch(selectedCategory);
            case 5 -> manageFirstSkipFilterSwitch(selectedCategory);
            default -> this.productsPagePanel();
        }
    }

    private void manageFirstSkipFilterSwitch(int selectedCategory) {
        System.out.println("""
                Filter BY:
                1) price (enter two num for this filter)
                2) available status
                """);
        int secondChoice = input.nextInt();
        switch (secondChoice) {
            case 1 -> {
                System.out.println("Enter start and the end of price : ");
                viewFilterProducts(productsPageController.filterByPrice(Admin.getAdmin().getProductsList(), input.nextDouble(), input.nextDouble()), selectedCategory);
            }
            case 2 ->
                    viewFilterProducts(productsPageController.filterByStatus(Admin.getAdmin().getProductsList()), selectedCategory);
            default -> {
                System.out.println("WRONG NUM");
                this.productsPagePanel();
            }
        }
    }

    //filter digital----------------------------------------------------------------------------------------------------
    private void manageDigitalFilterSwitch(int selectedCategory) {
        System.out.println("""
                Filter BY Subcategory:
                1) Information storage
                2) Personal computer
                3) skip
                """);
        int secondChoice = input.nextInt();
        if (secondChoice == 1)
            this.manageInformationStorageFilterSwitch(selectedCategory);
        else if (secondChoice == 2) {
            this.managePCFilterSwitch(selectedCategory);
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
                    viewFilterProducts(productsPageController.filterByPrice((productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()), selectedCategory);
                }
                case 2 ->
                        viewFilterProducts(productsPageController.filterByStatus((productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList()))), selectedCategory);
                default -> {
                    System.out.println("WRONG NUM");
                    this.productsPagePanel();
                }
            }
        } else {
            System.out.println("WRONG NUM");
            this.productsPagePanel();
        }
    }

    //manage Information Storage Filter Switch
    private void manageInformationStorageFilterSwitch(int selectedCategory) {
        System.out.println("""
                Filter BY Subcategory:
                1) SSD
                2) Flash Memory
                3) skip
                """);
        int thirdChoice = input.nextInt();
        switch (thirdChoice) {
            case 1 -> this.manageSSDFilterSwitch(selectedCategory);
            case 2 -> this.manageFlashMemoryFilterSwitch(selectedCategory);
            case 3 -> {
                System.out.println("""
                        Filter BY:
                        1) price (enter two num for this filter)
                        2) available status
                        """);
                int forthChoice = input.nextInt();
                switch (forthChoice) {
                    case 1 -> {
                        System.out.println("Enter start and the end of price : ");
                        viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterInformationStorageSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()), selectedCategory);
                    }
                    case 2 ->
                            viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterInformationStorageSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList()))), selectedCategory);
                    default -> {
                        System.out.println("WRONG NUM");
                        this.productsPagePanel();
                    }
                }
            }
            default -> {
                System.out.println("WRONG NUM");
                this.productsPagePanel();
            }
        }
    }

    //filter SSD switch
    private void manageSSDFilterSwitch(int selectedCategory) {
        System.out.println("""
                Filter BY:
                1) price (enter two num for this filter)
                2) available status
                3) read speed
                4) write speed
                """);
        int forthChoice = input.nextInt();
        switch (forthChoice) {
            case 1 -> {
                System.out.println("Enter the start and the end of price range : ");
                viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterSSDSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()), selectedCategory);
            }
            case 2 ->
                    viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterSSDSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList()))), selectedCategory);
            case 3 -> {
                System.out.println("Enter the start and the end of reed speed range : ");
                viewFilterProducts(productsPageController.filterByReedSpeed(productsPageController.filterSSDSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextInt(), input.nextInt()), selectedCategory);
            }
            case 4 -> {
                System.out.println("Enter the start and the end of write speed range : ");
                viewFilterProducts(productsPageController.filterByWriteSpeed(productsPageController.filterSSDSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextInt(), input.nextInt()), selectedCategory);
            }
            default -> {
                System.out.println("WRONG NUM");
                this.productsPagePanel();
            }
        }
    }

    // filter flash memory
    private void manageFlashMemoryFilterSwitch(int selectedCategory) {
        System.out.println("""
                Filter BY:
                1) price (enter two num for this filter)
                2) available status
                3) USB type
                """);
        int forthChoice = input.nextInt();
        switch (forthChoice) {
            case 1 -> {
                System.out.println("Enter start and the end of price : ");
                viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterFlashMemorySubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()), selectedCategory);
            }
            case 2 ->
                    viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterFlashMemorySubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList()))), selectedCategory);
            case 3 -> {
                System.out.println("Enter USB type : ");
                input.nextLine();
                viewFilterProducts(productsPageController.filterByUSBType(productsPageController.filterFlashMemorySubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextLine()), selectedCategory);
            }
            default -> {
                System.out.println("WRONG NUM");
                this.productsPagePanel();
            }
        }
    }

    //filter PC
    private void managePCFilterSwitch(int selectedCategory) {
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
                viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterPCSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()), selectedCategory);
            }
            case 2 ->
                    viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterPCSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList()))), selectedCategory);
            case 3 -> {
                System.out.println("Enter min for RAM Memory : ");
                viewFilterProducts(productsPageController.filterByRAMMemory(productsPageController.filterPCSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextInt()), selectedCategory);
            }
            case 4 -> {
                System.out.println("Enter the CPU model : ");
                input.nextLine();
                viewFilterProducts(productsPageController.filterByCPUModel(productsPageController.filterPCSubcategory(productsPageController.filterDigitalCategory(Admin.getAdmin().getProductsList())), input.nextLine()), selectedCategory);
            }
            default -> {
                System.out.println("WRONG NUM");
                this.productsPagePanel();
            }
        }
    }

    //filter Stationery-------------------------------------------------------------------------------------------------
    private void manageStationeryFilterSwitch(int selectedCategory) {
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
            case 1 -> this.managePencilFilterSwitch(selectedCategory);
            case 2 -> this.managePenFilterSwitch(selectedCategory);
            case 3 -> this.manageNoteBookFilterSwitch(selectedCategory);
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
                        viewFilterProducts(productsPageController.filterByPrice((productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()), selectedCategory);
                    }
                    case 2 ->
                            viewFilterProducts(productsPageController.filterByStatus((productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList()))), selectedCategory);
                    default -> {
                        System.out.println("WRONG NUM");
                        this.productsPagePanel();
                    }
                }
            }
        }
    }

    //filter Pencil
    private void managePencilFilterSwitch(int selectedCategory) {
        System.out.println("""
                Filter BY:
                1) price (enter two num for this filter)
                2) available status
                """);
        int thirdChoice = input.nextInt();
        switch (thirdChoice) {
            case 1 -> {
                System.out.println("Enter start and the end of price : ");
                viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterPencilSubcategory(productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()), selectedCategory);
            }
            case 2 ->
                    viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterPencilSubcategory(productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList()))), selectedCategory);
            default -> {
                System.out.println("WRONG NUM");
                this.productsPagePanel();
            }
        }
    }

    //filter Pen
    private void managePenFilterSwitch(int selectedCategory) {
        System.out.println("""
                Filter BY:
                1) price (enter two num for this filter)
                2) available status
                """);
        int thirdChoice = input.nextInt();
        switch (thirdChoice) {
            case 1 -> {
                System.out.println("Enter start and the end of price : ");
                viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterPenSubcategory(productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()), selectedCategory);
            }
            case 2 ->
                    viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterPenSubcategory(productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList()))), selectedCategory);
            default -> {
                System.out.println("WRONG NUM");
                this.productsPagePanel();
            }
        }
    }

    //filter Note book
    private void manageNoteBookFilterSwitch(int selectedCategory) {
        System.out.println("""
                Filter BY:
                1) price (enter two num for this filter)
                2) available status
                """);
        int thirdChoice = input.nextInt();
        switch (thirdChoice) {
            case 1 -> {
                System.out.println("Enter start and the end of price : ");
                viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterNoteBookSubcategory(productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()), selectedCategory);
            }
            case 2 ->
                    viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterNoteBookSubcategory(productsPageController.filterStationeryCategory(Admin.getAdmin().getProductsList()))), selectedCategory);
            default -> {
                System.out.println("WRONG NUM");
                this.productsPagePanel();
            }
        }
    }

    //filter vehicle----------------------------------------------------------------------------------------------------
    private void manageVehicleFilterSwitch(int selectedCategory) {
        System.out.println("""
                Filter BY Subcategory:
                1) Car
                2) Bicycle
                3) skip
                """);
        int secondChoice = input.nextInt();
        int thirdChoice;
        switch (secondChoice) {
            case 1 -> this.manageCarFilterSwitch(selectedCategory);
            case 2 -> this.manageBicycleFilterSwitch(selectedCategory);
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
                        viewFilterProducts(productsPageController.filterByPrice((productsPageController.filterVehicleCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()), selectedCategory);
                    }
                    case 2 ->
                            viewFilterProducts(productsPageController.filterByStatus((productsPageController.filterVehicleCategory(Admin.getAdmin().getProductsList()))), selectedCategory);
                    default -> {
                        System.out.println("WRONG NUM");
                        this.productsPagePanel();
                    }
                }
            }
            default -> {
                System.out.println("WRONG NUM");
                this.productsPagePanel();
            }
        }
    }

    //filter Car
    private void manageCarFilterSwitch(int selectedCategory) {
        System.out.println("""
                Filter BY:
                1) price (enter two num for this filter)
                2) available status
                """);
        int thirdChoice = input.nextInt();
        switch (thirdChoice) {
            case 1 -> {
                System.out.println("Enter start and the end of price : ");
                viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterCarSubcategory(productsPageController.filterVehicleCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()), selectedCategory);
            }
            case 2 ->
                    viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterCarSubcategory(productsPageController.filterVehicleCategory(Admin.getAdmin().getProductsList()))), selectedCategory);
            default -> {
                System.out.println("WRONG NUM");
                this.productsPagePanel();
            }
        }
    }

    //filter Bicycle
    private void manageBicycleFilterSwitch(int selectedCategory) {
        System.out.println("""
                Filter BY:
                1) price (enter two num for this filter)
                2) available status
                """);
        int thirdChoice = input.nextInt();
        switch (thirdChoice) {
            case 1 -> {
                System.out.println("Enter start and the end of price : ");
                viewFilterProducts(productsPageController.filterByPrice(productsPageController.filterBicycleSubcategory(productsPageController.filterVehicleCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()), selectedCategory);
            }
            case 2 ->
                    viewFilterProducts(productsPageController.filterByStatus(productsPageController.filterBicycleSubcategory(productsPageController.filterVehicleCategory(Admin.getAdmin().getProductsList()))), selectedCategory);
            default -> {
                System.out.println("WRONG NUM");
                this.productsPagePanel();
            }
        }
    }

    //filter edible-----------------------------------------------------------------------------------------------------
    private void manageEdibleFilterSwitch(int selectedCategory) {
        System.out.println("""
                Filter BY:
                1) price (enter two num for this filter)
                2) available status
                """);
        int secondChoice = input.nextInt();
        switch (secondChoice) {
            case 1 -> {
                System.out.println("Enter start and the end of price : ");
                viewFilterProducts(productsPageController.filterByPrice((productsPageController.filterEdibleCategory(Admin.getAdmin().getProductsList())), input.nextDouble(), input.nextDouble()), selectedCategory);
            }
            case 2 ->
                    viewFilterProducts(productsPageController.filterByStatus((productsPageController.filterEdibleCategory(Admin.getAdmin().getProductsList()))), selectedCategory);
            default -> {
                System.out.println("WRONG NUM");
                this.productsPagePanel();
            }
        }
    }

    //view function for filter page-------------------------------------------------------------------------------------
    private void viewFilterProducts(ArrayList<Product> products, int selectedCategory) {
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
                                if (customerIndex != -1)
                                    this.manageCustomerProductChoice(productsPageController.selectProductByID(productID)); //when select by customer
                                else //if you are not logIn
                                    this.manageUnknownProductChoice();
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
                            if (customerIndex != -1)
                                this.manageCustomerProductChoice(productsPageController.selectProductByID(productID)); //when select by customer
                            else //if you are not logIn
                                this.manageUnknownProductChoice();
                            choice = 1;
                        } else
                            System.out.println("This ID is wrong!");
                    } else if (choice == 3)
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
                            String productID = input.nextLine();
                            if (productsPageController.selectProductByID(productID) != null) {
                                System.out.println(productsPageController.selectProductByID(productID).toString());
                                if (customerIndex != -1)
                                    this.manageCustomerProductChoice(productsPageController.selectProductByID(productID)); //when select by customer
                                else //if you are not logIn
                                    this.manageUnknownProductChoice();
                                choice = 1;
                            } else
                                System.out.println("This ID is wrong!");
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
                            String productID = input.nextLine();
                            if (productsPageController.selectProductByID(productID) != null) {
                                System.out.println(productsPageController.selectProductByID(productID).toString());
                                if (customerIndex != -1)
                                    this.manageCustomerProductChoice(productsPageController.selectProductByID(productID)); //when select by customer
                                else //if you are not logIn
                                    this.manageUnknownProductChoice();
                                choice = 1;
                            } else
                                System.out.println("This ID is wrong!");
                        }
                        case 5 -> backToFirstFilter(selectedCategory);
                    }
                }
            } while (choice != 1);
        }
        productsPagePanel();
    }

    //back to first filter choice

    private void backToFirstFilter(int selectedCategory) {
        switch (selectedCategory) {
            case 1 -> manageDigitalFilterSwitch(selectedCategory);
            case 2 -> manageStationeryFilterSwitch(selectedCategory);
            case 3 -> manageVehicleFilterSwitch(selectedCategory);
            case 4 -> manageEdibleFilterSwitch(selectedCategory);
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
                        this.productsPagePanel();
                    }
                }
            }
            default -> {
                System.out.println("WRONG NUM");
                this.productsPagePanel();
            }
        }
    }

    //if customer is in panel and select product
    private void manageCustomerProductChoice(Product product) {
        System.out.println("""
                1) leave a comment
                2) add this product to shopping cart
                3) BACK
                """);
        int secondChoice = input.nextInt();
        switch (secondChoice) {
            case 1 -> {
                CustomerPanel customerPanel = new CustomerPanel();
                customerPanel.leaveComment(product.getProductID());
            }
            case 2 -> {
                CustomerController customerController = new CustomerController();
                if (customerController.buyProduct(product))
                    System.out.println("DONE SUCCESSFULLY!");
                else
                    System.out.println("This product is unavailable now!");
            }
            default -> {
                //return back
            }
        }
    }
    //if unknown user select product
    private void manageUnknownProductChoice() {
        System.out.println("1) Back to product page panel");
        if (input.nextInt() != 1)
            System.out.println("Although you did not enter the number one, I will return you to the main page :)");
    }
}