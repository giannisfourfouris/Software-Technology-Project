package gr.aueb.softeng.project1806.dao;

import java.util.List;

import gr.aueb.softeng.project1806.model.Line;


/** Η διεπαφή DAO για την κλάση {@link Line}.
 */

public interface LineDAO {

    /**
     * Αποθηκεύει ένα αντικείμενο στην πηγή
     * δεδομένων. Το αντικείμενο μπορεί να είναι κάποιο
     * νέο αντικείμενο που δεν υπάρχει στην πηγή δεδομένων
     * ή κάποιο το οποίο ήδη υπάρχει και ενημερώνεται η
     * κατάστασή του.
     * @param line Το αντικείμενο του οποίου η κατάσταση
     * αποθηκεύεται στην πηγή δεδομένων.
     */
    void add(Line line);


    /**
     * Διαγράφει το αντικείμενο από την πηγή δεδομένων.
     * @param line Το αντικείμενο προς διαγραφή.
     */
    void remove(Line line);

    /**
     * Επιστρέφει όλα τα αντικείμενα  από την πηγή δεδομένων.
     * @return Ο κατάλογος των αντικειμένων
     */
    List<Line> findAll();

    /**
     * Επιστρέφει τη γραμμή για κάποιο κωδικό.
     * @param code Ο κωδικός της γραμμής
     * @return Η γραμμή
     */

    Line searchLineByCode(String code);

    /**
     * Επιστρέφει τη γραμμή για κάποιο όνομα.
     * @param name Το όνομα της γραμμής
     * @return Η γραμμή
     */
    Line searchLineByName(String name);


}
