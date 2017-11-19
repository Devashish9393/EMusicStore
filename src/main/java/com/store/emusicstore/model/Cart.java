package com.store.emusicstore.model;

import java.awt.ItemSelectable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.event.PublicInvocationEvent;

public class Cart {
	
	private String cartId;
	private Map<Long ,CartItem> cartItemMap;
	private double grandTotal;
	
	private Cart(){
		this.cartItemMap = new HashMap<Long,CartItem>();
		grandTotal = 0;
		
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public Map<Long, CartItem> getCartItemMap() {
		return cartItemMap;
	}
	public void setCartItemMap(Map<Long, CartItem> cartItemMap) {
		this.cartItemMap = cartItemMap;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	public Cart(String cartId){
		this();
		this.cartId = cartId;
	}
	
	public void addCartItem(CartItem item){
			Long productId= item.getProduct().getProductId();
			if(cartItemMap.containsKey(productId)){
				CartItem existingCartItem = cartItemMap.get(productId);
				existingCartItem.setQuantity(existingCartItem.getQuantity() +  item.getQuantity());
				cartItemMap.put(productId,item);
			}
			else{
				cartItemMap.put(productId, item);
				
			}
			updateGrandTotal();
	}
	public void removeCartItem(CartItem item){
		Long productId = item.getProduct().getProductId();
		cartItemMap.remove(productId);
		updateGrandTotal();
	}
	
	public void updateGrandTotal(){
		grandTotal = 0 ; 
		for(CartItem item : cartItemMap.values()){
			grandTotal = grandTotal +item.getTotalprice();
		}
	}

}
