package domain.users;

import domain.entities.Entity;

public abstract class Employee extends Entity {

    protected String name;
    protected String phone;

    protected Employee(String id, String branchId, String name, String phone) {
        super(id, branchId);
        this.name = name;
        this.phone = phone;
    }

    public abstract String getRole();
    protected abstract String getExtra();

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }

    @Override
    public EmployeeData toData() {
        return new EmployeeData(id, getBranchId(), name, phone, getRole(), getExtra());
    }

    @Override
    public String toString() {
        return getRole() + " [ID=" + id + ", BranchId=" + branchId + ", Name=" + name + ", Phone=" + phone + ", Extra=" + getExtra() + "%]";
    }
}


