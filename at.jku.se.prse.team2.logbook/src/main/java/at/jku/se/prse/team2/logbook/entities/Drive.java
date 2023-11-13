package at.jku.se.prse.team2.logbook.entities;

import java.sql.Date;
import java.sql.Time;

public class Drive {

    private Integer driveId;
    private Integer vehicleId;
    private Date date;
    private Time departureTime;
    private Time arrivalTime;
    private Integer waitingTime;
    private Double drivenKilometres;
    private Status status;

    // lange vor der Fahrt (zukünftige Fahrt)
    public Drive(Integer vehicleId, Date date) {
        this.vehicleId = vehicleId;
        this.date = date;
        this.waitingTime = 0;
        this.drivenKilometres = 0.0;
        this.status = Status.ZUKUENFTIG;
    }

    // kurz vor der Fahrt (bei Abfahrt)
    public Drive(Integer vehicleId, Date date, Time departureTime) {
        this.vehicleId = vehicleId;
        this.date = date;
        this.departureTime = departureTime;
        this.waitingTime = 0;
        this.drivenKilometres = 0.0;
        this.status = Status.AUF_FAHRT;
    }

    // nach der Fahrt
    public Drive(Integer vehicleId, Date date, Time departureTime, Time arrivalTime, Integer waitingTime, Double drivenKilometres) {
        this.vehicleId = vehicleId;
        this.date = date;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.waitingTime = waitingTime;
        this.drivenKilometres = drivenKilometres;
        this.status = Status.ABGESCHLOSSEN;
    }

    public Drive() {

    }

    public Integer getDriveId() {
        return driveId;
    }

    public void setDriveId(Integer driveId) {
        this.driveId = driveId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Double getDrivenKilometres() {
        return drivenKilometres;
    }

    public void setDrivenKilometres(Double drivenKilometres) {
        this.drivenKilometres = drivenKilometres;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}