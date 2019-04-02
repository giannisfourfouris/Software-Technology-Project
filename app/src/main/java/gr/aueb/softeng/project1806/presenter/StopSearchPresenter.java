package gr.aueb.softeng.project1806.presenter;

import java.util.List;

import gr.aueb.softeng.project1806.memorydao.StopDAOMemory;
import gr.aueb.softeng.project1806.model.Location;
import gr.aueb.softeng.project1806.model.Stop;

public class StopSearchPresenter {

    Stop result;
    List<Stop> resultList;

    /**
     * Ελέγχετε αν υπάρχει κάποια στάση στη μνήμη.
     * @param stopInfo Το όνομα της στάσης.
     * Επιστρέφει ένα αντικείμενο τύπου Stop σε περίπτωση που υπάρχει η στάση αλλιώς επιστρεφεί null.
     */

    public Stop searchStopByName(String stopInfo){

        StopDAOMemory stopDAOMemory = new StopDAOMemory();
        result = stopDAOMemory.searchStopByName(stopInfo.toLowerCase());
        if(result == null)
            return null;
        else
            return result;

    }

    /**
     * Ελέγχετε αν υπάρχει κάποια στάση στη μνήμη.
     * @param stopInfo Ο κωδικός της στάσης.
     * Επιστρέφει ένα αντικείμενο τύπου Stop σε περίπτωση που υπάρχει η στάση αλλιώς επιστρεφεί null.
     */

    public Stop searchStopById(String stopInfo){

        StopDAOMemory stopDAOMemory = new StopDAOMemory();

        result = stopDAOMemory.searchStopByID(Integer.parseInt(stopInfo.toLowerCase()));
        if(result == null)
            return null;
        else
            return result;

    }

    /**
     * Ελέγχετε αν υπάρχει κάποια στάση στη μνήμη.
     * @param location H τοποθεσά της στάσης.
     * Επιστρέφει ένα αντικείμενο τύπου Stop σε περίπτωση που υπάρχει η στάση αλλιώς επιστρεφεί null.
     */

    public List<Stop> searchStopByLocation(Location location){
        StopDAOMemory stopDAOMemory = new StopDAOMemory();
        resultList = stopDAOMemory.searchStopByLocation(location);
        return resultList;
    }
}

