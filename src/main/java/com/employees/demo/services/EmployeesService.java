package com.employees.demo.services;

import java.util.Optional;

import com.employees.demo.entities.Employee;
import com.employees.demo.repositories.IEmployeeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeesService {
    @Autowired
    private IEmployeeRepo repo = null;
    
    public EmployeesService(IEmployeeRepo repository) {
        this.repo = repository;
    }

    public Iterable<Employee> getAll(){
        return this.repo.findAll();
    }

    public Employee findById(int id){
        Optional<Employee> find = this.repo.findById(id);
        if(find.isPresent()){
            return find.get();
        }
        return null;
    }

    public Employee save(Employee employee){
        return this.repo.save(employee);
    }

    public void delete(Employee employee){
        this.repo.delete(employee);
    }

    


}
