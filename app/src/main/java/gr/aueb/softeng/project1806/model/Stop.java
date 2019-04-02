package gr.aueb.softeng.project1806.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.*;


/**
 * Μία στάση και οι κατάλληλες μέθοδοι.
 */
public class Stop implements Serializable{

    protected int stopID;
    private String stopName;
    private Location location;
    private Set<Line> lines = new HashSet<Line>();


    /**
     * Προκαθορισμένος κατασκευαστής.
     */
    public Stop() {

    }

    /**
     * Βοηθητικός κατασκευαστής που δέχεται ως παραμέτρους
     * το ID της στάσης, το όνομα της και την ακριβή τοποθεσία της.
     *
     * @param stopID   ID στάσης.
     * @param stopName όνομα στάσης.
     * @param location τοποθεσία στάσης.
     */
    public Stop(int stopID, String stopName, Location location) {
        this.stopID = stopID;
        this.stopName = stopName;
        this.location = location;
    }


    /**
     * Επιστρέφει το ID της στάσης.
     * Το ID προσδιορίζει μοναδικά κάθε στάση.
     *
     * @return το ID της στάσης.
     */
    public int getStopID() {
        return stopID;
    }


    /**
     * Θέτει το ID κάθε στάσης.
     * Το ID προσδιορίζει μοναδικά κάθε στάση.
     *
     * @param stopID το ID στάσης.
     */
    public void setStopID(int stopID) {
        this.stopID = stopID;
    }


    /**
     * Επιστρέφει το όνομα της στάσης.
     *
     * @return το όνομα της στάσης.
     */
    public String getStopName() {
        return stopName;
    }


    /**
     * Θέτει το όνομα της στάσης.
     *
     * @param stopName το όνομα της στάσης.
     */
    public void setStopName(String stopName) {
        this.stopName = stopName;
    }


    /**
     * Επιστρέφει την τοποθεσία της στάσης.
     *
     * @return την τοποθεσία της στάσης.
     */
    public Location getLocation() {
        return location;
    }


    /**
     * Θέτει την τοποθεσία της στάσης.
     *
     * @param location η τοποθεσία της στάσης.
     */
    public void setLocation(Location location) {
        this.location = location;
    }


    /**
     * Επιστρέφει τις γραμμές ({@link Line}) για κάποια στάση. Για την
     * προσθήκη κάποιας γραμμής στη συλλογή χρησιμοποιείται
     * η μέθοδος {@link Stop#addLine(Line)} και για την
     * διαγραφή μιας γραμμής, η μέθοδος {@link Stop#removeLine(Line)}.
     *
     * @return Αντίγραφο των γραμμών της στάσης.
     */
    public Set<Line> getLines() {
        return new HashSet<Line>(lines);
    }


    /**
     * Προσθήκη μιας γραμμής ({@link Line}) στις γραμμές της στάσης.
     *
     * @param line η γραμμή.
     */
    public void addLine(Line line) {
        if (line != null) {
            line.addStop(this);
        }
    }


    /**
     * Διαγραφή μιας γραμμής ({@link Line}) από τις γραμμές της στάσης.
     *
     * @param line η γραμμή.
     */
    public void removeLine(Line line) {
        if (line != null) {
            line.removeStop(this);
        }
    }


    /**
     * Μη ενθυλακωμένη συλλογή των γραμμών της στάσης.
     *
     * @return Τις γραμμές της στάσης.
     */
    public Set<Line> friendLines() {
        return lines;
    }



}
