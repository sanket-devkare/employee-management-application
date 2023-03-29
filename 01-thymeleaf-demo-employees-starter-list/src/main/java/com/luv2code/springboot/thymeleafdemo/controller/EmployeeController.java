package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	/**
	 * field for EmployeeService
	 */
	@Autowired
	private EmployeeService employeeService;
	
	
	/**
	 * add mapping for "/list"
	 * @param theModel
	 * @return
	 */
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		List<Employee> theEmployees = new ArrayList<>();
		theEmployees.addAll(employeeService.findAll());
		
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "list-employees";
	}
	
	@GetMapping("/showFormToAddEmployee")
	public String getFormToAddEmployee(Model model)
	{
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employeeForm";
	}
	
	@PostMapping("/addEmployee")
	public String addEmployee(@ModelAttribute("employee") Employee employee)
	{
		System.out.println("First Name : " + employee.getFirstName());
		System.out.println("Last Name : " + employee.getLastName());
		System.out.println("Email : " + employee.getEmail());
		
		employeeService.save(employee);
		
		return "redirect:/employees/list";
	}
	
	@GetMapping("/showFormToUpdateEmployee")
	public String showFormToUpdate(@RequestParam("employeeId") int id, Model model)
	{
		Employee employee = employeeService.findById(id);
		model.addAttribute("employee", employee);
		
		return "employeeForm";
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("employeeId") int id, Model model)
	{
		employeeService.deleteById(id);
		return "redirect:/employees/list";
	}
}









