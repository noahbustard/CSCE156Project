package com.yrl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Noah Bustard
 * @author Caden France
 * 
 *         Date: 2024-02-22
 */

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
		return "Person [uuid=" + uuid + ", firstName=" + firstName + ", lastName=" + lastName + ", address="
				+ this.getAddress() + ", emails=" + emails + "]";
	}

	/**
	 * Takes in data from file and proccesses it by putting it into a list of items
	 * 
	 */
	public static ArrayList<Person> loadData() {
		ArrayList<Person> personList = new ArrayList<Person>();
		String file = "data/Persons.csv";
		Scanner s = null;

		try {
			s = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		s.nextLine();
		while (s.hasNextLine()) {
			String line = s.nextLine();
			String tokens[] = line.split(",");

			if (tokens.length == 7) {
				Address a = new Address(tokens[3], tokens[4], tokens[5], tokens[6]);
				new Person(tokens[0], tokens[1], tokens[2], a);

			} else if (tokens.length > 7) {
				ArrayList<String> emails = new ArrayList<String>();
				for (int i = 7; i < tokens.length; i++)
					emails.add(tokens[i]);
				Address a = new Address(tokens[3], tokens[4], tokens[5], tokens[6]);
				Person person = new Person(tokens[0], tokens[1], tokens[2], a, emails);
				personList.add(person);
			}

		}
		s.close();
		return personList;
	}
}
