package com.employees.demo.controllers;

import com.employees.demo.entities.Employee;
import com.employees.demo.services.EmployeesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EmployeesController {
    @Autowired
    private EmployeesService service = null;

    public EmployeesController(EmployeesService service) {
        this.service = service;
    }

    @GetMapping("/employees")
    public ResponseEntity<Iterable<Employee>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        if (employee.getEmpNo() > 0 && employee != null) {
            return new ResponseEntity<>(this.service.save(employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(employee, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id") String id) {
        try {
            Employee emp = this.service.findById(Integer.parseInt(id));
            if (emp != null) {
                return new ResponseEntity<>(emp, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        Employee emp = this.service.findById(Integer.parseInt(id));
        if (emp != null) {
            this.service.delete(emp);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/employees")
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        Employee emp = this.service.findById(employee.getEmpNo());
        if (emp != null) {
            emp = employee;
            this.service.save(emp);
            return new ResponseEntity<>(emp, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}