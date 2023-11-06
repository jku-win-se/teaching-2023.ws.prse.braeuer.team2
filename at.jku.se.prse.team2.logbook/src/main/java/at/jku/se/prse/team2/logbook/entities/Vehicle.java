package at.jku.se.prse.team2.logbook.entities;

import java.sql.Date;

public class Vehicle {
    private Long vehicleId;
    private String licensePlate;
    private Double odometer;

    public Vehicle(String licensePlate, Double odometer) {
        this.licensePlate = licensePlate;
        this.odometer = odometer;
    }

    public Vehicle() {
        
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Double getOdometer() {
        return odometer;
    }

    public void setOdometer(Double odometer) {
        this.odometer = odometer;
    }

    public void setVehicleId(long vehicle_id) {
    }
}
