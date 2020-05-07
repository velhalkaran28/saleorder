package com.karan.miniprojecth2.dao;

import com.karan.miniprojecth2.model.Customer;

public interface CustomerLoginDAO {
	public Customer findByUsername(String userName);
}
