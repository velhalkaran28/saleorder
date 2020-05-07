package com.karan.miniprojecth2.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.karan.miniprojecth2.entity.OrderEntity;
import com.karan.miniprojecth2.model.Order;
import com.karan.miniprojecth2.model.Product;

@Repository(value = "orderDao")
public class OrderDAOImpl implements OrderDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Order> getAllOrders() {
		Query q = entityManager.createQuery("select o from OrderEntity o");
		List<Order> orderList = new ArrayList<Order>();
		List<OrderEntity> orderEntityList = q.getResultList();
		for (OrderEntity orderEntity : orderEntityList) {
			Order order = new Order();
			order.setDateOfDelivery(orderEntity.getDateOfDelivery());
			order.setDateOfOrder(orderEntity.getDateOfOrder());
			order.setOrderId(orderEntity.getOrderId());
			order.setOrderNumber(orderEntity.getOrderNumber());
			Product p = new Product();
			p.setCategory(orderEntity.getProductEntity().getCategory());
			p.setName(orderEntity.getProductEntity().getName());
			p.setPrice(orderEntity.getProductEntity().getPrice());
			p.setProductId(orderEntity.getProductEntity().getProductId());
			p.setQuantity(orderEntity.getProductEntity().getQuantity());
			order.setProduct(p);
			order.setQuantity(orderEntity.getQuantity());
			order.setTotalPrice(orderEntity.getTotalPrice());
			
			orderList.add(order);
		}
		return orderList;
	}

}
