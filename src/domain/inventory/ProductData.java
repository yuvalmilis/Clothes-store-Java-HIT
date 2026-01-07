package domain.inventory;

import domain.entities.EntityData;

public class ProductData extends EntityData {
    public String name;
    public String category;
    public double price;
    public int quantity;

    public ProductData() {}

    public ProductData(String id, String branchId, String name, String category, double price, int quantity) {
        super(id, branchId);
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    // יצירה מ-Data (טעינה מה-JSON)
    public Product fromData() {
        return new Product(id, branchId, name, category, price, quantity);
    }
}