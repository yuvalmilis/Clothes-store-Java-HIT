package domain.customers;

import domain.entities.Entity;

// מחלקת הבסיס - כל סוגי הלקוחות ירשו ממנה
public abstract class Customer extends Entity {

    protected String name;
    protected String phone;
    protected double discountRate;

    protected Customer(String id, String branchId, String name, String phone, double discountRate) {
        super(id, branchId);
        this.name = name;
        this.phone = phone;
        this.discountRate = discountRate;
    }

    // מתודות מופשטות שכל בן חייב לממש
    public abstract String getCustomerType();

    // לוגיקה משותפת - חישוב מחיר אחרי הנחה
    public double applyDiscount(double price) {
        return price * (1 - discountRate);
    }

    @Override
    // המרה לאובייקט נתונים פשוט עבור ה-JSON
    public CustomerData toData() { return new CustomerData(id, getBranchId(), name, phone, getCustomerType(), discountRate); }

    // הפונקציה החכמה שלך: יוצרת אובייקט לוגי מתוך נתוני הקובץ
    public static Customer fromData(CustomerData data) {
        return switch (data.type) {
            case "VIP" -> new VIPCustomer(data.id, data.branchId, data.name, data.phone, data.discountValue);
            case "RETURNING" -> new ReturningCustomer(data.id, data.branchId, data.name, data.phone, data.discountValue);
            case "NEW" -> new NewCustomer(data.id, data.branchId, data.name, data.phone, data.discountValue);
            default -> throw new IllegalArgumentException("Unknown customer type: " + data.type);
        };
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return getCustomerType() + " [ID=" + id + ", BranchId=" + branchId + ", Name=" + name + ", Discount=" + (discountRate * 100) + "%]";
    }
}