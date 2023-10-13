package com.example.democrudapp.service;

import com.example.democrudapp.model.Employee;
import com.example.democrudapp.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository repository;
    @InjectMocks
    private EmployeeServiceImpl service;
    private Employee employee;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        employee = new Employee(1L, "Tom", "Smith", "tom@gmail.com");
        employee2 = new Employee(2L, "Osman", "Lass", "lass@gmail.com");
        repository.save(employee);
        repository.save(employee2);
    }

    @Test
    public void saveEmployee() {
        when(repository.save(Mockito.any(Employee.class))).thenReturn(employee);
        service.saveEmployee(employee);
    }
}
