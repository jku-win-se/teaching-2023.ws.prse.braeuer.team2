package at.jku.se.prse.team2.logbook.business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection conn;

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

    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
