package gr.aueb.softeng.project1806.model;


import java.io.Serializable;
import java.sql.Time;
import java.util.*;


/**
 * Ένα δρομολόγιο και οι κατάλληλες μέθοδοι.
 */
public class BusTimetable implements Serializable {

    private Time startTimeFromStart;
    private String Day;
    private Set<Leg> legs = new HashSet<Leg>();


    /**
     * Προκαθορισμένος κατασκευαστής.
     */
    public BusTimetable() {
    }


    /**
     * Βοηθητικός κατασκευαστής που δέχεται ως παραμέτρους
     * την ώρα έναρξης και την ημέρα της εβδομάδας.
     *
     * @param startTimeFromStart ώρα έναρξης.
     * @param day                ημέρα εβδομάδας.
     */
    public BusTimetable(Time startTimeFromStart, String day) {
        this.startTimeFromStart = startTimeFromStart;
        this.Day = day;
    }


    /**
     * Επιστρέφει την ώρα έναρξης του δρομολογίου ({@link Time}).
     *
     * @return ώρα έναρξης.
     */
    public Time getStartTimeFromStart() {
        return startTimeFromStart;
    }


    /**
     * Θέτει την ώρα έναρξης του δρομολογίου ({@link Time}).
     *
     * @param startTimeFromStart η ώρα έναρξης.
     */
    public void setStartTimeFromStart(Time startTimeFromStart) {
        this.startTimeFromStart = startTimeFromStart;
    }


    /**
     * Επιστρέφει την ημέρα του δρομολογίου ({@link String}).
     *
     * @return ημέρα εβδομάδας.
     */
    public String getDay() {
        return Day;
    }


    /**
     * Θέτει την ημέρα του δρομολογίου ({@link String}).
     *
     * @param day η ημέρα.
     */
    public void setDay(String day) {
        Day = day;
    }


    /**
     * Επιστρέφει τα σκέλη ({@link Leg}) για κάποιο δρομολόγιο.
     * Για την προσθήκη κάποιου σκέλους στη συλλογή χρησιμοποιείται
     * η μέθοδος {@link BusTimetable#addLeg(Leg)} και για την
     * διαγραφή ενός σκέλους, η μέθοδος {@link BusTimetable#removeLeg(Leg)}.
     *
     * @return Αντίγραφο των σκελών ενός δρομολογίου.
     */
    public Set<Leg> getLegs() {
        return new HashSet<Leg>(legs);
    }


    /**
     * Προσθήκη ενός σκέλους ({@link Leg}) στο δρομολόγιο.
     *
     * @param leg το σκέλος.
     */
    public void addLeg(Leg leg) {
        if (leg != null) {
            leg.friendBusTimetables().add(this);
            this.legs.add(leg);
        }
    }


    /**
     * Διαγραφή ενός σκέλους ({@link Leg}) από το δρομολόγιο.
     *
     * @param leg το σκέλος.
     */
    public void removeLeg(Leg leg) {
        if (leg != null) {
            leg.friendBusTimetables().remove(this);
            this.legs.remove(leg);
        }
    }


}
