package com.yrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class loads data from a database that is formatted into lists, arraylists,
 * and maps to be later processed into reports.
 * 
 * @author noahbustard
 * @author cadenfrance
 * 
 *         2024-04-11
 */
public class DataLoaderFromDatabase implements DataLoader {

	/**
	 * Loads items from a database. Loads the item based on the type which is
	 * identified from the database type variable.
	 */
	@Override
	public ArrayList<Item> loadItems() {
		ArrayList<Item> itemList = new ArrayList<>();
		Item i = null;

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "select  i.type, i.itemCode," + " i.name, i.baseCost," + " i.itemId from Item as i";
			PreparedStatement psItem = conn.prepareStatement(query);
			ResultSet rsItem = psItem.executeQuery();
			while (rsItem.next()) {
				String type = rsItem.getString(1);
				String itemCode = rsItem.getString(2);
				String itemName = rsItem.getString(3);
				Double price = rsItem.getDouble(4);
				int itemId = rsItem.getInt(5);
				if (type.equals("P")) {
					i = new Purchase(itemCode, itemName, price, itemId);
				} else if (type.equals("S")) {
					i = new Service(itemCode, itemName, price, itemId);
				} else if (type.equals("D")) {
					i = new DataPlan(itemCode, itemName, price, itemId);
				} else if (type.equals("V")) {
					i = new DataPlan(itemCode, itemName, price, itemId);
				}
				if (i != null) {
					itemList.add(i);
				}
			}
			rsItem.close();
			psItem.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return itemList;
	}

	/**
	 * Loads saleItems into a map where the sale code is the key and a list of all
	 * items in that sale is the value of that key.
	 */
	@Override
	public Map<String, ArrayList<String>> loadSaleItemsMap() {
		Map<String, ArrayList<String>> saleItemsMap = new HashMap<String, ArrayList<String>>();

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "select saleCode, itemCode from SaleItem " + "join Sale on SaleItem.saleId = Sale.saleId"
					+ " join Item on SaleItem.itemId = Item.itemId";
			PreparedStatement psSaleItem = conn.prepareStatement(query);
			ResultSet rsSaleItem = psSaleItem.executeQuery();
			while (rsSaleItem.next()) {
				String saleCode = rsSaleItem.getString(1);
				String itemCode = rsSaleItem.getString(2);
				if (!saleItemsMap.containsKey(saleCode)) {
					saleItemsMap.put(saleCode, new ArrayList<String>());
				}
				saleItemsMap.get(saleCode).add(itemCode);

			}
			rsSaleItem.close();
			psSaleItem.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println(saleItemsMap);
		return saleItemsMap;
	}

	@Override
	public Map<Sale, ArrayList<Item>> loadSaleItems(ArrayList<Person> personList, Map<String, Item> itemInfoMap,
			Map<String, Sale> saleMap, Map<String, ArrayList<String>> saleCodeItemCodeMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Store> loadStores(ArrayList<Person> personList) {
		ArrayList<Store> storeList = new ArrayList<Store>();

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "select storeCode, managerId, a.street," + " a.zip, a.city, a.state, storeId"
					+ " from Store join Address as a" + " on Store.addressId = a.addressId";
			PreparedStatement psStore = conn.prepareStatement(query);
			ResultSet rsStore = psStore.executeQuery();

			while (rsStore.next()) {
				String storeCode = rsStore.getString(1);
				int managerId = rsStore.getInt(2);
				Person manager = null;
				for (Person p : personList) {
					if (managerId == p.getPersonId()) {
						manager = p;
					}
				}
				String street = rsStore.getString(3);
				int zipcode = rsStore.getInt(4);
				String city = rsStore.getString(5);
				String state = rsStore.getString(6);
				int storeId = rsStore.getInt(7);
				Address a = new Address(street, city, state, zipcode);
				Store s = new Store(storeCode, manager, a, storeId);
				storeList.add(s);
			}

			rsStore.close();
			psStore.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return storeList;
	}

	/**
	 * Loads persons from database. Loads emails as well and puts them into a list
	 * in the person object.
	 */
	@Override
	public ArrayList<Person> loadPersons() {
		ArrayList<Person> personList = new ArrayList<Person>();

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "select firstName, lastName, a.street, a.zip, "
					+ "a.city, a.state, uuid, personId from Person "
					+ "join Address as a on Person.addressId = a.addressId";
			PreparedStatement psPerson = conn.prepareStatement(query);
			ResultSet rsPerson = psPerson.executeQuery();

			while (rsPerson.next()) {
				List<String> emails = new ArrayList<>();
				String firstName = rsPerson.getString(1);
				String lastName = rsPerson.getString(2);
				String street = rsPerson.getString(3);
				int zipcode = rsPerson.getInt(4);
				String city = rsPerson.getString(5);
				String state = rsPerson.getString(6);
				String uuid = rsPerson.getString(7);
				int personId = rsPerson.getInt(8);

				String emailQuery = "select address from Email where personId = ?";
				PreparedStatement psEmail = conn.prepareStatement(emailQuery);
				psEmail.setInt(1, personId);
				ResultSet rsEmail = psEmail.executeQuery();

				while (rsEmail.next()) {
					emails.add(rsEmail.getString(1));
				}
				rsEmail.close();
				psEmail.close();

				Address a = new Address(street, city, state, zipcode);
				Person p = new Person(uuid, firstName, lastName, a, emails, personId);
				personList.add(p);

			}
			rsPerson.close();
			psPerson.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return personList;
	}

	@Override
	public Map<String, Sale> loadSales(ArrayList<Person> personList, ArrayList<Store> storeList,
			Map<String, Item> itemInfoMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
