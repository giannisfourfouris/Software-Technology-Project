package gr.aueb.softeng.project1806.memorydao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.softeng.project1806.dao.BusTimetableDAO;
import gr.aueb.softeng.project1806.model.BusTimetable;

/**
 * Η υλοποίηση των αντικειμένων πρόσβασης δεδομένων (DAO) για
 * την κλάση {@link BusTimetable}
 */
public class BusTimetableDAOMemory implements BusTimetableDAO {
    private static ArrayList<BusTimetable> BusTimetables = new ArrayList<BusTimetable>();

    public void add(BusTimetable btt) {
        BusTimetables.add(btt);
    }

    public void remove(BusTimetable btt) {
        BusTimetables.remove(btt);
    }

    public List<BusTimetable> findAll(){
        ArrayList<BusTimetable> result = new ArrayList<BusTimetable>();
        result.addAll(BusTimetables);
        return result;
    }

}


