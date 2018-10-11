package com.xebia.prizypricer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.xebia.prizypricer.model.Prices;
import com.xebia.prizypricer.service.PricesService;

@RestController
public class PricesController {

	@Autowired
	private PricesService priceService;
	
	@PostMapping(path ="/prices",produces = "application/json")
	public Prices addPrice(@RequestBody Prices prices) {
		return priceService.addProductPrice(prices);
	}
	
	@GetMapping("/prices")
	public List<Prices> getAllPrices(){
		return priceService.getAllPrices();
	}
	
}
