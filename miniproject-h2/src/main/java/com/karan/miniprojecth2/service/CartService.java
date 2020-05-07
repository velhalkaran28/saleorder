package com.karan.miniprojecth2.service;

import java.util.List;

import com.karan.miniprojecth2.model.PCart;

public interface CartService {

	public String addProductToCart(String emailId, Integer productId, Integer quantity) throws Exception;
	public List<PCart> getCustomerCarts(String emailId) throws Exception;
	public String deleteProductFromCart(String emailId, Integer productId) throws Exception;
	public String modifyQuantityOfProductInCart(String emailId, Integer productId, Integer quantity) throws Exception;
}
