package service;

import domain.customers.Customer;
import domain.customers.CustomerData;
import repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void addCustomer(Customer customer) {
        List<CustomerData> data = repository.load();

        for (CustomerData d : data) {
            if (d.id.equals(customer.getId())) {
                System.out.println("Error: Customer with ID " + d.id + " already exists!");
                return;
            }
        }

        data.add(customer.toData());
        repository.save(data);
        System.out.println("Customer added successfully.");
    }

    public void deleteCustomer(String id) {
        List<CustomerData> data = repository.load();
        boolean removed = data.removeIf(d -> d.id.equals(id));

        if (removed) {
            repository.save(data);
            System.out.println("Customer with ID " + id + " deleted.");
        } else {
            System.out.println("Customer with ID " + id + " not found.");
        }
    }

    public List<Customer> getAllCustomers() {
        List<CustomerData> data = repository.load();
        List<Customer> customers = new ArrayList<>();
        for (CustomerData d : data) {
            customers.add(Customer.fromData(d));
        }
        return customers;
    }
}