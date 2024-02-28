package com.yrl;

public class DataPlan extends SaleItem {
	private String gbsPurchased;

	public DataPlan(String saleCode, String itemCode, String gbsPurchased) {
		super(saleCode, itemCode);
		this.gbsPurchased = gbsPurchased;
	}

	public String getGbsPurchased() {
		return gbsPurchased;
	}

}
