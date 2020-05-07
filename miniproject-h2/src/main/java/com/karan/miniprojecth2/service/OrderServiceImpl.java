package com.karan.miniprojecth2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karan.miniprojecth2.dao.OrderDAO;
import com.karan.miniprojecth2.model.Order;

@Service(value = "orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;
	@Override
	public List<Order> getAllOrders() throws Exception {
		List<Order> orderList = orderDAO.getAllOrders();
		if (orderList.isEmpty())
			throw new Exception("Order List is empty!!!");
		
		return orderList;
	}

}
