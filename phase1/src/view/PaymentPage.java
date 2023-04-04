package view;

import controller.CustomerController;
import model.user.CreditIncreaseRequest;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentPage {
    private final Scanner input;
    public PaymentPage(){input = new Scanner(System.in);}
    public void paymentPage(){
        System.out.println("How much do you wanna increase your credit?");
        double creditAmount = input.nextDouble();
        //cart code
        System.out.println("Enter your cartCode : ");
        String cardNumber;
        Pattern cardNumberPattern = Pattern.compile("^\\d{16}$");
        boolean beCorrect;
        input.nextLine();
        do {
            cardNumber = input.nextLine();
            Matcher cardNumberMather = cardNumberPattern.matcher(cardNumber);
            beCorrect = cardNumberMather.find();
            if (!beCorrect)
                System.out.println("You have entered an invalid card number. It should contain 16 digits!");
        } while (!beCorrect);
        //cvv2 code
        System.out.println("Enter your cvv2 : ");
        String cvv2;
        Pattern cvv2Pattern = Pattern.compile("^\\d{3}$");
        do {
            cvv2 = input.nextLine();
            Matcher cvv2Mather = cvv2Pattern.matcher(cvv2);
            beCorrect = cvv2Mather.find();
            if (!beCorrect)
                System.out.println("You have entered an invalid cvv2. It should contain 3 digits!");
        } while (!beCorrect);
        CreditIncreaseRequest newRequest = new CreditIncreaseRequest(CustomerPanel.getCustomer().getUsername() , creditAmount );
        CustomerController customerController = new CustomerController();
        if (customerController.addCreditIncreaseRequest(newRequest))
            System.out.println("DONE SUCCESSFULLY!");
    }
}
