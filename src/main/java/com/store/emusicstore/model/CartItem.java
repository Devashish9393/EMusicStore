package com.store.emusicstore.model;

public class CartItem {
	
	private Product product;
	private Integer quantity;
	private Double totalprice;
	public CartItem(Product product) {
		super();
		this.product = product;
		this.quantity = 1;
		this.totalprice = product.getProductPrice();
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}
	
	

}
