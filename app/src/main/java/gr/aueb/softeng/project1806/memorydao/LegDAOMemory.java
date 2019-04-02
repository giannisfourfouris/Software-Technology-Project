package gr.aueb.softeng.project1806.memorydao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.softeng.project1806.dao.LegDAO;
import gr.aueb.softeng.project1806.model.Leg;

/**
 * Η υλοποίηση των αντικειμένων πρόσβασης δεδομένων (DAO) για
 * την κλάση {@link Leg}
 */
public class LegDAOMemory implements LegDAO {

    private static ArrayList<Leg> Legs = new ArrayList<Leg>();
        public void  add(Leg leg){
            Legs.add(leg);
        }

        public void remove(Leg leg){
            Legs.remove(leg);
        }

        public List<Leg> findAll(){
            ArrayList<Leg> result = new ArrayList<Leg>();
            result.addAll(Legs);
            return result;
    }
}
