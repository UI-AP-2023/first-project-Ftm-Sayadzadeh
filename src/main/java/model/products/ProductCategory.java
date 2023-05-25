package model.products;

public enum ProductCategory {
    Digital(1001), Stationery(1002), Vehicle(1003), Edible(1004);
    private final int CategoryCode;

    ProductCategory(int categoryCode) {
        this.CategoryCode = categoryCode;
    }

    public int getCategoryCode() {
        return CategoryCode;
    }
}