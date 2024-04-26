package com.yrl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class SalesData {

	/**
	 * Removes all records from all tables in the database.
	 */
	public static void clearDatabase() {
		try {
			Connection conn = ConnectionFactory.getConnection();

			for (String tableName : DatabaseInfo.TABLELIST) {
				String removeTableQuery = "delete from " + tableName;
				PreparedStatement psRemoveTable = conn.prepareStatement(removeTableQuery);
				psRemoveTable.executeUpdate();
				psRemoveTable.close();
			}
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method to add a person record to the database with the provided data.
	 *
	 * @param personUuid
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 */
	public static void addPerson(String personUuid, String firstName, String lastName, String street, String city,
			String state, String zip) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			int addressId = addAddress(street, city, state, zip);

			String addPerson = "insert into Person (uuid,firstName,lastName,addressId) values (?, ?, ?, ?);";
			PreparedStatement psAddPerson = conn.prepareStatement(addPerson);
			psAddPerson.setString(1, personUuid);
			psAddPerson.setString(2, firstName);
			psAddPerson.setString(3, lastName);
			psAddPerson.setInt(4, addressId);
			

			psAddPerson.close();

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personUuid</code>
	 *
	 * @param personUuid
	 * @param email
	 */
	public static void addEmail(String personUuid, String email) {
	    try {
	        Connection conn = ConnectionFactory.getConnection();

	        String addEmail = "insert into Email (address, personId) values (?, (SELECT personId from Person where uuid = ?))";
	        PreparedStatement psAddEmail = conn.prepareStatement(addEmail);
	        psAddEmail.setString(1, email);
	        psAddEmail.setString(2, personUuid);
	        psAddEmail.executeUpdate();

	        psAddEmail.close();

	        conn.close();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}

	/**
	 * Adds a store record to the database managed by the person identified by the
	 * given code.
	 *
	 * @param storeCode
	 * @param managerCode
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 */
	public static void addStore(String storeCode, String managerCode, String street, String city, String state,
			String zip) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			int addressId = addAddress(street, city, state, zip);
			String addStore = "insert into Store (storeCode,addressId, managerId) values (?, ?, (select personId from Person where uuid = ?)))";
			PreparedStatement psAddStore = conn.prepareStatement(addStore);
			psAddStore.setString(1, storeCode);
			psAddStore.setInt(2, addressId);
			psAddStore.setString(3, managerCode);
			psAddStore.executeUpdate();

			psAddStore.close();

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds an item record to the database of the given <code>type</code> with the
	 * given <code>code</code>, <code>name</code> and <code>basePrice</code>.
	 *
	 * Valid values for the <code>type</code> will be <code>"Product"</code>,
	 * <code>"Service"</code>, <code>"Data"</code>, or <code>"Voice"</code>.
	 *
	 * @param itemCode
	 * @param name
	 * @param type
	 * @param basePrice
	 */
	public static void addItem(String code, String name, String type, double basePrice) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			String addItem = "insert into Item (itemCode,type,name,baseCost) values (?, ?, ?, ?)";
			PreparedStatement psAddItem = conn.prepareStatement(addItem);
			psAddItem.setString(1, code);
			psAddItem.setString(2, String.valueOf(type.charAt(0)));
			psAddItem.setString(3, name);
			psAddItem.setDouble(4, basePrice);
			psAddItem.executeUpdate();

			psAddItem.close();

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds an Sale record to the database with the given data.
	 *
	 * @param saleCode
	 * @param storeCode
	 * @param customerPersonUuid
	 * @param salesPersonUuid
	 * @param saleDate
	 */
	public static void addSale(String saleCode, String storeCode, String customerPersonUuid, String salesPersonUuid,
			String saleDate) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			String addSale = "insert into Sale (saleCode,date,storeId,customerId,salespersonId) values "
					+ "(?, ?, (select storeId from Store where storeCode = ?),"
					+ " (select personId from Person where uuid = ?), "
					+ "(select personId from Person where uuid = ?)";
			PreparedStatement psAddSale = conn.prepareStatement(addSale);
			psAddSale.setString(1, saleCode);
			psAddSale.setString(2, saleDate);
			psAddSale.setString(3, storeCode);
			psAddSale.setString(4, customerPersonUuid);
			psAddSale.setString(5, salesPersonUuid);
			psAddSale.executeUpdate();

			psAddSale.close();

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a particular product (identified by <code>itemCode</code>) to a
	 * particular sale (identified by <code>saleCode</code>).
	 *
	 * @param saleCode
	 * @param itemCode
	 */
	public static void addProductToSale(String saleCode, String itemCode) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			String addProductToSale = "insert into SaleItem (itemId,saleId) values ((select itemId from Item where itemCode = ?), (select saleId from Sale where saleCode = ?)) ";
			PreparedStatement psAddProductToSale = conn.prepareStatement(addProductToSale);
			psAddProductToSale.setString(1, itemCode);
			psAddProductToSale.setString(2, saleCode);

			psAddProductToSale.executeUpdate();

			psAddProductToSale.close();

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Adds a particular leased (identified by <code>itemCode</code>) to a
	 * particular sale (identified by <code>saleCode</code>) with the start/end date
	 * specified.
	 *
	 * @param saleCode
	 * @param startDate
	 * @param endDate
	 */
	public static void addLeaseToSale(String saleCode, String itemCode, String startDate, String endDate) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			String addLeaseToSale = "insert into SaleItem (startDate,endDate,itemId,saleId) values (?, ?, (select itemId from Item where itemCode = ?), (select saleId from Sale where saleCode = ?)) ";
			PreparedStatement psAddLeaseToSale = conn.prepareStatement(addLeaseToSale);
			psAddLeaseToSale.setString(1, startDate);
			psAddLeaseToSale.setString(2, endDate);
			psAddLeaseToSale.setString(3, itemCode);
			psAddLeaseToSale.setString(4, saleCode);

			psAddLeaseToSale.executeUpdate();

			psAddLeaseToSale.close();

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a particular service (identified by <code>itemCode</code>) to a
	 * particular sale (identified by <code>saleCode</code>) with the specified
	 * number of hours. The service is done by the employee with the specified
	 * <code>servicePersonUuid</code>
	 *
	 * @param saleCode
	 * @param itemCode
	 * @param billedHours
	 * @param servicePersonUuid
	 */
	public static void addServiceToSale(String saleCode, String itemCode, double billedHours,
			String servicePersonUuid) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			String addServiceToSale = "insert into SaleItem (hoursBilled,servicemanId,itemId,saleId) values (?, (select personId from Person where uuid = ?), (select itemId from Item where itemCode = ?), (select saleId from Sale where saleCode = ?)) ";
			PreparedStatement psAddServiceToSale = conn.prepareStatement(addServiceToSale);
			psAddServiceToSale.setDouble(1, billedHours);
			psAddServiceToSale.setString(2, servicePersonUuid);
			psAddServiceToSale.setString(3, itemCode);
			psAddServiceToSale.setString(4, saleCode);

			psAddServiceToSale.executeUpdate();

			psAddServiceToSale.close();

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a particular data plan (identified by <code>itemCode</code>) to a
	 * particular sale (identified by <code>saleCode</code>) with the specified
	 * number of gigabytes.
	 *
	 * @param saleCode
	 * @param itemCode
	 * @param gbs
	 */
	public static void addDataPlanToSale(String saleCode, String itemCode, double gbs) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			String addDataPlanToSale = "insert into SaleItem (gbsPurchased,itemId,saleId) values (?, (select itemId from Item where itemCode = ?), (select saleId from Sale where saleCode = ?)) ";
			PreparedStatement psAddDataPlanToSale = conn.prepareStatement(addDataPlanToSale);
			psAddDataPlanToSale.setDouble(1, gbs);
			psAddDataPlanToSale.setString(2, itemCode);
			psAddDataPlanToSale.setString(3, saleCode);

			psAddDataPlanToSale.executeUpdate();

			psAddDataPlanToSale.close();

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Adds a particular voice plan (identified by <code>itemCode</code>) to a
	 * particular sale (identified by <code>saleCode</code>) with the specified
	 * <code>phoneNumber</code> for the given number of <code>days</code>.
	 *
	 * @param saleCode
	 * @param itemCode
	 * @param phoneNumber
	 * @param days
	 */
	public static void addVoicePlanToSale(String saleCode, String itemCode, String phoneNumber, int days) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			String addVoicePlanToSale = "insert into SaleItem (phoneNumber,daysPurchased,itemId,saleId) values (?, ?, (select itemId from Item where itemCode = ?), (select saleId from Sale where saleCode = ?)) ";
			PreparedStatement psAddVoicePlanToSale = conn.prepareStatement(addVoicePlanToSale);
			psAddVoicePlanToSale.setString(1, phoneNumber);
			psAddVoicePlanToSale.setInt(1, days);
			psAddVoicePlanToSale.setString(3, itemCode);
			psAddVoicePlanToSale.setString(4, saleCode);

			psAddVoicePlanToSale.executeUpdate();

			psAddVoicePlanToSale.close();

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Method to add an address record to the database with the provided data.
	 * 
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @return
	 */
	public static int addAddress(String street, String city, String state, String zip) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			String insertAddress = "insert into Address (street,city,state,zip) values (?, ?, ?, ?);";
			PreparedStatement psInsertAddress = conn.prepareStatement(insertAddress, Statement.RETURN_GENERATED_KEYS);
			psInsertAddress.setString(1, street);
			psInsertAddress.setString(2, city);
			psInsertAddress.setString(3, state);
			psInsertAddress.setInt(4, Integer.parseInt(zip));
			psInsertAddress.executeUpdate();

			ResultSet keys = psInsertAddress.getGeneratedKeys();
			keys.next();
			int addressId = keys.getInt(1);

			psInsertAddress.close();

			conn.close();
			return addressId;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
