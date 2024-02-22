package com.yrl;

import java.util.ArrayList;

public class Person {
	private String uuid;
	private String firstName;
	private String lastName;
	private Address address;
	private ArrayList<String> emails;
	
	
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
		return uuid;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public ArrayList<String> getEmails() {
		return emails;
	}

	@Override
	public String toString() {
		return "Person [uuid=" + uuid + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + this.getAddress()
				+ ", emails=" + emails + "]";
	}

	
}
