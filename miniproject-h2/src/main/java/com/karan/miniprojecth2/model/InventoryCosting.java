package com.karan.miniprojecth2.model;

public class InventoryCosting {

	private Integer productId;
	private String productName;
	private String caregory;
	private Integer quantity;
	private Double singleUnitPrice;
	private Double totalPrice;
	
	public static InventoryCosting max(InventoryCosting x, InventoryCosting y) {
		return x.getTotalPrice() > y.getTotalPrice() ? x : y;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCaregory() {
		return caregory;
	}
	public void setCaregory(String caregory) {
		this.caregory = caregory;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getSingleUnitPrice() {
		return singleUnitPrice;
	}
	public void setSingleUnitPrice(Double singleUnitPrice) {
		this.singleUnitPrice = singleUnitPrice;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
