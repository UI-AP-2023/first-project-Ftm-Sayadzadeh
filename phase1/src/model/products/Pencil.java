package model.products;

public class Pencil extends Stationery {
    private PencilType pencilType;

    public Pencil(String productName, int productPrice, int numOfProduct, String manufactureCountry, String pencilType) {
        super(productName, productPrice, numOfProduct, manufactureCountry);
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
