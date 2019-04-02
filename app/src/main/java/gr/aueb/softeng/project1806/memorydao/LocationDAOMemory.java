package gr.aueb.softeng.project1806.memorydao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.softeng.project1806.dao.LocationDAO;
import gr.aueb.softeng.project1806.model.BusTimetable;
import gr.aueb.softeng.project1806.model.Location;

/**
 * Η υλοποίηση των αντικειμένων πρόσβασης δεδομένων (DAO) για
 * την κλάση {@link Location}
 */
public class LocationDAOMemory implements LocationDAO {

static ArrayList<Location> Locations = new ArrayList<Location>();

    public void add(Location location){
        Locations.add(location);
    }

    public void remove(Location location){
        Locations.remove(location);
    }

    public List<Location> findAll(){
        ArrayList<Location> result = new ArrayList<Location>();
        result.addAll(Locations);
        return result;
    }
}
