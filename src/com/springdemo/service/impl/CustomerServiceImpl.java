package com.springdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.dao.CustomerDao;
import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDao.getCustomers();
	}

	@Override
	public void saveCustomer(Customer customer) {
		customerDao.saveCustomer(customer);
	}

	@Override
	public Customer getCustomer(int customerId) {
		return customerDao.getCustomer(customerId);
	}

	@Override
	public void deleteCustomer(int customerId) {
		customerDao.deleteCustomer(customerId);
		
	}

}
