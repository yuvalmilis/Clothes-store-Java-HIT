package domain.customers;

public class ReturningCustomer extends Customer {
    public ReturningCustomer(String id, String branchId, String name, String phone, double discount) {
        super(id, branchId, name, phone, discount);
    }
    @Override
    public String getCustomerType() { return "RETURNING"; }
}