//package at.jku.se.prse.team2.logbook.business;
//
//import at.jku.se.prse.team2.logbook.entities.LogBookEntry;
//
//
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Stateless
//public class LogBookEntryFacade {
//    @PersistenceContext
//    private EntityManager em;
//
//    public LogBookEntryFacade(EntityManager entityManager) {
//        em = entityManager;
//    }
//    /**
//     * returns all loogBookEntries
//     */
//    public List<LogBookEntry> findAll() {
//        return (List<LogBookEntry>) em
//                .createNamedQuery("LogBookEntry.findAll")
//                .getResultList();
//    }
//
//    public void save(LogBookEntry logBookEntry) {
//        em.persist(logBookEntry);
//    }
//}
