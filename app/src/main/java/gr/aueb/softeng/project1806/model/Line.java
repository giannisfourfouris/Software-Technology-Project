package gr.aueb.softeng.project1806.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Μία γραμμή και οι κατάλληλες μέθοδοι.
 */
public class Line implements Serializable{

    private String lineCode, lineName;
    private Set<BusTimetable> timetables = new HashSet<BusTimetable>();
    private Stop start;
    private Stop end;
    private Set<Stop> stops = new HashSet<Stop>();


    /**
     * Προκαθορισμένος κατασκευαστής.
     */
    public Line() {

    }


    /**
     * Βοηθητικός κατασκευαστής που δέχεται ως παραμέτρους
     * τον κωδικό της γραμμής και το όνομα της.
     *
     * @param lineCode κωδικός γραμμής.
     * @param lineName όνομα γραμμής.
     */
    public Line(String lineCode, String lineName) {
        this.lineCode = lineCode;
        this.lineName = lineName;
    }

    /**
     * Επιστρέφει τον κωδικό της γραμμής.
     * Ο κωδικός προσδιορίζει μοναδικά κάθε γραμμή.
     *
     * @return ο κωδικός της γραμμής.
     */
    public String getLineCode() {
        return lineCode;
    }


    /**
     * Θέτει τον κωδικό της γραμμής.
     * Ο κωδικός προσδιορίζει μοναδικά κάθε γραμμή.
     *
     * @param lineCode ο κωδικός της γραμμής.
     */
    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }


    /**
     * Επιστρέφει το όνομα της γραμμής.
     *
     * @return το όνομα της γραμμής.
     */
    public String getLineName() {
        return lineName;
    }


    /**
     * Θέτει το όνομα της γραμμής.
     *
     * @param lineName το όνομα της γραμμής.
     */
    public void setLineName(String lineName) {
        this.lineName = lineName;
    }


    /**
     * Επιστρέφει τη στάση-αφετηρία της γραμμής ({@link Stop}).
     *
     * @return η στάση-αφετηρία.
     */
    public Stop getStart() {
        return start;
    }


    /**
     * Θέτει τη στάση-αφετηρία της γραμμής ({@link Stop}).
     *
     * @param start στάση-αφετηρία.
     */
    public void setStart(Stop start) {
        this.start = start;
    }


    /**
     * Επιστρέφει τη στάση-τερματισμό της γραμμής ({@link Stop}).
     *
     * @return η στάση-τερματισμός.
     */
    public Stop getEnd() {
        return end;
    }


    /**
     * Θέτει τη στάση-τερματισμό της γραμμής ({@link Stop}).
     *
     * @param end η στάση-τερματισμός.
     */
    public void setEnd(Stop end) {
        this.end = end;
    }


    /**
     * Προσθήκη μιας στάσης ({@link Stop}) στις στάσεις της γραμμής.
     *
     * @param stop η στάση.
     */
    public void addStop(Stop stop) {
        if (stop != null) {
            stop.friendLines().add(this);
            this.stops.add(stop);
        }

    }


    /**
     * Διαγραφή μιας στάσης ({@link Stop}) από τις στάσεις της γραμμής.
     *
     * @param stop η στάση.
     */
    public void removeStop(Stop stop) {
        if (stop != null) {
            stop.friendLines().remove(this);
            this.stops.remove(stop);
        }
    }


    /**
     * Προσθήκη ενός δρομολογίου ({@link BusTimetable}) στη γραμμή.
     *
     * @param btt το δρομολόγιο.
     */
    public void addBusTimetable(BusTimetable btt) {
        if (btt != null)
            timetables.add(btt);
    }


    /**
     * Διαγραφή ενός δρομολογίου ({@link BusTimetable}) από τη γραμμή.
     *
     * @param btt το δρομολόγιο.
     */
    public void removeBusTimetable(BusTimetable btt) {
        if (timetables != null && btt != null)
            timetables.remove(btt);
    }


    /**
     * Επιστρέφει τα δρομολόγια ({@link BusTimetable}) για κάποια γραμμή.
     * Για την προσθήκη κάποιου δρομολογίου στη συλλογή χρησιμοποιείται
     * η μέθοδος {@link Line#addBusTimetable(BusTimetable)} και για την
     * διαγραφή ενός δρομολογίου, η μέθοδος {@link Line#removeBusTimetable(BusTimetable)}.
     *
     * @return Αντίγραφο των δρομολογίων της γραμμής.
     */
    public Set<BusTimetable> getTimetables() {
        return new HashSet<BusTimetable>(timetables);
    }


    /**
     * Επιστρέφει τις στάσεις ({@link Stop}) για κάποια γραμμή. Για την
     * προσθήκη κάποιας στάσης στη συλλογή χρησιμοποιείται
     * η μέθοδος {@link Line#addStop(Stop)} και για την
     * διαγραφή μιας γραμμής, η μέθοδος {@link Line#removeStop(Stop)}.
     *
     * @return Αντίγραφο των στάσεων της γραμμής.
     */
    public Set<Stop> getStops() {
        return new HashSet<Stop>(stops);
    }


}
