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
	private Double costPerHour;
	private Sale sale;

	public Service(Sale sale, String itemCode, String name, Double baseCost, Double hoursBilled,
			Person serviceman) {
		super(itemCode, name, baseCost);
		this.hoursBilled = hoursBilled;
		this.serviceman = serviceman;
		this.costPerHour = baseCost;
		this.sale = sale;
	}

	public Service(String itemCode, String name, Double baseCost) {
		super(name, itemCode, baseCost);
	}

	public Double getHoursBilled() {
		return this.hoursBilled;
	}

	public Person getServiceman() {
		return this.serviceman;
	}

	public Double getCostPerHour() {
		return costPerHour;
	}

	public Sale getSale() {
		return sale;
	}

	@Override
	public Double getCost() {
		return this.costPerHour*this.hoursBilled;
	}

	@Override
	public String getType() {
		return "Service";
	}

	@Override
	public String toString() {
		if (this.serviceman != null) {
			return "Service\nItemCode= " + this.getItemCode()  + "\nHoursBilled=" + hoursBilled + "\nServiceman=" + serviceman.toString() + ",\nCostPerHour=" + costPerHour;
		}
		else {
			return "Service\nItemCode=" + this.getItemCode()  + "\nHoursBilled=" + hoursBilled + "\nServiceman=null" + ",\nCostPerHour=" + costPerHour;
		}
	}
}