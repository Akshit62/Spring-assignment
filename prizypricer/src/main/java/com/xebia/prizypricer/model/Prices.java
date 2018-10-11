package com.xebia.prizypricer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Prices")
public class Prices {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(nullable = false)
	private Integer barcodeId;
	
	@Column(nullable = false)
	private Integer price;
	
	private String notes;
	
	
	public Prices() {
		
	}
	
	public Prices(int barcodeId, int price, String notes) {
		super();
		this.barcodeId = barcodeId;
		this.price = price;
		this.notes = notes;
	}
	
	public Prices(int id, int barcodeId, int price, String notes) {
		super();
		this.id = id;
		this.barcodeId = barcodeId;
		this.price = price;
		this.notes = notes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getBarcodeId() {
		return barcodeId;
	}

	public void setBarcodeId(Integer barcodeId) {
		this.barcodeId = barcodeId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	

	
}
