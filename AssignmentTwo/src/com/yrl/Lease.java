package com.yrl;

import java.time.LocalDate;
/**
 * Lease class is an extension of purchase. Now has start and end dates
 * to signify the lease's duration.
 */
public class Lease extends Purchase {
	private LocalDate startDate;
	private LocalDate endDate;

	public Lease(String saleCode, String itemCode, String name,
			Double cost, LocalDate startDate, LocalDate endDate) {
		super(itemCode, saleCode, name, cost);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	@Override
	public String toString() {
		return "Lease\nItemCode= " + this.getItemCode()  + "\nstartDate=" + startDate + "\nendDate=" + endDate;
	}

}
