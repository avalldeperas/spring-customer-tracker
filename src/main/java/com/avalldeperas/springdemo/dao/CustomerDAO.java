package com.avalldeperas.springdemo.dao;

import java.util.List;

import com.avalldeperas.springdemo.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();
	public void save(Customer customer);
	public Customer getCustomer(int id);
	public void deleteCustomer(int id);
	
}
