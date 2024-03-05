package com.yrl;

import java.time.LocalDate;

public class Sale {
	private String saleCode;
	private Store store;
	private Person customer;
	private Person salesperson;
	private LocalDate date;

	public Sale(String saleCode, Store store, Person customer, Person salesperson, LocalDate date) {
		this.store = store;
		this.saleCode = saleCode;
		this.customer = customer;
		this.salesperson = salesperson;
		this.date = date;
	}

	public String getSaleCode() {
		return this.saleCode;
	}

	public String getStoreCode() {
		return this.store.getCode();
	}

	public String getCustomerCode() {
		return this.customer.getUuid();
	}

	public String getSalespersonCode() {
		return this.salesperson.getUuid();
	}

	public LocalDate getDate() {
		return this.date;
	}

}
