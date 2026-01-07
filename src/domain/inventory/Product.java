package domain.inventory;

import domain.entities.Entity;

public class Product extends Entity {

    private final String name;
    private final String category;
    private final double price;
    private int quantity;

    public Product(String id, String branchId, String name, String category, double price, int quantity) {
        super(id, branchId);
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // לוגיקה לעדכון מלאי (שימושי במכירה/קנייה)
    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public boolean reduceQuantity(int amount) {
        if (this.quantity >= amount) {
            this.quantity -= amount;
            return true;
        }
        return false; // אין מספיק במלאי
    }

    @Override
    // המרה ל-Data (לשמירה ב-JSON)
    public ProductData toData() {
        return new ProductData(id, getBranchId(), name, category, price, quantity);
    }

    @Override
    // הדרכים השונות לזהות את המוצר
    public boolean compare(String value) {
        return getCategory().equals(value) || getName().equals(value) || getId().equals(value);
    }

    @Override
    public String toString() {
        return String.format("Product [ID: %s, BranchId: %s, Name: %s, Category: %s, Price: %.2f, Stock: %d]",
                id, branchId, name, category, price, quantity);
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

}