package com.xebia.prizypricer.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.xebia.prizypricer.model.Prices;

@Repository
public interface PricesRepository extends CrudRepository<Prices,Integer> {

	List<Prices> findByBarcodeId(Integer barcode);
	
}
