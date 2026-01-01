package service;

import domain.users.Employee;
import domain.users.EmployeeData;
import repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void addEmployee(Employee employee) {
        List<EmployeeData> data = repository.load();

        for (EmployeeData d : data) {
            if (d.id.equals(employee.toData().id)) {
                System.out.println("Error: Employee with ID " + d.id + " already exists!");
                return;
            }
        }

        data.add(employee.toData());
        repository.save(data);
        System.out.println("Employee added successfully.");
    }

    public void deleteEmployee(String id) {
        List<EmployeeData> data = repository.load();
        boolean removed = data.removeIf(d -> d.id.equals(id));

        if (removed) {
            repository.save(data);
            System.out.println("Employee with ID " + id + " deleted.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    public List<Employee> getAllEmployees() {
        List<EmployeeData> data = repository.load();
        List<Employee> employees = new ArrayList<>();
        for (EmployeeData d : data) {
            employees.add(Employee.fromData(d));
        }
        return employees;
    }
}