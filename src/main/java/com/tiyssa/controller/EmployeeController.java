package com.tiyssa.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.servlet.ModelAndView;

import com.tiyssa.entity.Employee;
import com.tiyssa.service.IEmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping("/")
	public ModelAndView home(ModelAndView mv) {
		mv.setViewName("index");
		return mv;
 	}

	@RequestMapping(value="/employee/{id}", method = RequestMethod.GET )
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) {
		Employee employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value= "/employee", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> employees = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@RequestMapping(value= "/employee", method = RequestMethod.POST)
	public ResponseEntity<Void> employeePerson(@RequestBody Employee employee, UriComponentsBuilder builder) {
        boolean flag = employeeService.addEmployee(employee);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/employee/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@RequestMapping(value="/employee/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	@RequestMapping(value="/employee/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> Employee(@PathVariable("id") Integer employeeId) {
		employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 