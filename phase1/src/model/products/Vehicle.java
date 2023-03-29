package model.products;

abstract public class Vehicle extends Product {
    private String manufactureCompanyName;

    public Vehicle(String productCategory, String productName, int productPrice, int numOfProduct, String manufactureCompanyName) {
        super(productCategory, productName, productPrice, numOfProduct);
        this.manufactureCompanyName = manufactureCompanyName;
    }

    public String getManufactureCompanyName() {
        return manufactureCompanyName;
    }

    public void setManufactureCompanyName(String manufactureCompanyName) {
        this.manufactureCompanyName = manufactureCompanyName;
    }

    @Override
    public String toString() {
        return super.toString() +
                "manufacture company : " + manufactureCompanyName + "\n";
    }
}
