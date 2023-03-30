package model.products;

public class SSD extends InformationStorageEquipment {
    private int readSpeed;
    private int writeSpeed;

    public SSD(String productName, int productPrice, int numOfProduct, double weight, String dimensions, int capacity, int readSpeed, int writeSpeed) {
        super( productName, productPrice, numOfProduct, weight, dimensions, capacity);
        this.readSpeed = readSpeed;
        this.writeSpeed = writeSpeed;
    }

    public int getReadSpeed() {
        return readSpeed;
    }

    public void setReadSpeed(int readSpeed) {
        this.readSpeed = readSpeed;
    }

    public int getWriteSpeed() {
        return writeSpeed;
    }

    public void setWriteSpeed(int writeSpeed) {
        this.writeSpeed = writeSpeed;
    }

    @Override
    public String toString() {
        return super.toString() +
                "read speed : " + readSpeed + "\n" +
                "write speed : " + writeSpeed + "\n";
    }
}
