package gr.aueb.softeng.project1806.presenter;

import gr.aueb.softeng.project1806.memorydao.LineDAOMemory;
import gr.aueb.softeng.project1806.model.Line;

public class LineSearchPresenter {

    Line result;
    /**
     * Ελέγχετε αν υπάρχει κάποια γραμμή στη μνήμη.
     * @param lineInfo Το όνομα η o κωδικός της γραμμής.
     * Επιστρέφει ένα αντικείμενο τύπου Line σε περίπτωση που υπάρχει η γραμμή αλλιώς επιστρεφεί null.
     */

    public Line searchLine(String lineInfo){



        LineDAOMemory lineDAOMemory = new LineDAOMemory();
        result = lineDAOMemory.searchLineByCode(lineInfo.toLowerCase());
        if (result == null)
            result = lineDAOMemory.searchLineByName(lineInfo.toLowerCase());
        else
            return result;
        if(result == null)
            return null;
        else
            return result;

    }
}
