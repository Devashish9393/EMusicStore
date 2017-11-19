package com.store.emusicstore.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.store.emusicstore.dao.CartDao;
import com.store.emusicstore.model.Cart;

import antlr.collections.List;

@Repository
public class CartDaoImpl implements CartDao {

private Map<String, Cart> cartList;
	

public CartDaoImpl() {
cartList= new HashMap<String,Cart>();
}
public Cart create(Cart cart) {
	if(cartList.keySet().contains(cart.getCartId())){
		throw new IllegalArgumentException(String.format("Cannot create a cart. A cart with the given id(%)" + "already"
	+ "exists", cart.getCartId()));
	}
	cartList.put(cart.getCartId(), cart);
	return cart;
}
@Override
public Cart read(String cartId) {
	// TODO Auto-generated method stub
	return cartList.get(cartId);
}
@Override
public void update(String cartId, Cart cart) {
	// TODO Auto-generated method stub
	if(!cartList.keySet().contains(cartId)){
		throw new IllegalArgumentException(String.format("Cannot update  cart. A cart with the given id(%)" + "does not"
				+ "exist", cart.getCartId()));
	}
	cartList.put(cartId, cart);
	
}
@Override
public void delete(String cartId) {
	// TODO Auto-generated method stub
	if(!cartList.keySet().contains(cartId)){
		throw new IllegalArgumentException(String.format("Cannotdelete cart. A cart with the given id(%)" + "does not"
				+ "exist", cartId));
	}
	cartList.remove(cartId);
}

}

