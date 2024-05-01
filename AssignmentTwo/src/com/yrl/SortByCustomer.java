package com.yrl;

import java.util.Comparator;

public class SortByCustomer implements Comparator<Sale>{

	@Override
	public int compare(Sale o1, Sale o2) {
		String p1First = o1.getCustomer().getFirstName();
		String p1Last = o1.getCustomer().getLastName();
		String p2First = o2.getCustomer().getFirstName();
		String p2Last = o1.getCustomer().getLastName();
		
		if (p1Last.compareToIgnoreCase(p2Last) == 0) {
			return (p1First.compareToIgnoreCase(p2First));
		} else {
			return p1Last.compareToIgnoreCase(p2Last);
		}
	}
}
