package com.yrl;

public class SaleItem {
	private String saleCode;
	private String itemCode;

	public SaleItem(String saleCode, String itemCode) {
		this.saleCode = saleCode;
		this.itemCode = itemCode;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public String getItemCode() {
		return itemCode;
	}
	
	

}
