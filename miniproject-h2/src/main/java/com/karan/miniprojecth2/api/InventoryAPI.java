package com.karan.miniprojecth2.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.karan.miniprojecth2.model.InventoryCosting;
import com.karan.miniprojecth2.model.Order;
import com.karan.miniprojecth2.model.Product;

@CrossOrigin
@RestController
@RequestMapping(value = "InventoryAPI")
public class InventoryAPI {
	
	@GetMapping("/getInventory")
	public ResponseEntity<String> getTotalInventoryCost() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Product[]> productList = restTemplate.getForEntity("http://localhost:8080/ProductAPI/getAllProducts",Product[].class);
		Product[] products = productList.getBody();
		List<InventoryCosting> icList = new ArrayList<InventoryCosting>();
		for (Product product : products) {
			InventoryCosting ic = new InventoryCosting();
			ic.setProductId(product.getProductId());
			ic.setCaregory(product.getCategory());
			ic.setProductName(product.getName());
			ic.setQuantity(product.getQuantity());
			ic.setSingleUnitPrice(product.getPrice());
			ic.setTotalPrice(product.getPrice() * product.getQuantity());
			icList.add(ic);
		}
		InventoryCosting ic = icList.stream().reduce(InventoryCosting::max).get();
		return new ResponseEntity<String>("Product "+ic.getProductName()+" has highest cost in the inventory!!! with price: "+ic.getTotalPrice(),HttpStatus.OK);
	}
	
	//Orders which are placed before the passed date.
	@PostMapping("getOrdersPlacedByDate")
	public ResponseEntity<String> getOrdersPlacedByDate(@RequestParam @DateTimeFormat(iso = ISO.DATE) String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = LocalDate.parse(date,formatter);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Order[]> response = restTemplate.getForEntity("http://localhost:8080/OrderAPI/getAllOrders", Order[].class);
		Order[] orders = response.getBody();
		String str = "";
		for (Order order : orders) {
			if (date1.isAfter(order.getDateOfOrder().toLocalDate())) {
				str+="Order "+order.getOrderId()+" was placed on "+order.getDateOfOrder()+" with a total bill of: "+order.getTotalPrice()+"\n";
			}
		}
		if (str.equals(""))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No orders placed before that date");
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}
}
