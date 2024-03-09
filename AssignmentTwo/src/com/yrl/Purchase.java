package com.yrl;
/**
 * @author Noah Bustard
 * @Author Caden France
 * 
 * Date: 2024-03-07
 * 
 * Purchase class is a product that is purchased and not leased.
 * Is a default class with no extra parameters needed.
 * Is an extension of the item class.
 */
public class Purchase extends Item {

	private Sale sale;

	public Purchase(Sale sale, String itemCode, String name, Double baseCost) {
		super(itemCode, name, baseCost);
		this.sale = sale;
	}

	public Purchase(String itemCode, String name, Double baseCost) {
		super(name, itemCode, baseCost);
	}

	public Sale getSale() {
		return this.sale;
	}

	@Override
	public String getType() {
		return "Purchase/Lease";
	}

	@Override
	public String toString() {
		return "Purchase Item Code: " + this.getItemCode();
	}

	@Override
	public Double getCost() {
		return this.getBaseCost() * 1.065;
	}

}
