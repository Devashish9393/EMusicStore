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
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private Path path;
	@Autowired
	private ProductDao productDao;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/")
	public String home() {
		logger.info("Welcome home!");

		return "home";
	}

	@RequestMapping("/productList")
	public String getProduct(Model model) {
		List<Product> productList = productDao.getAllProducts();
		model.addAttribute("productList", productList);
		return "ProductList";
	}

	@RequestMapping("/productList/viewProduct/{productId}")
	public String viewProduct(@PathVariable Long productId, Model model) throws IOException {

		Product product = productDao.getProductById(productId);
		model.addAttribute(product);
		return "viewProduct";
	}

}

