package com.tiyssa.service;

import java.util.List;

import com.tiyssa.entity.Employee;

public interface IEmployeeService {
     List<Employee> getAllEmployees();
     Employee getEmployeeById(int employee_id);
     boolean addEmployee(Employee employee);
     void updateEmployee(Employee employee);
     void deleteEmployee(int employee_id);
}
