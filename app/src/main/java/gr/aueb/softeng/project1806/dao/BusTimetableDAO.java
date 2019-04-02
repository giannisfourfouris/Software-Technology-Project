package gr.aueb.softeng.project1806.dao;

import java.util.List;

import gr.aueb.softeng.project1806.model.BusTimetable;

/** Η διεπαφή DAO για την κλάση {@link BusTimetable}.
 */
public interface BusTimetableDAO {

    /**
     * Αποθηκεύει ένα αντικείμενο στην πηγή
     * δεδομένων. Το αντικείμενο μπορεί να είναι κάποιο
     * νέο αντικείμενο που δεν υπάρχει στην πηγή δεδομένων
     * ή κάποιο το οποίο ήδη υπάρχει και ενημερώνεται η
     * κατάστασή του.
     * @param btt Το αντικείμενο του οποίου η κατάσταση
     * αποθηκεύεται στην πηγή δεδομένων.
     */
    void add(BusTimetable btt);

    /**
     * Διαγράφει το αντικείμενο από την πηγή δεδομένων.
     * @param btt Το αντικείμενο προς διαγραφή.
     */
    void remove(BusTimetable btt);

    /**
     * Επιστρέφει όλα τα αντικείμενα  από την πηγή δεδομένων.
     * @return Ο κατάλογος των αντικειμένων
     */
    List<BusTimetable> findAll();
}
