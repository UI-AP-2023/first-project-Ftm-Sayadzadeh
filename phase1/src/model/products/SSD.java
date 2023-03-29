package model.products;

public class SSD extends InformationStorageEquipment {
    private double readSpeed;
    private double writeSpeed;

    public SSD(String productCategory, String productName, int productPrice, int numOfProduct, double weight, String dimensions, int capacity, double readSpeed, double writeSpeed) {
        super(productCategory, productName, productPrice, numOfProduct, weight, dimensions, capacity);
        this.readSpeed = readSpeed;
        this.writeSpeed = writeSpeed;
    }

    public double getReadSpeed() {
        return readSpeed;
    }

    public void setReadSpeed(double readSpeed) {
        this.readSpeed = readSpeed;
    }

    public double getWriteSpeed() {
        return writeSpeed;
    }

    public void setWriteSpeed(double writeSpeed) {
        this.writeSpeed = writeSpeed;
    }

    @Override
    public String toString() {
        return super.toString() +
                "read speed : " + readSpeed + "\n" +
                "write speed : " + writeSpeed + "\n";
    }
}
