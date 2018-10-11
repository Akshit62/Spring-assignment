package com.xebia.prizypricer.controllertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xebia.prizypricer.controller.PricesController;
import com.xebia.prizypricer.dao.PricesRepository;
import com.xebia.prizypricer.dao.ProductRepository;
import com.xebia.prizypricer.exception.BarcodeNotFoundException;
import com.xebia.prizypricer.model.Prices;
import com.xebia.prizypricer.model.Product;
import com.xebia.prizypricer.service.PricesService;
import com.xebia.prizypricer.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PricesControllerTest {

	@Configuration
	static class AccountServiceTestContextConfiguration {
		@Bean
		public PricesService priceService() {
			return new PricesService();
		}

		@Bean
		public PricesRepository priceRepository() {
			return Mockito.mock(PricesRepository.class);
		}
		@Bean
		public ProductService productService() {
			return new ProductService();
		}
		
		@Bean
		public ProductRepository productRepository() {
			return Mockito.mock(ProductRepository.class);
		}

	}
	
	@InjectMocks
	private PricesController pricesController;
	@Autowired
	private PricesService priceService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PricesRepository priceRepository;
	
	
	@Before
	public void makeDummyProductEntries() {
		List<Prices> list = new ArrayList<>();
		for(int i = 0;i < 10;i++) {
			Prices price = new Prices(12345, i+5, "Optimum price I feel");
			list.add(price);
		}
		Mockito.when(priceRepository.findByBarcodeId(12345)).thenReturn(list);
	}
	
	@Before
	public void makeDummyProducts() {
		List<Product> list = new ArrayList<>();
		for(int i = 1;i <= 10;i++) {
			Product product = new Product(i,"ptpref","weqf",i,"dsafew");
			list.add(product);
		}
		Mockito.when(productRepository.findAll()).thenReturn(list);
	}
	
	/*
	 * Adding price with existing product barcode
	 */
	@Test
	public void testAddingExisitingPrice() throws Exception {
		
		Product product = new Product("ptpref","weqf",1234,"dsafew");
		Mockito.when(productRepository.findByBarcode(product.getBarcode())).thenReturn (product);
		
		Prices price = new Prices(1234, 12, "Optimum price I feel");
		Prices addedPrice = priceService.addProductPrice(price);
		assertEquals(price, addedPrice);  		
	}
	
	/*
	 * Adding price with non existing product barcode
	 */
	@Test
	public void testAddingNonExisitingPrice() throws Exception {
		Prices price = new Prices(12345, 12, "Optimum price I feel");
		try {
		Prices addedPrice = priceService.addProductPrice(price);
		assertNotEquals(price, addedPrice);  
		}catch(Exception ex) {
			assertEquals((ex instanceof BarcodeNotFoundException), true);
		}
			
	}
	
	/*
	 * Checking if all products are obtained rightfully through get request
	 */
	@Test
	public void testViewingAllProducts() throws Exception {
		List<Product> productList = productService.getAllProducts();
		assertEquals(10, productList.size());
	}
	
	/*
	 * Checking for correct ideal Price for a product
	 */
	@Test
	public void calculate() throws Exception {
		HashMap<String,String> map = priceService.getPricesList(12345);
		String idealPrice = map.get("Ideal Value");
		assertEquals("62",idealPrice);
	}
}

