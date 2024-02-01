package com.example.fahrtenbuch;

import com.example.fahrtenbuch.business.CategoryFacade;
import com.example.fahrtenbuch.business.DatabaseConnection;
import com.example.fahrtenbuch.business.DriveFacade;
import com.example.fahrtenbuch.entities.Drive;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TableViewControllerTest {
    private TableViewController tableViewController;

    private DatabaseConnection dataBaseConnection;
    private Connection conn;

    public TableViewControllerTest() {
        dataBaseConnection = new DatabaseConnection();
        conn = dataBaseConnection.getConnection();
    }

    @Before
    public void setUp() {
        tableViewController = new TableViewController();
    }

    @Test
    public void testFilterByYear() {
        int selectedYear = 2027;

        List<Drive> filteredDrives = tableViewController.filterByYear(selectedYear);
        for (Drive drives : filteredDrives) {
            assertEquals(selectedYear, drives.getDate().toLocalDate().getYear());
        }
    }


    @Test
    public void testFilterByYearNoMatch() {
        int selectedYear = 1900;

        List<Drive> filteredDrives = tableViewController.filterByYear(selectedYear);
        assertTrue(filteredDrives.isEmpty());
    }

    @Test
    public void testFilterByMonth() {
        int selectedMonth = 5;

        List<Drive> filteredDrives = tableViewController.filterByMonth(selectedMonth);
        for (Drive drive : filteredDrives) {
            assertEquals(selectedMonth, drive.getDate().toLocalDate().getMonthValue());
        }
    }

    @Test
    public void testFilterByMonthNoMatch() {
        int selectedMonth = 2;
        tableViewController.filterByYear(selectedMonth);

        List<Drive> filteredDrives = tableViewController.filterByMonth(selectedMonth);
        assertTrue(filteredDrives.isEmpty());
    }

    @Test
    public void testFilterByYearAndMonth() {
        int selectedYear = 2024;
        int selectedMonth = 1;

        List<Drive> filteredDrives = tableViewController.filterByYearAndMonth(selectedYear, selectedMonth);
        for (Drive drive : filteredDrives) {
            assertEquals(selectedYear, drive.getDate().toLocalDate().getYear());
            assertEquals(selectedMonth, drive.getDate().toLocalDate().getMonthValue());
        }
    }

    @Test
    public void testFilterByYearAndMonthNoMatch() {
        int selectedMonth = 80;
        int selectedYear = 2022;

        List<Drive> filteredDrives = tableViewController.filterByYearAndMonth(selectedYear, selectedMonth);
        assertTrue(filteredDrives.isEmpty());
    }

    @Test
    public void testFilterByCategory() {
        DriveFacade driveFacade = new DriveFacade();
        CategoryFacade categoryFacade =new CategoryFacade();
        String selectedCategory;
        try {
            selectedCategory = categoryFacade.getCategoryById(1).getName();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Drive> filteredDrives = tableViewController.filterByCategory(selectedCategory);
        for (Drive drive : filteredDrives) {
            assertEquals(selectedCategory, driveFacade.getCategoryNameByDriveId(drive.getDriveId()));
        }
    }

    @Test
    public void testFilterByCategoryNoMatch() {
        String selectedCategory = "Unsinn";

        List<Drive> filteredDrives = tableViewController.filterByCategory(selectedCategory);
        assertTrue(filteredDrives.isEmpty());
    }
}



