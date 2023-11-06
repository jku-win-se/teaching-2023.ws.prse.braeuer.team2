package at.jku.se.prse.team2.logbook.business;

import at.jku.se.prse.team2.logbook.entities.Drive;
import at.jku.se.prse.team2.logbook.entities.Vehicle;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class DatabaseConnection {
    public Connection conn;
    Statement statement = null;

    public Connection getConnection() {
        String jdbcURL = "jdbc:mysql://localhost/logbook";
        String user = "root";
        String pass = "12345678";

        try {
            conn = DriverManager.getConnection(jdbcURL, user, pass);
            System.out.println("connection successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public void initiateDB() throws SQLException {
        //create DB connection
        conn = getConnection();

        //sql statements for drop table
        String dropTableDrive = "DROP TABLE IF EXISTS drive";
        String dropTableVehicle = "DROP TABLE IF EXISTS vehicle";

        // sql statements for create table
        String createVehicle = "CREATE TABLE vehicle (\n" +
                "    vehicle_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    license_plate VARCHAR(255) NOT NULL,\n" +
                "    odometer DOUBLE NOT NULL\n" +
                ");";

        String createDrive = "CREATE TABLE drive (\n" +
                "    drive_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    vehicle_id INT NOT NULL,\n" +
                "    drive_date DATE NOT NULL,\n" +
                "    departure_time TIME,\n" +
                "    arrival_time TIME,\n" +
                "    waiting_time INT,\n" + //in minutes
                "    driven_kilometres DOUBLE,\n" +
                "    status VARCHAR(255),\n" +
                "    FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)\n" +
                ");";

        try {
            statement = conn.createStatement();

            statement.executeUpdate(dropTableDrive);
            statement.executeUpdate(dropTableVehicle);

            statement.executeUpdate(createVehicle);
            statement.executeUpdate(createDrive);

            VehicleFacade vehicleFacade = new VehicleFacade();
            vehicleFacade.persistVehicle(new Vehicle("testlicense", 123456.0));
            vehicleFacade.persistVehicle(new Vehicle("secondlicense", 100.0));
            vehicleFacade.deleteVehicleById(2);

            DriveFacade driveFacade = new DriveFacade();
            driveFacade.persistDrive(new Drive(1, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), Time.valueOf(LocalTime.now()), 10,100.0));
            driveFacade.persistDrive(new Drive(1, Date.valueOf(LocalDate.now())));
            driveFacade.persistDrive(new Drive(1, Date.valueOf(LocalDate.now()),Time.valueOf(LocalTime.now())));

            System.out.println(vehicleFacade.getAllVehicles());
            System.out.println(driveFacade.getAllDrives());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (conn!= null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
