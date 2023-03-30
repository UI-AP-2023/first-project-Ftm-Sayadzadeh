package model.products;

public class Edible extends Product {
    private String productionDate;
    private String expirationDate;
    private static int edibleCounter = 1;

    public Edible(String productName, int productPrice, int numOfProduct, String productionDate, String expirationDate) {
        super(ProductCategory.Edible, productName, productPrice, numOfProduct);
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
    }

    public static int getEdibleCounter() {
        return edibleCounter;
    }

    public static void setEdibleCounter(int edibleCounter) {
        Edible.edibleCounter = edibleCounter;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String toString() {
        return super.toString() +
                "production date : " + productionDate + "\n" +
                "expiration date : " + expirationDate + "\n";
    }
}
