package model.products;

abstract public class Vehicle extends Product {
    private String manufactureCompany;
    private static int vehicleCounter = 1;

    public Vehicle(String productName, double productPrice, int numOfProduct, String manufactureCompany) {
        super(ProductCategory.Vehicle, productName, productPrice, numOfProduct);
        this.manufactureCompany = manufactureCompany;
    }

    public static int getVehicleCounter() {
        return vehicleCounter;
    }

    public static void setVehicleCounter(int vehicleCounter) {
        Vehicle.vehicleCounter = vehicleCounter;
    }

    public String getManufactureCompany() {
        return manufactureCompany;
    }

    public void setManufactureCompany(String manufactureCompany) {
        this.manufactureCompany = manufactureCompany;
    }

    @Override
    public String toString() {
        return super.toString() +
                "manufacture company : " + manufactureCompany + "\n";
    }
}
