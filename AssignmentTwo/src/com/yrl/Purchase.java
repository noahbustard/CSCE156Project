package com.yrl;

public class Purchase extends Item {

	private double cost;

	public Purchase( String saleCode, String itemCode, String name, Double cost) {
		super(itemCode, name, saleCode);
		this.cost = cost;
	}

	public Purchase(String itemCode, String name, Double cost) {
		super(itemCode, name, null);
	}
	@Override
	public Double getCost() {
		return this.cost;
	}

	@Override
	public String getType() {
		return "Purchase/Lease";
	}

}
