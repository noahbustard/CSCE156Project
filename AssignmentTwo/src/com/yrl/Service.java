package com.yrl;

public class Service extends Item {
	private Double hoursBilled;
	private Person serviceman;

	public Service(String itemCode, String saleCode, String name, Double hoursBilled, Person serviceman,
			Double basePrice) {
		super(itemCode, name, basePrice, saleCode);
		this.hoursBilled = hoursBilled;
		this.serviceman = serviceman;
	}

	public Double getHoursBilled() {
		return this.hoursBilled;
	}

	public Person getServiceman() {
		return this.serviceman;
	}

}