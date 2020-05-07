package com.karan.miniprojecth2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karan.miniprojecth2.dao.CartDAO;
import com.karan.miniprojecth2.model.PCart;

@Service(value = "cartService")
@Transactional
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDAO cartDAO;
	
	@Override
	public String addProductToCart(String emailId, Integer productId, Integer quantity) throws Exception {
		String res = cartDAO.addProductToCart(emailId, productId, quantity);
		if (res==null)
			throw new Exception("Cannot add prod to cart, something went wrong");
		return res;
	}

	@Override
	public List<PCart> getCustomerCarts(String emailId) throws Exception {
		List<PCart> cartList = cartDAO.getCustomerCarts(emailId);
		if (cartList.isEmpty())
			throw new Exception("Cart is empty!!!");
		return cartList;
	}

	@Override
	public String deleteProductFromCart(String emailId, Integer productId) throws Exception {
		String res = cartDAO.deleteProductFromCart(emailId, productId);
		if (res == null)
			throw new Exception("Cannt delete, something went wrong!!!");
		return res;
	}

	@Override
	public String modifyQuantityOfProductInCart(String emailId, Integer productId, Integer quantity) throws Exception {
		String res = cartDAO.modifyQuantityOfProductInCart(emailId, productId, quantity);
		if (res == null)
			throw new Exception("Cannt update, something went wrong!!!");
		return res;
	}

}
