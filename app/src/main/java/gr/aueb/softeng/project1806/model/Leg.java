package gr.aueb.softeng.project1806.model;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Ένα σκέλος και οι κατάλληλες μέθοδοι.
 */
public class Leg implements Serializable{

    private int legLength;
    private Stop startStop, endStop;
    private Set<BusTimetable> busTimetableSet = new HashSet<BusTimetable>();


    /**
     * Προκαθορισμένος κατασκευαστής.
     */
    public Leg() {
    }


    /**
     * Βοηθητικός κατασκευαστής που δέχεται ως παραμέτρους
     * το μήκος του σκέλους, τις στάσεις αφετηρίας και τερματισμού.
     *
     * @param legLength μήκος σκέλους.
     * @param startStop στάση αφετηρίας.
     * @param endStop   στάση τερματισμού.
     */
    public Leg(int legLength, Stop startStop, Stop endStop) {
        this.legLength = legLength;
        this.startStop = startStop;
        this.endStop = endStop;
    }


    /**
     * Επιστρέφει το μήκος του σκέλους.
     *
     * @return το μήκος.
     */
    public int getLegLength() {
        return legLength;
    }


    /**
     * Θέτει το μήκος του σκέλους.
     *
     * @param legLength το μήκος.
     */
    public void setLegLength(int legLength) {
        this.legLength = legLength;
    }

    /**
     * Επιστρέφει τη στάση-αφετηρία του σκέλους ({@link Stop}).
     *
     * @return η στάση-αφετηρία.
     */
    public Stop getStartStop() {
        return startStop;
    }


    /**
     * Θέτει τη στάση-αφετηρία του σκέλους ({@link Stop}).
     *
     * @param startStop στάση-αφετηρία.
     */
    public void setStartStop(Stop startStop) {
        this.startStop = startStop;
    }


    /**
     * Επιστρέφει τη στάση-τερματισμό του σκέλους ({@link Stop}).
     *
     * @return η στάση-τερματισμός.
     */
    public Stop getEndStop() {
        return endStop;
    }


    /**
     * Θέτει τη στάση-τερματισμό του σκέλους ({@link Stop}).
     *
     * @param endStop η στάση-τερματισμός.
     */
    public void setEndStop(Stop endStop) {
        this.endStop = endStop;
    }


    /**
     * Επιστρέφει τα δρομολόγια ({@link BusTimetable}) για κάποιο σκέλος.
     * Για την προσθήκη κάποιου δρομολογίου στη συλλογή χρησιμοποιείται
     * η μέθοδος {@link Leg#addBusTimetable(BusTimetable)} και για την
     * διαγραφή ενός δρομολογίου, η μέθοδος {@link Leg#removeBusTimetable(BusTimetable)}.
     *
     * @return Αντίγραφο των δρομολογίων του σκέλους.
     */
    public Set<BusTimetable> getBusTimetableSet() {
        return new HashSet<BusTimetable>(busTimetableSet);
    }


    /**
     * Προσθήκη ενός δρομολογίου ({@link BusTimetable}) στο σκέλος.
     *
     * @param btt το δρομολόγιο.
     */
    public void addBusTimetable(BusTimetable btt) {
        if (btt != null) {
            btt.addLeg(this);
        }
    }


    /**
     * Διαγραφή ενός δρομολογίου ({@link BusTimetable}) από το σκέλος.
     *
     * @param btt το δρομολόγιο.
     */
    public void removeBusTimetable(BusTimetable btt) {
        if (btt != null) {
            btt.removeLeg(this);
        }
    }

    /**
     * Μη ενθυλακωμένη συλλογή των δρομολογίων του σκέλους.
     *
     * @return τα δρομολόγια του σκέλους.
     */
    public Set<BusTimetable> friendBusTimetables() {
        return busTimetableSet;
    }

}
