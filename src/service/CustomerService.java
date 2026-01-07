package service;

import domain.customers.Customer;
import domain.customers.CustomerData;

import repository.Repository;

public class CustomerService extends EntityService<Customer, CustomerData> {

    public CustomerService(Repository<Customer, CustomerData> repository, String name) {
        super(repository, name);
    }
}