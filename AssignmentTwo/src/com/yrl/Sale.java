package com.yrl;

import java.time.LocalDate;

/**
 * @author Noah Bustard
 * @Author Caden France
 * 
 *         Date: 2024-03-07
 * 
 *         Sale class represents each individual sale that occurred from the
 *         data. Sales include their store, customer, and salesperson all as
 *         objects to allow for easy access to any information.
 */
public class Sale {
	private String saleCode;
	private Store store;
	private Person customer;
	private Person salesperson;
	private LocalDate date;
	private Item item;


	public Sale(String saleCode, Store store, Person customer, Person salesperson, LocalDate date, Item item) {
		this.store = store;
		this.saleCode = saleCode;
		this.customer = customer;
		this.salesperson = salesperson;
		this.date = date;
		this.item = item;

	}

	public Item getItem() {
		return item;
	}

	public String getSaleCode() {
		return this.saleCode;
	}

	public Person getCustomer() {
		return this.customer;
	}

	public String getCustomerCode() {
		return this.customer.getUuid();
	}

	public Person getSalesperson() {
		return salesperson;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public Store getStore() {
		return store;
	}
	

	@Override
	public String toString() {
		return this.getSaleCode();
	}

	public void printReport() {
		System.out.println("Sale      " + this.getSaleCode());
		System.out.println("Store     " + this.getStore().getStoreCode());
		System.out.println("Date      " + this.getDate());
		System.out.println("Customer");
		System.out.println(this.getCustomer().toString());
		System.out.println(this.getSalesperson().toString());
		System.out.println("\n\n");
		
	}

}
