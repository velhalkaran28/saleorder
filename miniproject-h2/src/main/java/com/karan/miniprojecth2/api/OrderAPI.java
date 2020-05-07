package com.karan.miniprojecth2.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.karan.miniprojecth2.model.Order;
import com.karan.miniprojecth2.service.OrderService;

@CrossOrigin
@RestController
@RequestMapping("OrderAPI")
public class OrderAPI {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("getAllOrders")
	public ResponseEntity<List<Order>> getAllOrders() {
		try {
			List<Order> orderList = orderService.getAllOrders();
			return new ResponseEntity<List<Order>>(orderList,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
