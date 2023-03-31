package model.products;

public class Car extends Vehicle {
    private int engineCapacity;
    private boolean isAutomatic;

    public Car( String productName, double productPrice, int numOfProduct, String manufactureCompany, int engineCapacity, boolean isAutomatic) {
        super(productName, productPrice, numOfProduct, manufactureCompany);
        this.engineCapacity = engineCapacity;
        this.isAutomatic = isAutomatic;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public boolean isAutomatic() {
        return isAutomatic;
    }

    public void setAutomatic(boolean automatic) {
        isAutomatic = automatic;
    }

    public String toString() {
        return super.toString() +
                "engine capacity : " + engineCapacity + "\n" +
                "is automatic : " + isAutomatic + "\n";
    }
}
