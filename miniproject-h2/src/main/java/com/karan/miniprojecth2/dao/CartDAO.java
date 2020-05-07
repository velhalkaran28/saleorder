package com.karan.miniprojecth2.dao;

import java.util.List;

import com.karan.miniprojecth2.model.PCart;

public interface CartDAO {

	public String addProductToCart(String emailId, Integer productId, Integer quantity);
	public List<PCart> getCustomerCarts(String emailId);
	public String deleteProductFromCart(String emailId, Integer productId);
	public String modifyQuantityOfProductInCart(String emailId, Integer productId, Integer quantity);
}
