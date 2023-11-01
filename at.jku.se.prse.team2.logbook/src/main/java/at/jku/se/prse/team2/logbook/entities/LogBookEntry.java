//package at.jku.se.prse.team2.logbook.entities;
//
//import javax.persistence.*;
//
//@Entity(name = "LogBookEntry")
//@NamedQueries({
//        @NamedQuery(name = "LogBookEntry.findAll", query = "select c from LogBookEntry c")
//})
//public class LogBookEntry {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long logBookId;
//    @Column
//    private String licensePlate;
//
//    public Long getLogBookId() {
//        return logBookId;
//    }
//
//    public LogBookEntry(String licensePlate) {
//        this.licensePlate = licensePlate;
//    }
//}
