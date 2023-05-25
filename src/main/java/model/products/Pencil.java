package model.products;

public class Pencil extends Stationery implements Discountable{
    private PencilType pencilType;
    private double discountPercent;

    public Pencil(String productName, double productPrice, int numOfProduct, String manufactureCountry, String pencilType) {
        super("Pencil", productName, productPrice, numOfProduct, manufactureCountry);
        this.pencilType = PencilType.valueOf(pencilType);
    }

    public PencilType getPencilType() {
        return pencilType;
    }

    public void setPencilType(String pencilType) {
        this.pencilType = PencilType.valueOf(pencilType);
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String toString() {
        return super.toString() +
                "pencil type : " + pencilType.toString() + "\n";
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
