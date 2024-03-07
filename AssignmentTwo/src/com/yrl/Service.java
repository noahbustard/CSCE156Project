package com.yrl;

public class Service extends Item {
	private Double hoursBilled;
	private Person serviceman;
	private Double costPerHour;

	public Service(String saleCode, String itemCode, String name, Double costPerHour, 
			Double hoursBilled, Person serviceman) {
		super(itemCode, name, saleCode);
		this.hoursBilled = hoursBilled;
		this.serviceman = serviceman;
		this.costPerHour = costPerHour;
	}

	public Service(String itemCode, String name, Double costPerHour) {
		super(itemCode, name, null);
	}
	

	public Double getHoursBilled() {
		return this.hoursBilled;
	}

	public Person getServiceman() {
		return this.serviceman;
	}
	@Override
	public Double getCost() {
		return this.costPerHour;
	}

	@Override
	public String getType() {
		return "Service";
	}

}