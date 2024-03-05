package com.yrl;

public class Service extends Item {
	private Double hoursBilled;
	private Person serviceman;
	private Double basePrice;
	private String saleCode;
	
	public Service(String itemCode, String saleCode, String name, Double hoursBilled, Person serviceman, Double basePrice) {
		super(itemCode, name);
		this.hoursBilled = hoursBilled;
		this.serviceman = serviceman;
		this.basePrice = basePrice;
		this.saleCode = saleCode;
	}

	public Double getHoursBilled() {
		return this.hoursBilled;
	}

	public Person getServiceman() {
		return this.serviceman;
	}

	public Double getBasePrice() {
		return this.basePrice;
	}

	public String getSaleCode() {
		return saleCode;
	}

}