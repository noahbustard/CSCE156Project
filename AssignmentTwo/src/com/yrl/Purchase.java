package com.yrl;

public class Purchase extends Product {

	private String saleCode;

	public Purchase(String itemCode, String saleCode, String name, Double basePrice) {
		super(itemCode, name, basePrice);
		this.saleCode = saleCode;
	}

	public String getSaleCode() {
		return saleCode;
	}

}
