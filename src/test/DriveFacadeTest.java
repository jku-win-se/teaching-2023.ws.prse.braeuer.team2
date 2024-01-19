package com.example.fahrtenbuch;

import com.example.fahrtenbuch.business.DatabaseConnection;
import com.example.fahrtenbuch.business.DriveFacade;
import com.example.fahrtenbuch.entities.Drive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.fahrtenbuch.business.DriveFacade;
import com.example.fahrtenbuch.entities.Drive;
public class DriveFacadeTest {

	private DriveFacade driveFacade;
	private FahrtenbucherController fahrController;
	private Connection conn;

	@Before
	public void setUp() {
		driveFacade = new DriveFacade();
		fahrController = new FahrtenbucherController();
		conn = createConnection();
	}

	@After
	public void tearDown() {
		closeConnection(conn);
	}


	private Connection createConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/logbook", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testParseTextFieldToIntValidInput() {
		String validInput = "123";
		Integer result = fahrController.parseTextFieldToInt(validInput);
		assertEquals(Integer.valueOf(123), result);
	}

	@Test
	public void testParseTextFieldToIntInvalidInput() {
		String invalidInput = "abc";
		Integer result = fahrController.parseTextFieldToInt(invalidInput);
		assertNull(result);
	}

	@Test
	public void testParseTextFieldToDoubleValidInput() {
		String validInput = "123.45";
		Double result = fahrController.parseTextFieldToDouble(validInput);
		assertEquals(Double.valueOf(123.45), result, 0.001);
	}

	@Test
	public void testParseTextFieldToDoubleInvalidInput() {
		String invalidInput = "abc";
		Double result = fahrController.parseTextFieldToDouble(invalidInput);
		assertNull(result);
	}



	@Test
	public void testGetLicensePlateByDriveId() {
		String licensePlate = driveFacade.getLicensePlateByDriveId(1);
		assertNotNull(licensePlate);
	}



	@Test
	public void testGetDriveById() throws SQLException {
		DriveFacade driveFacade = new DriveFacade();
		Drive drive = driveFacade.getDriveById(1);

		assertNotNull(drive);
		assertEquals(1, drive.getDriveId().intValue());
	}


	@Test
	public void testGetDrivesByLicensePlate() throws SQLException {
		DriveFacade driveFacade = new DriveFacade();
		List<Drive> drives = driveFacade.getDrivesByLicensePlate("testlicense");

		assertNotNull(drives);
		assertFalse(drives.isEmpty());
	}


	@Test
	public void testGetLastDriveId() {
		DriveFacade driveFacade = new DriveFacade();
		Integer lastDriveId = driveFacade.getLastDriveId();

		assertNotNull(lastDriveId);
	}

	@Test
	public void testGetCategoryNameByDriveId() {
		DriveFacade driveFacade = new DriveFacade();
		String categoryName = driveFacade.getCategoryNameByDriveId(1);

		assertNotNull(categoryName);
	}

	@Test
	public void testFilterDrivesWithQuery() {
		DriveFacade driveFacade = new DriveFacade();
		List<Drive> filteredDrives = driveFacade.filterDrivesWithQuery("SELECT * FROM drive WHERE status = 'COMPLETED'", null);

		assertNotNull(filteredDrives);
		assertFalse(filteredDrives.isEmpty());
	}


	@Test
	public void testFilterByStatus() {
		DriveFacade driveFacade = new DriveFacade();
		List<Drive> filteredDrives = driveFacade.filterByStatus("COMPLETED");

		assertNotNull(filteredDrives);
		assertFalse(filteredDrives.isEmpty());
	}


	@Test
	public void testPersistRecurringDrive() throws SQLException {
		DriveFacade driveFacade = new DriveFacade();
		int vehicleId = 1;
		Date startDate = Date.valueOf("2023-01-01");
		Date endDate = Date.valueOf("2023-12-31");
		int interval = 30;

		driveFacade.persistRecurringDrive(vehicleId, startDate, endDate, interval);
		List<Drive> recurringDrives = driveFacade.getDrivesByLicensePlate("secondlicense");
		assertFalse(recurringDrives.isEmpty());
	}


	@Test
	public void testGetAverageSpeedByDriveId() {
		DriveFacade driveFacade = new DriveFacade();
		int knownDriveId = 1;
		double expectedAverageSpeed = 49.0;


		try {
			double actualAverageSpeed = driveFacade.getAverageSpeedByDriveId(knownDriveId);
			assertEquals(expectedAverageSpeed, actualAverageSpeed, 1.0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}




}
