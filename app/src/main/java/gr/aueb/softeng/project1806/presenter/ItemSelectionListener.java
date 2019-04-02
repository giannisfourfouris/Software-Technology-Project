package gr.aueb.softeng.project1806.presenter;

public interface ItemSelectionListener<T> {
    /**
     * Μεταφερόμαστε εδώ απο το StopAdapter.
     * Επιλέγουμε ένα αντικειμένο απο τις στάσεις στην addStopsToLine
     * Προσθέτει και αφαιρει στην λιστα stopsToAdd τις στάσεις που επιλέγει ο χρήστης
     */
    void onItemSelected(T item, int id);
}
