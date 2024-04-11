package com.yrl;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Noah Bustard
 * @author Caden France
 * 
 * Date: 2024-03-08
 * 
 * Perosn class represents customers,
 * managers, servicemen, salespersons.
 * Included emails and address along with name.
 */

public class Person {
	private String uuid;
	private String firstName;
	private String lastName;
	private Address address;
	private List<String> emails;

	public Person(String uuid, String firstName, String lastName, Address address) {
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}

	public Person(String uuid, String firstName, String lastName, Address address, ArrayList<String> emails) {
		this(uuid, firstName, lastName, address);
		this.emails = emails;
	}

	public String getUuid() {
		return this.uuid;
	}

	public String getFirstName() {
		return this.firstName;
	}
	
	public String getFullName() {
		String name = this.lastName+", "+this.firstName;
		return name;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Address getAddress() {
		return this.address;
	}

	public List<String> getEmails() {
		return this.emails;
	}
	

	@Override
	public String toString() {
		if (this.emails == null) {
			return this.lastName + ", " + this.firstName + " (" + this.uuid + ")\n"
					+ "        []\n"
					+ "        " + this.address;
		} else {
			return this.lastName + ", " + this.firstName + " (" + this.uuid + ")\n"
					+ "        " + this.emails.toString() + "\n"
					+ "        " + this.address;
		}
		
	}

}
