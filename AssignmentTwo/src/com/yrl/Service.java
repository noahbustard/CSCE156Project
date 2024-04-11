package com.yrl;
/**
 * @author Noah Bustard
 * @Author Caden France
 * 
 * Date: 2024-03-07
 * 
 * Service class represents any item where a servicemen completed
 * a service. Calculates cost based on pricePerHour * hoursBilled.
 * Is an extension of the Item class.
 */
public class Service extends Item {

	private double hoursBilled;
	private Person serviceman;
	private double pricePerHour;
	private int itemId;

	public Service(String itemCode, String name, Double pricePerHour, Double hoursBilled,
			Person serviceman) {
		super(itemCode, name);
		this.hoursBilled = hoursBilled;
		this.serviceman = serviceman;
		this.pricePerHour = pricePerHour;
	}

	public Service(String itemCode, String name, Double pricePerHour) {
		super(itemCode, name);
		this.pricePerHour = pricePerHour;
	}
	
	public Service(String itemCode, String name, Double pricePerHour, int itemId) {
		super(itemCode, name);
		this.pricePerHour = pricePerHour;
		this.itemId = itemId;
	}

	public double getPricePerHour() {
		return this.pricePerHour;
	}

	public Double getHoursBilled() {
		return this.hoursBilled;
	}

	public Person getServiceman() {
		return this.serviceman;
	}

	@Override
	public Double getCost() {
		return this.getPricePerHour() * this.hoursBilled;
		
	}
	public Double getTax() {
		return this.getCost() * 0.035;
	}

	@Override
	public String getType() {
		return "Service";
	}

	@Override
	public String toString() {
		return this.getName() +  " (" + this.getItemCode() + ") - Served by " + this.serviceman.getFullName() + 
				"\n  	" + this.hoursBilled + " hours @ $" + this.getPricePerHour() + "/hour";
	}

	@Override
	public Double getBaseCost() {
		return this.getPricePerHour();
	}

	public int getItemId() {
		return itemId;
	}
}