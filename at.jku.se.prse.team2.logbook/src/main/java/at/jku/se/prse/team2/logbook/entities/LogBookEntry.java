package at.jku.se.prse.team2.logbook.entities;

import java.sql.Date;

public class LogBookEntry {

    private Long logBookId;
    private String licensePlate;
    private Date date;
    private Date departureTime;
    private Date arrivalTime;
    private Integer drivenKilometres;
    private Integer odometer;


    public Long getLogBookId() {
        return logBookId;
    }

    public LogBookEntry(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
