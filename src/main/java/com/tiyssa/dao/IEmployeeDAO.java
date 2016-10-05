package com.tiyssa.dao;
import java.util.List;
import com.tiyssa.entity.Employee;
public interface IEmployeeDAO {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int employeeId);
    boolean addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(int employeeId);
}
 