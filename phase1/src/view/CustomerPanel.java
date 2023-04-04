package view;

import controller.CustomerController;
import controller.ProductsPageController;
import model.products.Score;
import model.user.Customer;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.products.Comment;
import model.user.ProductInfoReceipt;
import model.user.Receipt;

public class CustomerPanel {
    private final CustomerController customerController;
    private final Scanner input;
    private static Customer customer;
    public CustomerPanel() {
        this.customerController = new CustomerController();
        this.input = new Scanner(System.in);
    }

    public static Customer getCustomer() {
        return customer;
    }

    public static void setCustomer(Customer customer) {
        CustomerPanel.customer = customer;
    }

    public void customerMenu(){
        System.out.println("""
                What do you want to do?
                1) view your info
                2) edit your info \s
                3) go to product page
                4) shopping history
                5) increase your credit
                6) shoppingCart
                7) Main page
                8) EXIT
                """);
        int choice = input.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println(customerController.viewCustomer(customer).toString());
                this.customerMenu();
            }
            case 2 -> this.editInfo();
            case 3 -> {
                ProductsPage.setCustomerIndex(CustomerController.getCustomersList().indexOf(customer));
                ProductsPage productsPage = new ProductsPage();
                productsPage.productsPagePanel();
                this.customerMenu();
            }
            case 4 ->
                shoppingHistoryPage();
            case 5 -> {
                PaymentPage paymentPage =  new PaymentPage();
                paymentPage.paymentPage();
                this.customerMenu();
            }
            case 6 -> shoppingCart();
            case 7 -> {
                MainPage mainPage = new MainPage();
                mainPage.mainPage();
            }
            case 8 -> System.exit(0);
        }
    }
    //edit info switch case
    private void editInfo(){
        System.out.println("""
                Which one do you wanna edit?
                1) email \s
                2) phoneNumber
                3) password
                4) BACK
                """);
        int choice = input.nextInt();
        if(choice == 1){
            this.editEmail();
            this.customerMenu();
        }
        else if(choice == 2){
            this.editPhoneNumber();
            this.customerMenu();
        }
        else if(choice == 3){
            this.editPassword();
            this.customerMenu();
        }
        else //choice=4 or other numbers
            this.customerMenu();
    }
    private void editEmail(){
        System.out.println("Enter your new email : ");
        //we check it is new or not
        String email;
        Pattern emailPattern = Pattern.compile("^[\\w-._%+0-9]+@[\\w0-9.-]+\\.com$");
        boolean beCorrect = false;
        input.nextLine();
        do {
            boolean find = false;
            email = input.nextLine();
            for (Customer customer : CustomerController.getCustomersList()) {
                find = customer.getEmail().equals(email);
                if (find) {
                    System.out.println("Another member use this email so please enter another one :)");
                    break;
                }
            }
            if (!find) {
                Matcher emailMatcher = emailPattern.matcher(email);
                beCorrect = emailMatcher.find();
                if (!beCorrect)
                    System.out.println("You have entered an invalid email address so please enter another one :)");
            }
        } while (!beCorrect);
        if(customerController.editEmail(email))
            System.out.println("DONE SUCCESSFULLY!");
    }
    private void editPhoneNumber(){
        System.out.println("Enter your phone number : ");
        //we check it is new or not
        String phoneNumber;
        Pattern phoneNumberPattern = Pattern.compile("^09\\d{9}$");
        boolean beCorrect = false;
        input.nextLine();
        do {
            boolean find = false;
            phoneNumber = input.nextLine();
            for (Customer customer : CustomerController.getCustomersList()) {
                find = customer.getPhoneNumber().equals(phoneNumber);
                if (find) {
                    System.out.println("Another member use this phone number so please enter another one :)");
                    break;
                }
            }
            if (!find) {
                Matcher phoneNumberMather = phoneNumberPattern.matcher(phoneNumber);
                beCorrect = phoneNumberMather.find();
                if (!beCorrect)
                    System.out.println("You have entered an invalid phone number so please enter another one :)");
            }
        } while (!beCorrect);
        if(customerController.editPhoneNumber(phoneNumber))
            System.out.println("DONE SUCCESSFULLY!");
    }
    private void editPassword(){
        boolean beCorrect;
        System.out.println("Enter your password : (should contain 8 char without space!) ");
        //we check it is new or not
        String password;
        Pattern passwordPattern = Pattern.compile("^\\S{8,}$");
        input.nextLine();
        do {
            password = input.nextLine();
            Matcher passwordMather = passwordPattern.matcher(password);
            beCorrect = passwordMather.find();
            if (!beCorrect)
                System.out.println("You have entered an invalid password. It should contain 8 char without space!");
        } while (!beCorrect);
        if(customerController.editPassword(password))
            System.out.println("DONE SUCCESSFULLY!");
    }
    public void leaveComment(String productID){
        System.out.println("Write your comment text!");
        //input.nextLine();
        String commentText = input.nextLine();
        Comment newComment = new Comment(customer , productID , commentText , customerController.isBoughtChecker(productID));
        if (customerController.addNewCommentToCommentsRequest(newComment))
            System.out.println("DONE SUCCESSFULLY!");
    }
    private void shoppingHistoryPage(){
        if(customerController.shoppingHistory().size() != 0) {
            System.out.println("*YOUR RECEIPT LIST" + "\n");
            for (Receipt element : customerController.shoppingHistory()) {
                System.out.println(element.toString());
            }
            System.out.println("""
                    Now you can :
                    1) select product and rate\s
                    2) back to panel
                    """);
            int choice = input.nextInt();
            if (choice == 1) {
                System.out.println("Enter productID : ");
                input.nextLine(); //
                String productID = input.nextLine();
                if (customerController.checkBoughtForRating(productID)) {
                    System.out.println("Enter your score out of 5 : ");
                    double score = input.nextDouble();
                    ProductsPageController productsPageController = new ProductsPageController();
                    Score newScore = new Score(customer, score, productsPageController.selectProductByID(productID));
                    if (customerController.ratingManager(newScore))
                        System.out.println("DONE SUCCESSFULLY!");
                } else {
                    System.out.println("ERROR ... Only buyer can rate!");
                    this.customerMenu();
                }
            } else //choice = 2 or other num
                this.customerMenu();
        }
        else
            System.out.println("Your shopping history list is empty !");
        this.customerMenu();
    }
    private void shoppingCart(){
        if(customerController.showShoppingCart().size() != 0) {
            int choice;
            do {
                for(ProductInfoReceipt element : customerController.showShoppingCart())
                    System.out.println(element.toString());
                System.out.println("""
                        Now you can :
                        1) remove product\s
                        2) FINALIZE
                        3) back to panel
                        """);
                choice = input.nextInt();
                if (choice == 1) {
                    input.nextLine();
                    System.out.println("Enter the product ID : ");
                    String productID = input.nextLine();
                    if(customerController.removeProductFromShoppingCart(productID))
                        System.out.println("DONE SUCCESSFULLY!");
                    else
                        System.out.println("This product is not in your shopping cart!");
                }
                else if(choice == 2) {
                    if(customerController.finalizeShoppingCart())
                        System.out.println("DONE SUCCESSFULLY!");
                    else
                        System.out.println("Your credit isn't enough!");
                    choice = 3; //back to panel
                }else
                    System.out.println("WRONG NUM!");
            } while (choice != 3);
            this.customerMenu();
        }
        else
            System.out.println("Your shopping cart is empty !");
        this.customerMenu();
    }
}
