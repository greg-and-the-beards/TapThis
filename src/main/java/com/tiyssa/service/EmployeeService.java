package com.tiyssa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiyssa.dao.IEmployeeDAO;
import com.tiyssa.entity.Employee;
@Service
@Transactional
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeDAO employeeDAO;

	@Override
	public Employee getEmployeeById(int employeeId) {
		Employee obj = employeeDAO.getEmployeeById(employeeId);
		return obj;
	}	

	@Override
	public List<Employee> getAllEmployees(){
		return employeeDAO.getAllEmployees();
	}

	@Override
	public synchronized boolean addEmployee(Employee employee){
    	   employeeDAO.addEmployee(employee);
    	   return true;
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeDAO.updateEmployee(employee);
	}

	@Override
	public void deleteEmployee(int employeeId) {
		employeeDAO.deleteEmployee(employeeId);
	}
}
