package domain.users;
import java.io.Serializable;
public abstract class Employee implements Serializable{
    private static final long serialVersionUID = 1L;

    protected String id;
    protected String name;
    protected String phone;

    protected Employee(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public abstract String getRole();
    protected abstract String getExtra();

    public EmployeeData toData() {
        EmployeeData data = new EmployeeData();
        data.id = id;
        data.name = name;
        data.phone = phone;
        data.role = getRole();
        data.extra = getExtra();
        return data;
    }

    public static Employee fromData(EmployeeData data) {
        return switch (data.role) {
            case "Cashier" -> new Cashier(data.id, data.name, data.phone, data.extra);
            case "Seller" -> new Seller(data.id, data.name, data.phone, data.extra);
            case "ShiftManager" -> new ShiftManager(data.id, data.name, data.phone, data.extra);
            default -> throw new IllegalArgumentException("Unknown role: " + data.role);
        };
    }
}


