package com.karan.miniprojecth2.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.karan.miniprojecth2.entity.ProductEntity;
import com.karan.miniprojecth2.model.Product;

@Repository(value = "customerDao")
public class ProductDAOImpl implements ProductDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Product> getAllProducts() {
		Query query = entityManager.createQuery("select p from ProductEntity p");
		List<Product> productList = new ArrayList<Product>();
		List<ProductEntity> productEntityList = query.getResultList();
		for (ProductEntity productEntity : productEntityList) {
			Product p = new  Product();
			p.setCategory(productEntity.getCategory());
			p.setName(productEntity.getName());
			p.setPrice(productEntity.getPrice());
			p.setProductId(productEntity.getProductId());
			p.setQuantity(productEntity.getQuantity());
			
			productList.add(p);
		}
		return productList;
	}

}
