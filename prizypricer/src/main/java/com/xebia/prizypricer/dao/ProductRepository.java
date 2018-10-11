package com.xebia.prizypricer.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.xebia.prizypricer.model.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {
	
	Product findByBarcode(int barcode);
	Product findByName(String name);
	
}
