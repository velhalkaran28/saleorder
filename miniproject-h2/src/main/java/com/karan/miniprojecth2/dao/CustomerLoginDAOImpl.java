package com.karan.miniprojecth2.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.karan.miniprojecth2.entity.CustomerEntity;
import com.karan.miniprojecth2.model.Customer;

@Repository(value = "customerLoginDao")
public class CustomerLoginDAOImpl implements CustomerLoginDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Customer findByUsername(String userName) {
		System.out.println("select c from CustomerEntity c where c.name='"+userName+"'");
		Query q = entityManager.createQuery("select c from CustomerEntity c where c.name='"+userName+"'");
		CustomerEntity ce = (CustomerEntity) q.getSingleResult();
		Customer customer = new Customer();
		customer.setAddress(ce.getAddress());
		customer.setEmailId(ce.getEmailId());
		customer.setName(ce.getName());
		customer.setPassword(ce.getPassword());
		customer.setPhoneNumber(ce.getPhoneNumber());
		return customer;
	}

}
