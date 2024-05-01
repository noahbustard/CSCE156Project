package com.yrl;

import java.time.LocalDate;
import java.util.Comparator;

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
	private int saleId;
	private Double total;

	public static final Comparator<Sale> COMPARE_BY_CUSTOMER = (o1, o2) -> {
		String p1First = o1.getCustomer().getFirstName();
		String p1Last = o1.getCustomer().getLastName();
		String p2First = o2.getCustomer().getFirstName();
		String p2Last = o2.getCustomer().getLastName();
		int lastNameComp = p1Last.compareToIgnoreCase(p2Last);
		int firstNameComp = p1First.compareToIgnoreCase(p2First);
		if (lastNameComp == 0) {
			if (firstNameComp == 0) {
				return Double.compare(o2.getTotal(), o1.getTotal());
			} else {
				return firstNameComp;
			}
		} else {
			return lastNameComp;
		}
	};
    public static final Comparator<Sale> COMPARE_BY_TOTAL = Comparator.comparing(Sale::getTotal).reversed()
            .thenComparing(COMPARE_BY_CUSTOMER);
    
    public static final Comparator<Sale> COMPARE_BY_STORE = Comparator
            .comparing((Sale sale) -> sale.getStore().getStoreCode(), String.CASE_INSENSITIVE_ORDER)
            .thenComparing(COMPARE_BY_TOTAL);


	public Sale(String saleCode, Store store, Person customer, Person salesperson, LocalDate date, int saleId) {
		this.saleCode = saleCode;
		this.store = store;
		this.customer = customer;
		this.salesperson = salesperson;
		this.date = date;
		this.saleId = saleId;
	}

	public Sale(String saleCode, Store store, Person customer, Person salesperson, LocalDate date) {
		this.store = store;
		this.saleCode = saleCode;
		this.customer = customer;
		this.salesperson = salesperson;
		this.date = date;
	}

	public Sale(String saleCode, Store store, Person customer, Person salesperson, LocalDate date, int saleId,
			double total) {
		this.saleCode = saleCode;
		this.store = store;
		this.customer = customer;
		this.salesperson = salesperson;
		this.date = date;
		this.saleId = saleId;
		this.total = total;
	}

	public String getSaleCode() {
		return this.saleCode;
	}

	public Person getCustomer() {
		return this.customer;
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
		return "\nSale\n" + "Sale Code: " + this.saleCode + "\nCustomer UUID: " + this.getCustomer().getUuid()
				+ "\nSalesperson UUID: " + this.getSalesperson().getUuid();
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

	public int getSaleId() {
		return saleId;
	}

	public Double getTotal() {
		return total;
	}
}
