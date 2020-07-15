package com.avalldeperas.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.avalldeperas.springdemo.entity.Customer;
import com.avalldeperas.springdemo.services.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		List<Customer> customer = customerService.getCustomers();
		theModel.addAttribute("customers", customer);
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
		System.out.println("customer from form: " + customer);
		
		customerService.save(customer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String updateCustomer(@RequestParam("customerId") int id, Model model) {
		if(id <= 0) return "redirect:/customer/list"; 
		
		Customer customer = customerService.getCustomer(id);
		if(customer == null) return "redirect:/customer/list";
		
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
}
