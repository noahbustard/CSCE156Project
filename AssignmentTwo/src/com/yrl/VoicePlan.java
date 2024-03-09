package com.yrl;
/**
 * @author Noah Bustard
 * @Author Caden France
 * 
 * Date: 2024-03-07
 * 
 * VoicePlan is an extension of the item class and 1/2 of the Plan objects.
 * No need to calculate cost because there is a flat price,
 * rather than charging per day.
 */
public class VoicePlan extends Item {
	private String phoneNumber;
	private Integer daysPurchased;
	private Double cost;
	private Sale sale;

	public VoicePlan(Sale sale, String itemCode, String name, Double baseCost, String phoneNumber,
			Integer daysPurchased) {
		super(itemCode, name, baseCost);
		this.phoneNumber = phoneNumber;
		this.daysPurchased = daysPurchased;
		this.cost = baseCost;
		this.sale = sale;
	}

	public VoicePlan(String itemCode, String name, Double baseCost) {
		super(name, itemCode, baseCost);
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public Sale getSale() {
		return this.sale;
	}

	public Integer getDaysPurchased() {
		return this.daysPurchased;
	}

	@Override
	public Double getCost() {
			return cost;
		
	}

	public Double getTotalCost() {
		double totalCost = (double) (daysPurchased/30) * cost;
		return totalCost;
	
	}
	@Override
	public String getType() {
		return "VoicePlan";
	}

	@Override
	public String toString() {
		return "VoicePlan\n" + this.getItemCode() + "PhoneNumber=" + phoneNumber + "\nDaysPurchased=" + daysPurchased
				+ "\nCost=" + cost;
	}

}
