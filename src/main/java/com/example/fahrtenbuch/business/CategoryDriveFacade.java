
package com.example.fahrtenbuch.business;

import com.example.fahrtenbuch.entities.CategoryDrive;
import com.example.fahrtenbuch.entities.Drive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDriveFacade {
    private Connection conn;

    public CategoryDriveFacade() {
        DatabaseConnection databaseConnection = new DatabaseConnection();



        conn = databaseConnection.getConnection();

    }

    public List<Drive> getDrivesByCategoryId(Integer id) throws SQLException {
        Drive drive = null;


        List<Drive> drives = new ArrayList<>();

        DriveFacade driveFacade = new DriveFacade();
        String query = "SELECT * FROM category_drive WHERE category_id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                drive = driveFacade.getDriveById(resultSet.getInt("drive_id"));
                drives.add(drive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drives;
    }

    public void persistCategoryDrive(CategoryDrive cd) {
        String query = "INSERT INTO category_drive (category_id, drive_id) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            Integer driveId = cd.getDriveId();
            if (driveId == null) {
                throw new IllegalArgumentException("drive_id darf nicht null sein.");
            }

            preparedStatement.setInt(1, cd.getCategoryId());
            preparedStatement.setInt(2, cd.getDriveId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void changeCategoryByDriveID(Integer driveId, Integer newCategoryId) {
        String updateQuery = "UPDATE category_drive SET category_id = ? WHERE drive_id = ?";

        try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)){
            if (driveId == null || newCategoryId == null) {
                throw new IllegalArgumentException("driveId and newCategoryId must not be null.");
            }

            updateStatement.setInt(1, newCategoryId);
            updateStatement.setInt(2, driveId);
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
