package com.karan.miniprojecth2.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.karan.miniprojecth2.entity.CustomerEntity;
import com.karan.miniprojecth2.entity.PCartEntity;
import com.karan.miniprojecth2.entity.ProductEntity;
import com.karan.miniprojecth2.model.PCart;
import com.karan.miniprojecth2.model.Product;

@Repository(value = "cartDao")
public class CartDAOImpl implements CartDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String addProductToCart(String emailId, Integer productId, Integer quantity) {
		ProductEntity productEntity = entityManager.find(ProductEntity.class, productId);
		PCartEntity pCartEntity = new PCartEntity();
		pCartEntity.setProductEntity(productEntity);
		pCartEntity.setQuantity(quantity);
		productEntity.setQuantity(productEntity.getQuantity() - quantity);
		entityManager.persist(pCartEntity);
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, emailId);
		customerEntity.getCustomerCarts().add(pCartEntity);
		
		return "Product "+productEntity.getName()+" added successfully in cart with cartid: "+customerEntity.getCustomerCarts().get(customerEntity.getCustomerCarts().size()-1).getCartId();
	}

	@Override
	public List<PCart> getCustomerCarts(String emailId) {
		CustomerEntity customerEntity =entityManager.find(CustomerEntity.class, emailId);
		List<PCart> cartList = new ArrayList<PCart>();
		List<PCartEntity> cartEntityList = customerEntity.getCustomerCarts();
		for (PCartEntity pCartEntity : cartEntityList) {
			PCart cart = new PCart();
			cart.setCartId(pCartEntity.getCartId());
			Product product = new Product();
			product.setCategory(pCartEntity.getProductEntity().getCategory());
			product.setName(pCartEntity.getProductEntity().getName());
			product.setPrice(pCartEntity.getProductEntity().getPrice());
			product.setProductId(pCartEntity.getProductEntity().getProductId());
			product.setQuantity(pCartEntity.getProductEntity().getQuantity());
			
			cart.setProduct(product);
			cart.setQuantity(pCartEntity.getQuantity());
			cartList.add(cart);
		}
		return cartList;
	}

	@Override
	public String deleteProductFromCart(String emailId, Integer productId) {
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, emailId);
		PCartEntity pCartEntityToRemove = null;
		List<PCartEntity> pcartEntityList = customerEntity.getCustomerCarts();
		Integer quantity = 0;
		for (PCartEntity pCartEntity : pcartEntityList) {
			if(pCartEntity.getProductEntity().getProductId().equals(productId)) {
				pCartEntity.setProductEntity(null);
				pCartEntityToRemove = pCartEntity;
				quantity = pCartEntity.getQuantity();
			}
		}
		ProductEntity productEntity = entityManager.find(ProductEntity.class, productId);
		productEntity.setQuantity(productEntity.getQuantity()+quantity);
		pcartEntityList.remove(pCartEntityToRemove);
		PCartEntity pCartEntity = entityManager.find(PCartEntity.class, pCartEntityToRemove.getCartId());
		entityManager.remove(pCartEntity);
		return "Product with productid: "+productEntity.getProductId()+" removed from cart with cardid: "+pCartEntity.getCartId()+" successfully!!!";
	}

	@Override
	public String modifyQuantityOfProductInCart(String emailId, Integer productId, Integer quantity) {
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, emailId);
		List<PCartEntity> pCartEntityList = customerEntity.getCustomerCarts();
		for (PCartEntity pCartEntity : pCartEntityList) {
			if (pCartEntity.getProductEntity().getProductId().equals(productId)) {
				Integer pQuantity = pCartEntity.getQuantity();
				pCartEntity.setQuantity(quantity);
				entityManager.persist(pCartEntity);
				ProductEntity productEntity = entityManager.find(ProductEntity.class, productId);
				Integer nQuantity = ( productEntity.getQuantity() + pQuantity ) - quantity;
				productEntity.setQuantity(nQuantity);
			}
		}
		return "Product In cart modified successfully";
	}

}
