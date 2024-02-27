package com.yrl;

public class Sales {
	private String saleCode;
	private String storeCode;
	private String customerCode;
	private String salespersonCode;
	private String date;

	public Sales(String storeCode, String saleCode, String customerCode, String salespersonCode, String date) {
		this.storeCode = storeCode;
		this.saleCode = saleCode;
		this.customerCode = customerCode;
		this.salespersonCode = salespersonCode;
		this.date = date;
	}
	
	public String getSaleCode() {
		return this.saleCode;
	}

	public String getStoreCode() {
		return this.storeCode;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public String getSalespersonCode() {
		return this.salespersonCode;
	}

	public String getDate() {
		return this.date;
	}

}
