package view;

import controller.AdminController;

import java.util.Scanner;

public class AdminPanel {               //setter getter?
    private AdminController adminController;
    private Scanner input;

    public AdminPanel() {
        this.adminController = new AdminController();
        this.input = new Scanner(System.in);
    }

    public void adminCommandPage() {
        System.out.println("Enter the command line ! ");
        String commandLine = input.nextLine();
        String[] dividedCommend = commandLine.split(" ");
        switch (dividedCommend[0]) {
            case "Add":
                this.addCommandType(dividedCommend);
                this.adminCommandPage();
                break;
            case "Remove":
                if (adminController.removeProduct(dividedCommend[2]))
                    System.out.println("The operation was done successfully!");
                else
                    System.out.println("There is no product with this ID !");
                this.adminCommandPage();
                break;
            case "Edit":
                this.editCommandType(dividedCommend);
                this.adminCommandPage();
                break;
            case "ShowCustomers" :
//                this.adminController.showCustomerInfo().toString(); //I need to check it and write for each loop
                this.adminCommandPage();
                break;
            case "help":
                System.out.println(helpCommand());
                this.adminCommandPage();
                break;
            case "MainPage" :
                MainPage mainPage = new MainPage();
                mainPage.mainPage();
                break;
            case "EXIT" :
                System.exit(0);
            default:
                System.out.println("WRONG COMMEND LINE!");
                this.adminCommandPage();
                break;
        }
    }
    public String helpCommand(){
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
                   - Edit name productID newName
                   - Edit price productID newPrice
                   - Edit stock productID newStock
                   - ShowCustomers
                   - MainPage
                   - EXIT
                   """;
    }
    public void addCommandType(String[] dividedCommend){
        switch (dividedCommend[1]) {
            case "FlashMemory":
                if (adminController.addFlashMemory(dividedCommend[2], Integer.parseInt(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), Double.parseDouble(dividedCommend[5]), dividedCommend[6], Integer.parseInt(dividedCommend[7]), dividedCommend[8]))
                    System.out.println("The operation was done successfully!");
                break;
            case "SSD":
                if (adminController.addSSD(dividedCommend[2], Integer.parseInt(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), Double.parseDouble(dividedCommend[5]), dividedCommend[6], Integer.parseInt(dividedCommend[7]), Integer.parseInt(dividedCommend[8]), Integer.parseInt(dividedCommend[9])))
                    System.out.println("The operation was done successfully!");
                break;
            case "PC":
                if (adminController.addPersonalComputer(dividedCommend[2], Integer.parseInt(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), Double.parseDouble(dividedCommend[5]), dividedCommend[6], dividedCommend[7], Integer.parseInt(dividedCommend[8])))
                    System.out.println("The operation was done successfully!");
                break;
            case "Pencil":
                if (adminController.addPencil(dividedCommend[2], Integer.parseInt(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), dividedCommend[5], dividedCommend[6]))
                    System.out.println("The operation was done successfully!");
                break;
            case "Pen":
                if (adminController.addPen(dividedCommend[2], Integer.parseInt(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), dividedCommend[5], dividedCommend[6]))
                    System.out.println("The operation was done successfully!");
                break;
            case "NoteBook":
                if (adminController.addNoteBook(dividedCommend[2], Integer.parseInt(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), dividedCommend[5], Integer.parseInt(dividedCommend[6]), dividedCommend[7]))
                    System.out.println("The operation was done successfully!");
                break;
            case "Car":
                if (adminController.addCar(dividedCommend[2], Integer.parseInt(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), dividedCommend[5], Integer.parseInt(dividedCommend[6]), Boolean.parseBoolean(dividedCommend[7])))
                    System.out.println("The operation was done successfully!");
                break;
            case "Bicycle":
                if (adminController.addBicycle(dividedCommend[2], Integer.parseInt(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), dividedCommend[5], dividedCommend[6]))
                    System.out.println("The operation was done successfully!");
                break;
            case "Edible":
                if (adminController.addEdible(dividedCommend[2], Integer.parseInt(dividedCommend[3]), Integer.parseInt(dividedCommend[4]), dividedCommend[5], dividedCommend[6]))
                    System.out.println("The operation was done successfully!");
                break;
            default:
                System.out.println("WRONG COMMEND LINE!");
                break;
        }
    }
    public void editCommandType(String[] dividedCommend ){
        switch (dividedCommend[1]) {
            case "name":
                if (adminController.editProductName(dividedCommend[2], dividedCommend[3]))
                    System.out.println("The operation was done successfully!");
                else
                    System.out.println("There is no product with this ID !");
            case "stock":
                if (adminController.editProductStock(dividedCommend[2], Integer.parseInt(dividedCommend[3])))
                    System.out.println("The operation was done successfully!");
                else
                    System.out.println("There is no product with this ID !");
            case "price":
                if (adminController.editProductPrice(dividedCommend[2], Integer.parseInt(dividedCommend[3])))
                    System.out.println("The operation was done successfully!");
                else
                    System.out.println("There is no product with this ID !");
            default:
                System.out.println("WRONG COMMEND LINE!");
                break;
        }
    }

}
