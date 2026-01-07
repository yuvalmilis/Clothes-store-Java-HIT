package domain.customers;

public class NewCustomer extends Customer {
    public NewCustomer(String id, String branchId, String name, String phone, double discount) {
        super(id, branchId, name, phone, discount);
    }
    @Override
    public String getCustomerType() { return "NEW"; }
}