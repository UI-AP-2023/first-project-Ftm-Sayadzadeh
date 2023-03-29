package model.products;

public class Pen extends Stationery {
    private PenColor penColor;

    public Pen(String productCategory, String productName, int productPrice, int numOfProduct, String madeIn, String penColor) {
        super(productCategory, productName, productPrice, numOfProduct, madeIn);
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
