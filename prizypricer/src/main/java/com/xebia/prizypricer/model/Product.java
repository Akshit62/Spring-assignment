package com.xebia.prizypricer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String name;
	private	String description; 
	private Integer barcode;
	private String category;
	
	public Product() {
		
	}
	
	public Product( String name, String description, int barcode, String category) {
		super();
		this.name = name;
		this.description = description;
		this.barcode = barcode;
		this.category = category;
	}
	
	public Product(int id, String name, String description, int barcode, String category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.barcode = barcode;
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getBarcode() {
		return barcode;
	}
	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
