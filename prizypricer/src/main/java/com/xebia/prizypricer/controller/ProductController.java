package com.xebia.prizypricer.controller;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xebia.prizypricer.model.Product;
import com.xebia.prizypricer.service.ProductService;


@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/products")
	public List<Product> getProduct() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/products/{barcode}")
	public HashMap<String,String> getProductByBarcode(@PathVariable("barcode") int barcode) {
		HashMap<String, String> map = new HashMap<>();
		map =  productService.getProductByBarcode(barcode);
		return map;
	}
	
	@PostMapping(path ="/products",produces = "application/json")
	public Product addProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}
}
