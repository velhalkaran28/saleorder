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

import com.karan.miniprojecth2.model.Product;
import com.karan.miniprojecth2.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/ProductAPI")
public class ProductAPI {

	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts()  {
		try {
			List<Product> productList = productService.getAllProducts();
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
}
