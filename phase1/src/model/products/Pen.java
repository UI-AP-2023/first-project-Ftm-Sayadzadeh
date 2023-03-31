package model.products;

public class Pen extends Stationery {
    private PenColor penColor;

    public Pen( String productName, double productPrice, int numOfProduct, String manufactureCountry, String penColor) {
        super( productName, productPrice, numOfProduct, manufactureCountry);
        this.penColor = PenColor.valueOf(penColor);
    }

    public PenColor getPenColor() {
        return penColor;
    }

    public void setPenColor(String penColor) {
        this.penColor = PenColor.valueOf(penColor);
    }

    public String toString() {
        return super.toString() +
                "pen color : " + penColor.toString() + "\n";
    }
}
