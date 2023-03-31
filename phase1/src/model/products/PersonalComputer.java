package model.products;

public class PersonalComputer extends Digital {
    private String CPUModel;
    private int RAMMemory;

    public PersonalComputer(String productName, double productPrice, int numOfProduct, double weight, String dimensions, String CPUModel, int RAMMemory) {
        super( productName, productPrice, numOfProduct, weight, dimensions);
        this.CPUModel = CPUModel;
        this.RAMMemory = RAMMemory;
    }

    public String getCPUModel() {
        return CPUModel;
    }

    public void setCPUModel(String CPUModel) {
        this.CPUModel = CPUModel;
    }

    public int getRAMMemory() {
        return RAMMemory;
    }

    public void setRAMMemory(int RAMMemory) {
        this.RAMMemory = RAMMemory;
    }

    @Override
    public String toString() {
        return super.toString() +
                "CPU model : " + CPUModel + "\n" +
                "RAM memory : " + RAMMemory + "\n";
    }
}
