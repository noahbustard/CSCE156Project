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
	
	private double Price;
	
	public Purchase(String itemCode, String name, Double price) {
		super(itemCode, name);
		this.Price = price;
	}

	public double getPrice() {
		return this.Price;
	}

	@Override
	public String getType() {
		return "Purchase";
	}

	@Override
	public String toString() {
		return this.getName() + " (" + this.getItemCode() + ")";
	}
	@Override
	public Double getCost() {
		return this.getPrice();
	}
	@Override
	public Double getTax() {
		return this.getPrice() * 0.065;
	}

	@Override
	public Double getBaseCost() {
		return this.getPrice();
	}

}
