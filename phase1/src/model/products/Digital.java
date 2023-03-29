package model.products;

abstract public class Digital extends Product {
    private double weight;
    private String dimensions;

    public Digital(String productCategory, String productName, int productPrice, int numOfProduct, double weight, String dimensions) {
        super(productCategory, productName, productPrice, numOfProduct);
        this.weight = weight;
        this.dimensions = dimensions;
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

    @Override
    public String toString() {
        return super.toString() +
                "weight : " + weight + "\n" +
                "dimensions : " + dimensions + "\n";
    }
}
