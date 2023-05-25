package model.products;

abstract public class Digital extends Product implements Discountable {
    private double weight;
    private String dimensions;
    private double discountPercent;
    private static int digitalCounter = 1;

    public Digital( String subcategory , String productName, double productPrice, int numOfProduct, double weight, String dimensions) {
        super(subcategory, ProductCategory.Digital , productName, productPrice, numOfProduct);
        this.weight = weight;
        this.dimensions = dimensions;
    }

    public static int getDigitalCounter() {
        return digitalCounter;
    }

    public static void setDigitalCounter(int digitalCounter) {
        Digital.digitalCounter = digitalCounter;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public String toString() {
        return super.toString() +
                "weight : " + weight + "\n" +
                "dimensions : " + dimensions + "\n";
    }

    @Override
    public void addDiscountToProduct(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public void removeDiscountFromProduct() {
        this.discountPercent = 0;
    }
}