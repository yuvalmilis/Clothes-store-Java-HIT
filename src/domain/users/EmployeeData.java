package domain.users;

import domain.entities.EntityData;

public class EmployeeData extends EntityData {
    public String name;
    public String phone;
    public String role;
    public String extra;

    public EmployeeData() { super(); }

    public EmployeeData(String id, String branchId, String name, String phone, String role, String extra) {
        super(id, branchId);
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.extra = extra;
    }

    @Override
    public Employee fromData() {
        return switch (role) {
            case "Cashier" -> new Cashier(id, name, phone, extra);
            case "Seller" -> new Seller(id, branchId, name, phone, extra);
            case "ShiftManager" -> new ShiftManager(id, branchId, name, phone, extra);
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        };
    }
}
