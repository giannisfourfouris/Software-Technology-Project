package gr.aueb.softeng.project1806.dao;

import java.util.List;

import gr.aueb.softeng.project1806.model.Location;

/** Η διεπαφή DAO για την κλάση {@link Location}.
 */
public interface LocationDAO {
    /**
     * Αποθηκεύει ένα αντικείμενο στην πηγή
     * δεδομένων. Το αντικείμενο μπορεί να είναι κάποιο
     * νέο αντικείμενο που δεν υπάρχει στην πηγή δεδομένων
     * ή κάποιο το οποίο ήδη υπάρχει και ενημερώνεται η
     * κατάστασή του.
     * @param location Το αντικείμενο του οποίου η κατάσταση
     * αποθηκεύεται στην πηγή δεδομένων.
     */
    void add(Location location);


    /**
     * Διαγράφει το αντικείμενο από την πηγή δεδομένων.
     * @param location Το αντικείμενο προς διαγραφή.
     */
    void remove(Location location);

    /**
     * Επιστρέφει όλα τα αντικείμενα  από την πηγή δεδομένων.
     * @return Ο κατάλογος των αντικειμένων
     */
    List<Location> findAll();



}
