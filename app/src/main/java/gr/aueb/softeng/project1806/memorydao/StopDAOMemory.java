package gr.aueb.softeng.project1806.memorydao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.softeng.project1806.dao.StopDAO;
import gr.aueb.softeng.project1806.model.Location;
import gr.aueb.softeng.project1806.model.Stop;

/**
 * Η υλοποίηση των αντικειμένων πρόσβασης δεδομένων (DAO) για
 * την κλάση {@link Stop}
 */

public class StopDAOMemory implements StopDAO {

    protected static ArrayList<Stop> Stops = new ArrayList<Stop>();

    public void add(Stop stop){
        Stops.add(stop);
    }

    public void remove(Stop stop){
        Stops.remove(stop);
    }

    public List<Stop> findAll(){
        ArrayList<Stop> result = new ArrayList<Stop>();
        result.addAll(Stops);
        return result;
    }

    public Stop searchStopByID(int id){
        for (Stop stop : Stops)
            if (id == stop.getStopID())
                return stop;
        return null;
    }

    public Stop searchStopByName(String name){
        for (Stop stop : Stops)
            if (name.trim().toLowerCase().equals(stop.getStopName().trim().toLowerCase()))
                return stop;
        return null;
    }

    public List<Stop> searchStopByLocation(Location location){
        ArrayList<Stop> result = new ArrayList<Stop>();
        for (Stop stop : Stops)
            if (location.distanceBetweenLocations(stop.getLocation()) <= 500)
                result.add(stop);
        return result;
    }

}
