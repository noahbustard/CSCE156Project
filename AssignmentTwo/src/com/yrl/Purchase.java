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
	
	public Purchase(String itemCode, String name, Double baseCost) {
		super(itemCode, name, baseCost);
	}

	@Override
	public String getType() {
		return "Purchase";
	}

	@Override
	public String toString() {
		return "\nPurchase\nItemCode=" + this.getItemCode() + "\nName=" + this.getName() + "\nbaseCost=" + this.getBaseCost();
	}
	@Override
	public Double getCost() {
		return super.getBaseCost();
	}
	@Override
	public Double getTax() {
		return super.getBaseCost() * 0.065;
	}

}
