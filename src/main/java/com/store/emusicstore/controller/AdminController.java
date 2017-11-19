package com.store.emusicstore.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.store.emusicstore.dao.ProductDao;
import com.store.emusicstore.model.Product;

/**
 * Handles requests for the admin home page.
 */
@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private Path path;
	@Autowired
	private ProductDao productDao;

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping("/admin")
	public String getAdminPage() {
		return "admin";
	}

	@RequestMapping("/admin/productInventory")
	public String getProductInventory(Model model) {

		List<Product> productList = productDao.getAllProducts();
		model.addAttribute("productList", productList);
		return "ProductInventory";

	}

	@RequestMapping("/admin/productInventory/addProduct")
	public String addProduct(Model model) {
		Product product = new Product();
		product.setProductCategory("instrument");
		product.setProductCondition("new");
		product.setProductStatus("active");

		model.addAttribute("product", product);
		return "AddProduct";

	}

	@RequestMapping(value = "/admin/productInventory/addProduct", method = RequestMethod.POST)
	public String addProductPost(@Valid @ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request) {
		
		if(result.hasErrors()){
			return "AddProduct";
		}
		
		productDao.addProduct(product);

		MultipartFile productImage = product.getProductImage();

		/*
		 * String rootDirectory =
		 * request.getSession().getServletContext().getRealPath("/"); path =
		 * Paths.get(rootDirectory +
		 * "/resources/images/"+product.getProductId()+".png");
		 */
		String rootDirectory = "/home/reflex/Documents/workspace-sts-3.8.3.RELEASE/EMusicStore/src/main/webapp/resources";
		path = Paths.get(rootDirectory + "/images/" + product.getProductId() + ".png");

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(path.toString()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Product image saving failed", e);
			}
		}
		return "redirect:/admin/productInventory";

	}

	@RequestMapping("/admin/productInventory/deleteProduct/{productId}")
	public String deleteProduct(@PathVariable Long productId, Model model) {

		String rootDirectory = "/home/reflex/Documents/workspace-sts-3.8.3.RELEASE/EMusicStore/src/main/webapp/resources";
		path = Paths.get(rootDirectory + "/images/" + productId + ".png");

		if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		productDao.deleteProduct(productId);
		return "redirect:/admin/productInventory";
	}

	@RequestMapping("/admin/productInventory/editProduct/{productId}")
	public String editProduct(@PathVariable Long productId, Model model){
		
		
		Product product = productDao.getProductById(productId);
		
		model.addAttribute(product);
		
		return "EditProduct";
	}

	@RequestMapping(value = "/admin/productInventory/editProduct" , method = RequestMethod.POST)
	public String editProduct(@Valid @ModelAttribute Product product ,Model model ,BindingResult result, HttpServletRequest request){
		
		if(result.hasErrors()){
			return "editProduct";
		}
		
		MultipartFile productImage = product.getProductImage();
		
		/*String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		path = Paths.get(rootDirectory + "/resources/images/"+product.getProductId()+".png");
		*/
		String rootDirectory = "/home/reflex/Documents/workspace-sts-3.8.3.RELEASE/EMusicStore/src/main/webapp/resources";
		path = Paths.get(rootDirectory + "/images/"+product.getProductId()+".png");
 
		if(productImage != null && !productImage.isEmpty()){
			try{
				productImage.transferTo(new File(path.toString()));
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException("Product image saving failed" ,e);
			} 
		}
		productDao.editProduct(product);
		return "redirect:/admin/productInventory";
	}
}

