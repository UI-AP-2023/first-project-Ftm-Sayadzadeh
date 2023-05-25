package view;

import java.util.Scanner;

public class MainPage {
    private final Scanner input;

    public MainPage(){
        input = new Scanner(System.in);
    }
    public void mainPage(){
        System.out.println("""
                Where do you want to go?
                1) Registration page \s
                2) Log in page and user panel
                3) Products page
                4) EXIT
                """);
        int choice = input.nextInt();
        switch (choice) {
            case 1 -> {
                RegistrationPage registrationPage = new RegistrationPage();
                registrationPage.registrationPage();
            }
            case 2 -> {
                LogInPage logInPage = new LogInPage();
                logInPage.logInPage();
            }
            case 3 -> {
                ProductsPage productsPage = new ProductsPage();
                productsPage.productsPagePanel();
            }
            case 4 -> System.exit(0);
            default -> {
                System.out.println("Enter correct number!");
                mainPage();
            }
        }
    }
}
