package view;

import controller.AdminController;
import model.products.Comment;
import model.products.Product;
import model.user.CreditIncreaseRequest;
import model.user.Customer;

import java.util.Scanner;

public class AdminPanel {
    private final AdminController adminController;
    private final Scanner input;

    public AdminPanel() {
        this.adminController = new AdminController();
        this.input = new Scanner(System.in);
    }

    public void adminCommandPage() {
        System.out.println("Enter the command line ! ");
        String commandLine = input.nextLine();
        String[] dividedCommend = commandLine.split(" ");
        switch (dividedCommend[0]) {
            case "Add" -> {
                this.manageAddCommandType(dividedCommend);
                this.adminCommandPage();
            }
            case "Remove" -> {
                this.manageRemoveCommand(dividedCommend);
                this.adminCommandPage();
            }
            case "Edit" -> {
                this.manageEditCommandType(dividedCommend);
                this.adminCommandPage();
            }
            case "Show" -> {
                this.manageShowCommandType(dividedCommend);
                this.adminCommandPage();
            }
            case "Accept" -> {
                this.manageAcceptCommandType(dividedCommend);
                this.adminCommandPage();
            }
            case "Reject" -> {
                this.manageRejectCommandType(dividedCommend);
                this.adminCommandPage();
            }
            case "CustomerDiscount" -> {
                this.manageCustomerDiscountCommandType(dividedCommend);
                this.adminCommandPage();
            }
            case "ProductDiscount" -> {
                this.manageProductDiscountCommandType(dividedCommend);
                this.adminCommandPage();
            }
            case "help" -> {
                System.out.println(helpCommand());
                this.adminCommandPage();
            }
            case "MainPage" -> {
                MainPage mainPage = new MainPage();
                mainPage.mainPage();
            }
            case "EXIT" -> System.exit(0);
            default -> {
                System.out.println("WRONG COMMEND LINE!");
                this.adminCommandPage();
            }
        }
    }

    private String helpCommand() {
        return """
                - Add FlashMemory productName productPrice numOfProduct weight dimensions capacity USBType
                - Add SSD productName productPrice numOfProduct weight dimensions capacity reedSpeed writeSpeed
                - Add PC productName productPrice numOfProduct weight dimensions CPUModel RAMMemory
                - Add Pencil productName productPrice numOfProduct manufactureCountry pencilType
                - Add Pen productName productPrice numOfProduct manufactureCountry penColor
                - Add NoteBook productName productPrice numOfProduct manufactureCountry numOfSheets paperType
                - Add Car productName productPrice numOfProduct manufactureCompany engineCapacity isAutomatic
                - Add Bicycle productName productPrice numOfProduct manufactureCompany bicycleType
                - Add Edible productName productPrice numOfProduct productionDate expirationDate
                - Remove productID
                - Edit (name || price || stock) productID (newName || newPrice || newStock)
                - Show (Product || Customers || RegistrationRequests || CommentRequests || CreditRequests)
                - Accept (Registration || Comment || CreditIncrease ) index
                - Reject (Registration || Comment || CreditIncrease ) index
                - CustomerDiscount discountPercent codeValidity capacity ( 1000 PRICE || (1001 || 1002 || 1003 || 1004) PURCHASE )
                - ProductDiscount (add productID discountPercent|| remove productID discountPercent)
                - MainPage
                - EXIT
                """;
    }

    private void manageAddCommandType(String[] dividedCommend) {
        switch (dividedCommend[1]) {
            case "FlashMemory" -> {
                if (adminController.addFlashMemory(dividedCommend[2], Double.parseDouble(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), Double.parseDouble(dividedCommend[5]), dividedCommend[6], Integer.parseInt(dividedCommend[7]), dividedCommend[8]))
                    System.out.println("The operation was done successfully!");
            }
            case "SSD" -> {
                if (adminController.addSSD(dividedCommend[2], Double.parseDouble(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), Double.parseDouble(dividedCommend[5]), dividedCommend[6], Integer.parseInt(dividedCommend[7]), Integer.parseInt(dividedCommend[8]), Integer.parseInt(dividedCommend[9])))
                    System.out.println("The operation was done successfully!");
            }
            case "PC" -> {
                if (adminController.addPersonalComputer(dividedCommend[2], Double.parseDouble(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), Double.parseDouble(dividedCommend[5]), dividedCommend[6], dividedCommend[7], Integer.parseInt(dividedCommend[8])))
                    System.out.println("The operation was done successfully!");
            }
            case "Pencil" -> {
                if (adminController.addPencil(dividedCommend[2], Double.parseDouble(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), dividedCommend[5], dividedCommend[6]))
                    System.out.println("The operation was done successfully!");
            }
            case "Pen" -> {
                if (adminController.addPen(dividedCommend[2], Double.parseDouble(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), dividedCommend[5], dividedCommend[6]))
                    System.out.println("The operation was done successfully!");
            }
            case "NoteBook" -> {
                if (adminController.addNoteBook(dividedCommend[2], Double.parseDouble(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), dividedCommend[5], Integer.parseInt(dividedCommend[6]), dividedCommend[7]))
                    System.out.println("The operation was done successfully!");
            }
            case "Car" -> {
                if (adminController.addCar(dividedCommend[2], Double.parseDouble(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), dividedCommend[5], Integer.parseInt(dividedCommend[6]), Boolean.parseBoolean(dividedCommend[7])))
                    System.out.println("The operation was done successfully!");
            }
            case "Bicycle" -> {
                if (adminController.addBicycle(dividedCommend[2], Double.parseDouble(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), dividedCommend[5], dividedCommend[6]))
                    System.out.println("The operation was done successfully!");
            }
            case "Edible" -> {
                if (adminController.addEdible(dividedCommend[2], Double.parseDouble(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), dividedCommend[5], dividedCommend[6]))
                    System.out.println("The operation was done successfully!");
            }
            default -> System.out.println("WRONG COMMEND LINE!");
        }
    }

    private void manageRemoveCommand(String[] dividedCommend) {
        if (adminController.removeProduct(dividedCommend[1]))
            System.out.println("The operation was done successfully!");
        else
            System.out.println("There is no product with this ID !");
    }

    private void manageEditCommandType(String[] dividedCommend) {
        switch (dividedCommend[1]) {
            case "name" -> {
                if (adminController.editProductName(dividedCommend[2], dividedCommend[3]))
                    System.out.println("The operation was done successfully!");
                else
                    System.out.println("There is no product with this ID !");
            }
            case "stock" -> {
                if (adminController.editProductStock(dividedCommend[2], Integer.parseInt(dividedCommend[3])))
                    System.out.println("The operation was done successfully!");
                else
                    System.out.println("There is no product with this ID !");
            }
            case "price" -> {
                if (adminController.editProductPrice(dividedCommend[2], Integer.parseInt(dividedCommend[3])))
                    System.out.println("The operation was done successfully!");
                else
                    System.out.println("There is no product with this ID !");
            }
            default -> System.out.println("WRONG COMMEND LINE!");
        }
    }

    private void manageShowCommandType(String[] dividedCommend) {
        switch (dividedCommend[1]) {
            case "Customers" -> {
                for (Customer element : this.adminController.showCustomerInfo()) {
                    System.out.println(element.toString());
                }
            }
            case "RegistrationRequests" -> {
                for (Customer element : this.adminController.showRegistrationRequest()) {
                    System.out.println(element.toString());
                }
            }
            case "CommentRequests" -> {
                for (Comment element : this.adminController.showCommentRequest()) {
                    System.out.println(element.toString());
                }
            }
            case "CreditRequests" -> {
                for (CreditIncreaseRequest element : this.adminController.showCreditIncreaseRequest()) {
                    System.out.println(element.toString());
                }
            }
            case "Products" -> {
                for (Product element : this.adminController.showProductList()) {
                    System.out.println(element.toString());
                }
            }
            default -> System.out.println("WRONG COMMEND LINE!");
        }
    }

    private void manageAcceptCommandType(String[] dividedCommend) {
        switch (dividedCommend[1]) {
            case "Registration" -> {
                if (this.adminController.acceptRegistrationRequest(Integer.parseInt(dividedCommend[2])))
                    System.out.println("The operation was done successfully!");
                else
                    System.out.println("WRONG INDEX!");
            }
            case "Comment" -> {
                if (this.adminController.acceptCommentRequest(Integer.parseInt(dividedCommend[2])))
                    System.out.println("The operation was done successfully!");
                else
                    System.out.println("WRONG INDEX!");
            }
            case "CreditIncrease" -> {
                if (this.adminController.acceptCreditIncreaseRequest(Integer.parseInt(dividedCommend[2])))
                    System.out.println("The operation was done successfully!");
                else
                    System.out.println("WRONG INDEX!");
            }
            default -> System.out.println("WRONG COMMEND LINE!");
        }
    }

    private void manageRejectCommandType(String[] dividedCommend) {
        switch (dividedCommend[1]) {
            case "Registration" -> {
                if (this.adminController.rejectRegistrationRequest(Integer.parseInt(dividedCommend[2])))
                    System.out.println("The operation was done successfully!");
                else
                    System.out.println("WRONG INDEX!");
            }
            case "Comment" -> {
                if (this.adminController.rejectCommentRequest(Integer.parseInt(dividedCommend[2])))
                    System.out.println("The operation was done successfully!");
                else
                    System.out.println("WRONG INDEX!");
            }
            case "CreditIncrease" -> {
                if (this.adminController.rejectCreditIncreaseRequest(Integer.parseInt(dividedCommend[2])))
                    System.out.println("The operation was done successfully!");
                else
                    System.out.println("WRONG INDEX!");
            }
            default -> System.out.println("WRONG COMMEND LINE!");
        }
    }
    private void manageCustomerDiscountCommandType(String[] dividedCommend){
        switch (dividedCommend[1]) {
            case "PRICE" -> {
                if (adminController.addNewDiscountCodeAbove10000$(Double.parseDouble(dividedCommend[2]), dividedCommend[3], Integer.parseInt(dividedCommend[4]), Integer.parseInt(dividedCommend[5])))
                    System.out.println("The operation was done successfully!");
            }
            case "PURCHASE" -> {
                if (adminController.addNewDiscountCodeAbove5Purchase(Double.parseDouble(dividedCommend[2]), dividedCommend[3], Integer.parseInt(dividedCommend[4]), Integer.parseInt(dividedCommend[5])))
                    System.out.println("The operation was done successfully!");
            }
            default -> System.out.println("WRONG COMMEND LINE!");
        }
    }
    private void manageProductDiscountCommandType(String[] dividedCommend){
        switch(dividedCommend[1]){
            case "add" ->{
                if(adminController.addDiscountToProductByProductID(dividedCommend[2],Double.parseDouble(dividedCommend[3])))
                    System.out.println("The operation was done successfully!");
                else
                    System.out.println("The product ID is incorrect or invalid!");
            }
            case "remove" ->{
                if(adminController.removeDiscountFromProductByProductID(dividedCommend[2]))
                    System.out.println("The operation was done successfully!");
                else
                    System.out.println("The product ID is incorrect or invalid!");
            }
        }
    }
}