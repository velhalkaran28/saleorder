package com.karan.miniprojecth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karan.miniprojecth2.dao.CustomerLoginDAO;
import com.karan.miniprojecth2.model.Customer;
import com.karan.miniprojecth2.model.CustomerPrincipal;

@Service
@Transactional
public class CustomerLoginService implements UserDetailsService {

	@Autowired
	private CustomerLoginDAO customerLoginDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerLoginDAO.findByUsername(username);
		System.out.println("Customer Name:   "+customer.getName()+" PASS:  "+customer.getPassword());
		if (customer == null)
			throw new UsernameNotFoundException("404 Not found customer..");
		return new CustomerPrincipal(customer);
	}

}
