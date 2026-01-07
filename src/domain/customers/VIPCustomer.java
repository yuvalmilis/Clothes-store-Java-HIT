package domain.customers;

public class VIPCustomer extends Customer {
    public VIPCustomer(String id, String branchId, String name, String phone, double discount) {
        super(id, branchId, name, phone, discount);
    }
    @Override
    public String getCustomerType() { return "VIP"; }
}