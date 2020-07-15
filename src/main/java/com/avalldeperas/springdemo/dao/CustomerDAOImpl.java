package com.avalldeperas.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.avalldeperas.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		Session session = sessionFactory.getCurrentSession();
			
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
		
		List<Customer> customers = query.getResultList();
		
		return customers;
	}

	@Override
	public void save(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("customer id = " + customer.getId());
		session.saveOrUpdate(customer); // if customer has id, updates. otherwise inserts.
		System.out.println("Customer saved or updated!");
	}

	@Override
	public Customer getCustomer(int id) {
		System.out.println("Getting a customer with id = " + id);
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, id);
		System.out.println("customer retrieved from DB: " + customer);
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();		
		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id);
		query.executeUpdate();
		
		System.out.println("Customer deleted correctly!");
	}
	
	

}
