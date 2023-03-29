package model.products;

public class Bicycle extends Vehicle {
    private BicycleType bicycleType;

    public Bicycle(String productCategory, String productName, int productPrice, int numOfProduct, String manufactureCompanyName, String bicycleType) {
        super(productCategory, productName, productPrice, numOfProduct, manufactureCompanyName);
        this.bicycleType = BicycleType.valueOf(bicycleType);
    }

    public BicycleType getBicycleType() {
        return bicycleType;
    }

    public void setBicycleType(String bicycleType) {
        this.bicycleType = BicycleType.valueOf(bicycleType);
    }

    public String toString() {
        return super.toString() +
                "bicycle type : " + bicycleType.toString() + "\n";
    }
}
