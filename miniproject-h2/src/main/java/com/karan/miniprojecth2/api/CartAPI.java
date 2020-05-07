package com.karan.miniprojecth2.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.karan.miniprojecth2.model.PCart;
import com.karan.miniprojecth2.model.Product;
import com.karan.miniprojecth2.service.CartService;

@CrossOrigin
@RestController
@RequestMapping("/CartAPI")
public class CartAPI {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping(value = "/addProductToCart")
	public ResponseEntity<String> addProductToCart(@RequestParam String emailId, @RequestParam Integer quantity, @RequestBody Product p) {
		try {
			String res = cartService.addProductToCart(emailId, p.getProductId(), quantity);
			return new ResponseEntity<String>(res,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping(value = "/getCustomerCarts")
	public ResponseEntity<List<PCart>> getCustomerCarts(@RequestParam String emailId) {
		try {
			List<PCart> cartList = cartService.getCustomerCarts(emailId);
			return new ResponseEntity<List<PCart>>(cartList,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@PostMapping(value = "/deleteProductFromCart")
	public ResponseEntity<String> deleteProductFromCart(@RequestParam String emailId, @RequestParam Integer productId) {
		try {
			String res = cartService.deleteProductFromCart(emailId, productId);
			return new ResponseEntity<String>(res, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@PostMapping(value = "modifyQuantityOfProductInCart")
	public ResponseEntity<String> modifyQuantityOfProductInCart(@RequestParam String emailId, @RequestParam Integer quantity, @RequestBody Product product) {
		try {
			String res = cartService.modifyQuantityOfProductInCart(emailId, product.getProductId(), quantity);
			return new ResponseEntity<String>(res,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
