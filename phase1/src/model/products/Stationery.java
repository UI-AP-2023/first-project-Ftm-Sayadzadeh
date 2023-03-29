package model.products;

abstract public class Stationery extends Product {
    private String manufactureCountry;

    public Stationery(String productCategory, String productName, int productPrice, int numOfProduct, String manufactureCountry) {
        super(productCategory, productName, productPrice, numOfProduct);
        this.manufactureCountry = manufactureCountry;
    }

    public String getManufactureCountry() {
        return manufactureCountry;
    }

    public void setManufactureCountry(String manufactureCountry) {
        this.manufactureCountry = manufactureCountry;
    }

    @Override
    public String toString() {
        return super.toString() +
                "manufacture country : " + manufactureCountry + "\n";
    }
}
