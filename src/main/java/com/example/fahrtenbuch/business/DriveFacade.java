package com.example.fahrtenbuch.business;
import com.example.fahrtenbuch.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class DriveFacade {
    private final Connection conn;
    public DriveFacade() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        conn = databaseConnection.getConnection();
    }
    public Drive getDriveById(Integer id) throws SQLException {
        Drive drive = null;
        String query = "SELECT * FROM drive WHERE drive_id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                drive = new Drive();
                drive.setDriveId(resultSet.getInt("drive_id"));
                drive.setVehicleId(resultSet.getInt("vehicle_id"));
                drive.setDate(resultSet.getDate("drive_date"));
                drive.setDepartureTime(resultSet.getTime("departure_time"));
                drive.setArrivalTime(resultSet.getTime("arrival_time"));
                drive.setWaitingTime(resultSet.getInt("waiting_time"));
                drive.setDrivenKilometres(resultSet.getDouble("driven_kilometres"));
                drive.setStatus(Status.valueOf(resultSet.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drive;
    }

    public List<Drive> getDrivesByLicensePlate(String licensePlate) {
        List<Drive> drives = new ArrayList<>();
        String query = "SELECT * FROM drive " +
                "JOIN vehicle ON drive.vehicle_id = vehicle.vehicle_id " +
                "WHERE vehicle.license_plate = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, licensePlate);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Drive drive = new Drive();
                drive.setDriveId(resultSet.getInt("drive_id"));
                drive.setVehicleId(resultSet.getInt("vehicle_id"));
                drive.setDate(resultSet.getDate("drive_date"));
                drive.setDepartureTime(resultSet.getTime("departure_time"));
                drive.setArrivalTime(resultSet.getTime("arrival_time"));
                drive.setWaitingTime(resultSet.getInt("waiting_time"));
                drive.setDrivenKilometres(resultSet.getDouble("driven_kilometres"));
                drive.setStatus(Status.valueOf(resultSet.getString("status")));
                drives.add(drive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drives;
    }

    public List<Drive> getAllDrives() {
        List<Drive> drives = new ArrayList<>();
        String query = "SELECT * FROM drive";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Drive drive = new Drive();
                drive.setDriveId(resultSet.getInt("drive_id"));
                drive.setVehicleId(resultSet.getInt("vehicle_id"));
                drive.setDate(resultSet.getDate("drive_date"));
                drive.setDepartureTime(resultSet.getTime("departure_time"));
                drive.setArrivalTime(resultSet.getTime("arrival_time"));
                drive.setWaitingTime(resultSet.getInt("waiting_time"));
                drive.setDrivenKilometres(resultSet.getDouble("driven_kilometres"));
                drive.setStatus(Status.valueOf(resultSet.getString("status")));
                drives.add(drive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drives;
    }
    public void persistDrive(Drive v) {
        //check if departure time is before arrival time
        if(v.getDepartureTime() != null && v.getArrivalTime() != null && v.getDepartureTime().after(v.getArrivalTime()))
            try {
                throw new InvalidDriveException("Abfahrtszeit muss vor Ankunftszeit sein");
            } catch (Exception e) {
                e.printStackTrace();
            }

        String query = "INSERT INTO drive (vehicle_id, drive_date, departure_time, arrival_time, waiting_time, driven_kilometres, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1,v.getVehicleId());
            preparedStatement.setDate(2,v.getDate());
            preparedStatement.setTime(3,v.getDepartureTime());
            preparedStatement.setTime(4,v.getArrivalTime());
            preparedStatement.setInt(5,v.getWaitingTime());
            preparedStatement.setDouble(6,v.getDrivenKilometres());
            preparedStatement.setString(7,v.getStatus().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void persistRecurringDrive(Integer vehicleId, Date startDate, Date endDate, int interval) {
        if (vehicleId == null || startDate == null || endDate == null || interval <= 0) {
            return;
        }

        try {
            persistDrive(new Drive(vehicleId, startDate));

            LocalDate currentDate = startDate.toLocalDate();
            LocalDate endLocalDate = endDate.toLocalDate();

            while (currentDate.isBefore(endLocalDate)) {
                currentDate = currentDate.plusDays(interval);
                if (currentDate.isBefore(endLocalDate) || currentDate.isEqual(endLocalDate)) {
                    persistDrive(new Drive(vehicleId, Date.valueOf(currentDate)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDriveById(Integer id) {
        String query = "DELETE FROM drive WHERE drive_id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Integer getLastDriveId() {
        Integer lastDriveId = null;
        String query = "SELECT drive_id FROM drive ORDER BY drive_id DESC LIMIT 1";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lastDriveId = resultSet.getInt("drive_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastDriveId;
    }




    public String getLicensePlateByDriveId(int driveId) {
        String query = "SELECT d.drive_id, v.license_plate " +
                "FROM drive d " +
                "JOIN vehicle v ON d.vehicle_id = v.vehicle_id " +
                "WHERE d.drive_id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1, driveId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("license_plate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getCategoryNameByDriveId(int driveId) {
        String categoryName = "...";

        String sql = "SELECT c.category_name " + "FROM category c "
                + "JOIN category_drive cd ON c.category_id = cd.category_id " + "WHERE cd.drive_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, driveId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    categoryName = resultSet.getString("category_name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryName;
    }

    @SuppressWarnings("exports")
    public List<Drive> filterDrivesWithQuery(String query, String category) {
        List<Drive> filteredDrives = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            if (category != null && !category.isEmpty()) {
                preparedStatement.setString(1, category);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Drive drive = new Drive();
                    drive.setDriveId(resultSet.getInt("drive_id"));
                    drive.setVehicleId(resultSet.getInt("vehicle_id"));
                    drive.setDate(resultSet.getDate("drive_date"));
                    drive.setDepartureTime(resultSet.getTime("departure_time"));
                    drive.setArrivalTime(resultSet.getTime("arrival_time"));
                    drive.setWaitingTime(resultSet.getInt("waiting_time"));
                    drive.setDrivenKilometres(resultSet.getDouble("driven_kilometres"));
                    drive.setStatus(Status.valueOf(resultSet.getString("status")));

                    filteredDrives.add(drive);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filteredDrives;
    }

    public List<String> getAllCategoryNames() {
        List<String> categoryNames = new ArrayList<>();
        String query = "SELECT category_name FROM category";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String categoryName = resultSet.getString("category_name");
                categoryNames.add(categoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryNames;
    }

    @SuppressWarnings({ "unused", "exports" })
    public List<Drive> filterByStatus(String status) {
        List<Drive> filteredDrives = new ArrayList<>();
        String query = "SELECT * FROM drive";

        if (!"all".equals(status)) {
            //System.out.println();
            query += " WHERE status = ?";
        }

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            if (!"all".equals(status)) {
                preparedStatement.setString(1, status);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Drive> driveList = new ArrayList<>();
                while (resultSet.next()) {
                    Drive drive = new Drive();
                    drive.setDriveId(resultSet.getInt("drive_id"));
                    drive.setVehicleId(resultSet.getInt("vehicle_id"));
                    drive.setDate(resultSet.getDate("drive_date"));
                    drive.setDepartureTime(resultSet.getTime("departure_time"));
                    drive.setArrivalTime(resultSet.getTime("arrival_time"));
                    drive.setWaitingTime(resultSet.getInt("waiting_time"));
                    drive.setDrivenKilometres(resultSet.getDouble("driven_kilometres"));
                    drive.setStatus(Status.valueOf(resultSet.getString("status")));

                    filteredDrives.add(drive);
                }

                return filteredDrives;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredDrives;
    }


    class InvalidDriveException extends Exception {
        private static final long serialVersionUID = 1L;

        public InvalidDriveException(String message) {
            super(message);
        }
    }


    public double getAverageSpeedByDriveId(int driveId) throws SQLException {
        double averageSpeed = 0.0;
        long timeDiffSeconds = 0;
        String query = "SELECT driven_kilometres, departure_time, arrival_time, waiting_time FROM drive WHERE drive_id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, driveId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double drivenKilometres = resultSet.getDouble("driven_kilometres");
                Time departureTime = resultSet.getTime("departure_time");
                Time arrivalTime = resultSet.getTime("arrival_time");
                int waitingTime = resultSet.getInt("waiting_time");

                if(departureTime != null && arrivalTime != null)
                    timeDiffSeconds = calculateTimeDifferenceInSeconds(departureTime, arrivalTime, waitingTime);

                if (timeDiffSeconds != 0) {
                    averageSpeed = Math.abs(drivenKilometres / (timeDiffSeconds / 3600.0));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averageSpeed;
    }

    public long calculateTimeDifferenceInSeconds(Time departureTime, Time arrivalTime, int waitingTime) {
        long departureMillis = departureTime.getTime();
        long arrivalMillis = arrivalTime.getTime();
        long timeDiffMillis = arrivalMillis - departureMillis;

        timeDiffMillis += waitingTime * 1000;
        long timeDiffSeconds = timeDiffMillis / 1000;

        return timeDiffSeconds;
    }

    public void updateDrive(int driveId, Drive updatedDrive) {
        String query = "UPDATE drive " +
                "SET vehicle_id = ?, " +
                "    drive_date = ?, " +
                "    departure_time = ?, " +
                "    arrival_time = ?, " +
                "    waiting_time = ?, " +
                "    driven_kilometres = ?, " +
                "    status = ? " +
                "WHERE drive_id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1, updatedDrive.getVehicleId());
            preparedStatement.setDate(2, updatedDrive.getDate());
            preparedStatement.setTime(3, updatedDrive.getDepartureTime());
            preparedStatement.setTime(4, updatedDrive.getArrivalTime());
            preparedStatement.setInt(5, updatedDrive.getWaitingTime());
            preparedStatement.setDouble(6, updatedDrive.getDrivenKilometres());
            preparedStatement.setString(7, updatedDrive.getStatus().toString());
            preparedStatement.setInt(8, driveId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOdometerIfCompleted(int driveId) {
        String query = "SELECT status, driven_kilometres, vehicle_id FROM drive WHERE drive_id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, driveId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String status = resultSet.getString("status");
                double drivenKilometres = resultSet.getDouble("driven_kilometres");
                int vehicleId = resultSet.getInt("vehicle_id");

                if ("ABGESCHLOSSEN".equals(status)) {
                    updateVehicleOdometer(vehicleId, drivenKilometres);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateVehicleOdometer(int vehicleId, double drivenKilometres) {
        String updateQuery = "UPDATE vehicle SET odometer = odometer + ? WHERE vehicle_id = ?";

        try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
            updateStatement.setDouble(1, drivenKilometres);
            updateStatement.setInt(2, vehicleId);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
