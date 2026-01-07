package domain.users;

public class ShiftManager extends Employee {

    private String shift;

    public ShiftManager(String id, String branchId, String name, String phone, String shift) {
        super(id, branchId, name, phone);
        this.shift = shift;
    }

    @Override
    public String getRole() {
        return "ShiftManager";
    }

    @Override
    protected String getExtra() {
        return shift;
    }


    protected void loadExtra(String extra) {
        this.shift = extra;
    }

    @Override
    public String toString() {
        return "ShiftManager{" +
                "id='" + id + '\'' +
                ", branchId='" + getBranchId() + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", shift='" + shift + '\'' +
                '}';
    }
}
