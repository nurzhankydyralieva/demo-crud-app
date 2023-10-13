package com.example.democrudapp.repository;

import com.example.democrudapp.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository repository;
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
    public void testEmployeeRepository() {
        Employee saved = repository.save(employee);

        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getId()).isGreaterThan(0);
    }

    @Test
    public void findAllEmployeeRepository() {
        List<Employee> allEmployee = repository.findAll();

        Assertions.assertThat(allEmployee).isNotNull();
        Assertions.assertThat(allEmployee.size()).isEqualTo(2);
    }

    @Test
    public void findByNameEmployeeRepository() {
        Employee saved = repository.findByFirstName(employee.getFirstName()).orElseThrow();

        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getId()).isGreaterThan(0);
    }

    @Test
    public void updateEmployeeRepository() {
        Employee update = repository.findById(employee.getId()).get();
        update.setFirstName("New user");
        update.setLastName("New user last name");

        Employee updatedEmployee = repository.save(update);
        Assertions.assertThat(updatedEmployee.getFirstName()).isNotNull();
        Assertions.assertThat(updatedEmployee.getLastName()).isNotNull();

    }
    @Test
    public void deleteEmployeeRepository() {
       repository.deleteById(employee.getId());
        Optional<Employee> returned = repository.findById(employee.getId());

        Assertions.assertThat(returned).isEmpty();
    }

}