package com.store.emusicstore.dao;

import java.util.List;

import com.store.emusicstore.model.Product;

public interface ProductDao {

	void addProduct(Product product);
	
	Product getProduct(Long id);
	
	Product getProductById(Long id);
	
	List<Product> getAllProducts();
	
	void deleteProduct(Long id);
	
	void editProduct(Product product);
}
