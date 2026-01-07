package domain.users;

public class Cashier extends Employee {

    public Cashier(String id, String branchId, String name, String phone) {
        super(id, branchId, name, phone);
    }

    @Override
    public String getRole() {
        return "Cashier";
    }

    @Override
    protected String getExtra() {
        return getBranchId();
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "id='" + id + '\'' +
                ", branchId='" + getBranchId() + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
