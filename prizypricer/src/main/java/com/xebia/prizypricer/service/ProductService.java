package com.xebia.prizypricer.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xebia.prizypricer.dao.ProductRepository;
import com.xebia.prizypricer.exception.BarcodeNotFoundException;
import com.xebia.prizypricer.exception.CustomDataIntegrityException;
import com.xebia.prizypricer.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PricesService priceService;
	
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}
	
	public HashMap<String,String> getProductByBarcode(int barcode) {
		Product product = productRepository.findByBarcode(barcode);
		if(product == null) {
			throw new BarcodeNotFoundException("Barcode does not exist");
		}
		HashMap<String, String> map = new HashMap<>();
		map = priceService.getPricesList(barcode);
		map.put("Product Name ", product.getName());
		map.put("Product Description",product.getDescription());
		map.put("Product Category",product.getCategory());
		
		return map;
	}
	
	public boolean getBarcode(int barcode) {
		if(productRepository.findByBarcode(barcode) == null) {
			return false;
		}
		return true;
	}
	
	public Product createProduct(Product product) {
		try {
			if(productRepository.findByBarcode(product.getBarcode()) != null) {
				throw new BarcodeNotFoundException("Barcode already exists");
			}
			productRepository.save(product);
			}
			catch(DataIntegrityViolationException exception) {
				throw new CustomDataIntegrityException("Fields cannot be null");
			}
		return product;
	}
}
