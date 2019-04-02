package gr.aueb.softeng.project1806.dao;

import java.util.List;

import gr.aueb.softeng.project1806.model.Location;
import gr.aueb.softeng.project1806.model.Stop;

/** Η διεπαφή DAO για την κλάση {@link Stop}.
 */

public interface StopDAO {

    /**
     * Αποθηκεύει ένα αντικείμενο στην πηγή
     * δεδομένων. Το αντικείμενο μπορεί να είναι κάποιο
     * νέο αντικείμενο που δεν υπάρχει στην πηγή δεδομένων
     * ή κάποιο το οποίο ήδη υπάρχει και ενημερώνεται η
     * κατάστασή του.
     * @param stop Το αντικείμενο του οποίου η κατάσταση
     * αποθηκεύεται στην πηγή δεδομένων.
     */
    void add(Stop stop);


    /**
     * Διαγράφει το αντικείμενο από την πηγή δεδομένων.
     * @param stop Το αντικείμενο προς διαγραφή.
     */
    void remove(Stop stop);


    /**
     * Επιστρέφει όλα τα αντικείμενα  από την πηγή δεδομένων.
     * @return Ο κατάλογος των αντικειμένων
     */
     List<Stop> findAll();

    /**
     * Επιστρέφει τη στάση για κάποιο κωδικό.
     * @param id Ο κωδικός της στάσης
     * @return Η στάση
     */

     Stop searchStopByID(int id);
    /**
     * Επιστρέφει τη στάση για κάποιο όνομα.
     * @param name Το όνομα της στάσης
     * @return Η στάση
     */

     Stop searchStopByName(String name);

    /**
     * Επιστρέφει τα αντικείμενα της στάσης τα οποία είναι κοντά σε μια τοποθεσία.
     * @param location Η τοποθεσία
     * @return Ο κατάλογος των αντικειμένων
     */

     List<Stop> searchStopByLocation(Location location);



}
