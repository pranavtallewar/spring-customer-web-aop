package com.springdemo.dao.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.dao.CustomerDao;
import com.springdemo.dao.GenericDao;
import com.springdemo.entity.Customer;

@Repository
public class CustomerDaoImpl extends GenericDao implements CustomerDao {

	@Override
	public List<Customer> getCustomers() {

		// Create query
		Query<Customer> query = getSession().createQuery("from Customer", Customer.class);

		// get results from query
		List<Customer> customers = query.getResultList();
		return customers;
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		getSession().saveOrUpdate(customer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int customerId) {

		// get customer from session
		Customer customer = getSession().get(Customer.class, customerId);

		return customer;
	}

	@Override
	@Transactional
	public void deleteCustomer(int customerId) {
		// delete object with primary key
		Query query = getSession().createQuery("Delete from Customer where id= :customerId");
		query.setParameter("customerId", customerId);

		query.executeUpdate();
	}

}
