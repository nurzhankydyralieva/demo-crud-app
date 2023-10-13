package com.example.democrudapp.service;

import com.example.democrudapp.model.Employee;
import com.example.democrudapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository repository;
    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.repository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employeeId= repository.findById(id);
        Employee foundId;
        if (employeeId.isPresent()) {
            foundId=employeeId.get();
        }else{
            throw new RuntimeException("Employee not found for id: " + id);
        }
        return foundId;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        this.repository.deleteById(id);
    }
}
