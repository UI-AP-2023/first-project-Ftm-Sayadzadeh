package view;

import controller.AdminController;
import controller.CustomerController;
import model.user.Customer;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationPage {
    private final Scanner input;
    MainPage mainPage = new MainPage();

    public RegistrationPage() {
        input = new Scanner(System.in);
    }

    public void registrationPage() {
        //Username----------------------------------
        System.out.println("Enter your username : ");
        //we check it is new or not
        String username;
        boolean find;
        do {
            username = input.nextLine();
            CustomerController customerController = new CustomerController();
            find = customerController.checkUsername(username);
            if (find) {
                System.out.println("Another member use this username so please enter another one :)");
            }
        } while (find);

        //Email--------------------------------------
        System.out.println("Enter your email : ");
        //we check it is new or not
        String email;
        Pattern emailPattern = Pattern.compile("^[\\w-._%+0-9]+@[\\w0-9.-]+\\.com$");
        boolean beCorrect = false;
        do {
            email = input.nextLine();
            CustomerController customerController = new CustomerController();
            find = customerController.checkEmail(email);
            if (find)
                System.out.println("Another member use this email so please enter another one :)");
            else { //if you enter a new email
                Matcher emailMatcher = emailPattern.matcher(email);
                beCorrect = emailMatcher.find();
                if (!beCorrect)
                    System.out.println("You have entered an invalid email address so please enter another one :)");
            }
        } while (!beCorrect);
        //Phone Number--------------------------------
        System.out.println("Enter your phone number : ");
        //we check it is new or not
        String phoneNumber;
        Pattern phoneNumberPattern = Pattern.compile("^09\\d{9}$");
        beCorrect = false;
        do {
            phoneNumber = input.nextLine();
            CustomerController customerController = new CustomerController();
            find = customerController.checkPhoneNumber(phoneNumber);
            if (find)
                System.out.println("Another member use this phone number so please enter another one :)");
            else {
                Matcher phoneNumberMather = phoneNumberPattern.matcher(phoneNumber);
                beCorrect = phoneNumberMather.find();
                if (!beCorrect)
                    System.out.println("You have entered an invalid phone number so please enter another one :)");
            }
        } while (!beCorrect);
        //password-------------------------------------
        System.out.println("Enter your password : (should contain 8 char without space!) ");
        String password;
        Pattern passwordPattern = Pattern.compile("^\\S{8,}$");
        do {
            password = input.nextLine();
            Matcher passwordMather = passwordPattern.matcher(password);
            beCorrect = passwordMather.find();
            if (!beCorrect)
                System.out.println("You have entered an invalid password. It should contain 8 char without space!");
        } while (!beCorrect);
        //add this to request
        Customer newCustomer = new Customer(username, email, phoneNumber, password);
        AdminController adminController = new AdminController();
        if (adminController.addRegisteredCustomerToRequestList(newCustomer))
            System.out.println("Your registration request has been sent to the admin ! ");
        mainPage.mainPage();
    }
}