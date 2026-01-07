package domain.customers;

import domain.entities.EntityData;

public class CustomerData extends EntityData {

    public String name;
    public String phone;
    public String type;
    public double discountValue;

    public CustomerData() {}

    public CustomerData(String id, String branchId, String name, String phone, String type, double discountValue) {
        super(id, branchId);
        this.name = name;
        this.phone = phone;
        this.type = type;
        this.discountValue = discountValue;
    }

    public Customer fromData() {
        return switch (type) {
            case "VIP" -> new VIPCustomer(id, branchId, name, phone, discountValue);
            case "RETURNING" -> new ReturningCustomer(id, branchId, name, phone, discountValue);
            case "NEW" -> new NewCustomer(id, branchId, name, phone, discountValue);
            default -> throw new IllegalArgumentException("Unknown customer type: " + type);
        };
    }
}