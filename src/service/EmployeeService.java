package service;

import domain.users.Employee;
import domain.users.EmployeeData;
import repository.Repository;

public class EmployeeService extends EntityService<Employee, EmployeeData> {

    public EmployeeService(Repository<Employee, EmployeeData> repository, String name) {
        super(repository, name);
    }
}
