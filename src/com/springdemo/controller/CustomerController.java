package com.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model model) {
		// get customers from service
		List<Customer> customers = customerService.getCustomers();

		// add customer to the model
		model.addAttribute("customers", customers);
		return "list-customers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.saveCustomer(customer);
		// try using redirect:/customer/list
		return "redirect:/customer/list";
	}

	@GetMapping("/showFormForUpdate")
	public String updateCustomer(@RequestParam("customerId") int customerId, Model model) {

		// get customer from database
		Customer customer = customerService.getCustomer(customerId);

		// set customer as model attribute to pre populate form
		model.addAttribute("customer", customer);

		return "customer-form";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int customerId, Model model) {
		// delete customer
		customerService.deleteCustomer(customerId);
		
		return "redirect:/customer/list";
	}
}
