package com.yrl;

import java.time.LocalDate;
import java.time.Period;

/**
 * Lease class is an extension of purchase. Now has start and end dates to
 * signify the lease's duration.
 */
public class Lease extends Purchase {
	private LocalDate startDate;
	private LocalDate endDate;
	private int saleItemId;

	public Lease(String itemCode, String name, double price, int itemId, LocalDate startDate, LocalDate endDate,
			int saleItemId) {
		super(itemCode, name, price, itemId);
		this.startDate = startDate;
		this.endDate = endDate;
		this.saleItemId = saleItemId;
	}

	public Lease(String itemCode, String name, double price, LocalDate startDate, LocalDate endDate) {
		super(itemCode, name, price);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public String getType() {
		return "Lease";
	}

	@Override
	public String toString() {
		int months = Period.between(this.startDate, this.endDate).getMonths();
		months += Period.between(this.startDate, this.endDate).getYears() * 12;
		return this.getName() + " (" + this.getItemCode() + ")  - Lease for " + months + " Months";
	}

	@Override
	public Double getCost() {
		int months = Period.between(this.startDate, this.endDate).getMonths();
		months += Period.between(this.startDate, this.endDate).getYears() * 12;
		double monthlyCost = (this.getBaseCost() * 1.5) / months;
		return monthlyCost;
	}

	@Override
	public Double getTax() {
		return 0.0;
	}

	public int getSaleItemId() {
		return saleItemId;
	}

}
