package com.example.demo.Repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String>, CustomRepo<Employee> {

    @Override
    List<Employee> findAllByEmailAddressAndLastName();

    @Override
    List<Employee> findAllByFirstNameAndLastName();

    @Override
    List<Employee> findAllByLastNameAndOrderByFirstNameAsc();

    @Override
    List<Employee> findAllByFirstNameIgnoreCase();
}
