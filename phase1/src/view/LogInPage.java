package view;

import controller.AdminController;
import controller.CustomerController;

import java.util.Scanner;

public class LogInPage {
    private final Scanner input = new Scanner(System.in);

    public void logInPage() {
        System.out.println("Enter your username : ");
        String username = input.nextLine();
        System.out.println("Enter your password : ");
        String password = input.nextLine();
        AdminController adminController = new AdminController();
        boolean isAdmin = adminController.logIn(username, password);
        if (isAdmin) {
            AdminPanel adminPanel = new AdminPanel();
            adminPanel.adminCommandPage();
        } else {
            CustomerController customerController = new CustomerController();
            boolean existCustomer = customerController.logIn(username, password);
            if (existCustomer) {
                CustomerPanel customerPanel = new CustomerPanel();
                customerPanel.customerMenu();
            } else {
                System.out.println("We cant find you... TRY AGAIN !");
                logInPage();
            }
        }
    }
}