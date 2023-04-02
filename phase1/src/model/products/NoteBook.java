package model.products;

public class NoteBook extends Stationery {
    private int numOfSheets;
    private String paperType;

    public NoteBook(String productName, double productPrice, int numOfProduct, String manufactureCountry, int numOfSheets, String paperType) {
        super("NoteBook", productName, productPrice, numOfProduct, manufactureCountry);
        this.numOfSheets = numOfSheets;
        this.paperType = paperType;
    }

    public int getNumOfSheets() {
        return numOfSheets;
    }

    public void setNumOfSheets(int numOfSheets) {
        this.numOfSheets = numOfSheets;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String toString() {
        return super.toString() +
                "num of sheets : " + numOfSheets + "\n" +
                "paper type : " + paperType + "\n";
    }
}