package com.karan.miniprojecth2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karan.miniprojecth2.dao.ProductDAO;
import com.karan.miniprojecth2.model.Product;

@Service(value = "productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public List<Product> getAllProducts() throws Exception {
		List<Product> productList = productDAO.getAllProducts();
		if (productList.isEmpty())
			throw new Exception("Product List is empty");
		return productList;
	}

}
