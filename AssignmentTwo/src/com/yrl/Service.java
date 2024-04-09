package com.yrl;
/**
 * @author Noah Bustard
 * @Author Caden France
 * 
 * Date: 2024-03-07
 * 
 * Service class represents any item where a servicemen completed
 * a service. Calculates cost based on baseCost * hoursBilled.
 * Is an extension of the Item class.
 */
public class Service extends Item {

	private Double hoursBilled;
	private Person serviceman;

	public Service(String itemCode, String name, Double baseCost, Double hoursBilled,
			Person serviceman) {
		super(itemCode, name, baseCost);
		this.hoursBilled = hoursBilled;
		this.serviceman = serviceman;
	}

	public Service(String itemCode, String name, Double baseCost) {
		super(itemCode, name, baseCost);;
	}
	

	public Double getHoursBilled() {
		return this.hoursBilled;
	}

	public Person getServiceman() {
		return this.serviceman;
	}

	@Override
	public Double getCost() {
		return this.getBaseCost() * this.hoursBilled;
		
	}
	public Double getTax() {
		return this.getBaseCost() * this.hoursBilled * 0.035;
	}

	@Override
	public String getType() {
		return "Service";
	}

	@Override
	public String toString() {
		return this.getName() +  " (" + this.getItemCode() + ") - Served by " + this.serviceman.getFullName() + 
				"\n  	" + this.hoursBilled + " hours @ $" + this.getBaseCost() + "/hour";
	}
}