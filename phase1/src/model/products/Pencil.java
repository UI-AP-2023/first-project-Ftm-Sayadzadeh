package model.products;

public class Pencil extends Stationery {
    private PencilType pencilType;

    public Pencil(String productCategory, String productName, int productPrice, int numOfProduct, String madeIn, String pencilType) {
        super(productCategory, productName, productPrice, numOfProduct, madeIn);
        this.pencilType = PencilType.valueOf(pencilType);
    }

    public PencilType getPencilType() {
        return pencilType;
    }

    public void setPencilType(String pencilType) {
        this.pencilType = PencilType.valueOf(pencilType);
    }

    public String toString() {
        return super.toString() +
                "pencil type : " + pencilType.toString() + "\n";
    }
}
