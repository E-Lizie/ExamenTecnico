package com.employees.demo.repositories;

import com.employees.demo.entities.Employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepo extends CrudRepository<Employee, Integer>{
    
}
