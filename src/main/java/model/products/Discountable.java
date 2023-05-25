package model.products;

public interface Discountable {
    void addDiscountToProduct(double discountPercent);
    void removeDiscountFromProduct();
}
