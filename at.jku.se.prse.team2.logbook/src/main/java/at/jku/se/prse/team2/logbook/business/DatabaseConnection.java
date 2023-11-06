package at.jku.se.prse.team2.logbook.business;

import at.jku.se.prse.team2.logbook.entities.Vehicle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
        String dropTableVehicle = "DROP TABLE vehicle";

        // sql statements for create table
        String createVehicle = "CREATE TABLE vehicle (\n" +
                "    vehicle_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    license_plate VARCHAR(255) NOT NULL,\n" +
                "    odometer DOUBLE NOT NULL\n" +
                ");";

        try {
            statement = conn.createStatement();
            statement.executeUpdate(dropTableVehicle);
            statement.executeUpdate(createVehicle);

            VehicleFacade vehicleFacade = new VehicleFacade();
            vehicleFacade.persistVehicle(new Vehicle("testlicense", 123456.0));
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
