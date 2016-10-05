package com.tiyssa.dao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.tiyssa.entity.Employee;

@Repository
public class EmployeeDAO implements IEmployeeDAO {

	@Autowired
	private HibernateTemplate  hibernateTemplate;

	@Override
	public Employee getEmployeeById(int employeeId) {
		return hibernateTemplate.get(Employee.class, employeeId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() {
		String hql = "FROM Employee as e ORDER BY e.employeeId";
		return (List<Employee>) hibernateTemplate.find(hql);
	}	

	@Override
	public boolean addEmployee(Employee employee) {
		hibernateTemplate.save(employee);
		return false;
	}

	@Override
	public void updateEmployee(Employee employee) {
		Employee record = getEmployeeById(employee.getEmployeeId());

		record.setFirstName(employee.getFirstName());
		record.setLastName(employee.getLastName());
		record.setEmail(employee.getEmail());
		record.setHomePhone(employee.getHomePhone());
		record.setCellPhone(employee.getCellPhone());
		record.setPassword(employee.getPassword());
		record.setActive(employee.getActive());

		hibernateTemplate.update(record);
	}
	@Override
	public void deleteEmployee(int employeeId) {
		hibernateTemplate.delete(getEmployeeById(employeeId));
	}
}
