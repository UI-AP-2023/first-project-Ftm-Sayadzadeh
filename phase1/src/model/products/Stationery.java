package model.products;

abstract public class Stationery extends Product {
    private String manufactureCountry;
    private static int stationeryCounter = 1;

    public Stationery(String subcategory, String productName, double productPrice, int numOfProduct, String manufactureCountry) {
        super(subcategory, ProductCategory.Stationery, productName, productPrice, numOfProduct);
        this.manufactureCountry = manufactureCountry;
    }

    public static int getStationeryCounter() {
        return stationeryCounter;
    }

    public static void setStationeryCounter(int stationeryCounter) {
        Stationery.stationeryCounter = stationeryCounter;
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
