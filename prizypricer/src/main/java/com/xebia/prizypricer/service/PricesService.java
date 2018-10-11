package com.xebia.prizypricer.service;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xebia.prizypricer.dao.PricesRepository;
import com.xebia.prizypricer.exception.BarcodeNotFoundException;
import com.xebia.prizypricer.exception.CustomDataIntegrityException;
import com.xebia.prizypricer.model.Prices;
import com.xebia.prizypricer.util.PriceUtil;

@Service
public class PricesService {

	@Autowired
	private PricesRepository pricesRepository;
	
	@Autowired
	private ProductService productService;
	
	public Prices addProductPrice(Prices price) {
		try {
		if(!productService.getBarcode(price.getBarcodeId())) {
			throw new BarcodeNotFoundException("Barcode does not exist");
		}
		pricesRepository.save(price);
		}
		catch(DataIntegrityViolationException exception) {
			throw new CustomDataIntegrityException("Fields cannot be null");
		}
		return price;
		
	}
	
	public HashMap<String,String> getPricesList(int barcode) {
		HashMap<String ,String> map = new HashMap<>();
		List<Prices> list =  pricesRepository.findByBarcodeId(barcode);
		
		List<Integer> priceList = PriceUtil.getPricesList(list);
		map.put("Minimum Value", PriceUtil.calculateMinPrice(priceList));
		map.put("Maximum Value", PriceUtil.calculateMaxPrice(priceList));
		map.put("Average Value", PriceUtil.calculateAvgPrice(priceList));
		map.put("Ideal Value", PriceUtil.calculateIdealPrice(priceList));
		map.put("Total values collected",String.valueOf(priceList.size()));
		return map; 
	}
	
	public List<Prices> getAllPrices(){
		return (List<Prices>) pricesRepository.findAll();
	}
}
 