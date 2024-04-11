package com.yrl;
/**
 * @author Noah Bustard
 * @Author Caden France
 * 
 * Date: 2024-03-07
 * 
 * VoicePlan is an extension of the item class and 1/2 of the Plan objects.
 * Cost is calculated by mulitplying the costPer30Days by the 
 * number of days purchased and then dividing days purchased by 30.
 */
public class VoicePlan extends Item {
	private String phoneNumber;
	private Integer daysPurchased;
	private double pricePer30Days;

	public VoicePlan(String itemCode, String name, Double pricePer30Days, String phoneNumber,
			Integer daysPurchased) {
		super(itemCode, name);
		this.phoneNumber = phoneNumber;
		this.daysPurchased = daysPurchased;
		this.pricePer30Days = pricePer30Days;
	}

	public VoicePlan(String itemCode, String name, Double pricePer30Days) {
		super(itemCode, name);
		this.pricePer30Days = pricePer30Days;
	}

	public double getPricePer30Days() {
		return this.pricePer30Days;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public Integer getDaysPurchased() {
		return this.daysPurchased;
	}

	@Override
	public Double getCost() {
		return this.getPricePer30Days() * this.daysPurchased / 30;
	}
	
	@Override
	public Double getTax() {
		return this.getCost() * 0.065;
	}
	
	@Override
	public String getType() {
		return "Voice Plan";
	}

	@Override
	public String toString() {
		return this.getName() + " (" + this.getItemCode() + ") - Voice " + this.phoneNumber + "\n  	"
				+ this.daysPurchased + " days @ $" + this.getPricePer30Days() + " / 30 days";
	}

	@Override
	public Double getBaseCost() {
		return this.getPricePer30Days();
	}

}
