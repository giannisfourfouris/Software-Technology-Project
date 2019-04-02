package gr.aueb.softeng.project1806.memorydao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.softeng.project1806.dao.LineDAO;
import gr.aueb.softeng.project1806.model.Line;

/**
 * Η υλοποίηση των αντικειμένων πρόσβασης δεδομένων (DAO) για
 * την κλάση {@link Line}
 */

public class LineDAOMemory implements LineDAO {

    private static ArrayList<Line> Lines = new ArrayList<Line>();

    public void add(Line line){
        Lines.add(line);
    }

    public void remove(Line line){
        Lines.remove(line);
    }

    public List<Line> findAll(){
        ArrayList<Line> result = new ArrayList<Line>();
        result.addAll(Lines);
        return result;
    }

    public Line searchLineByCode(String code){
        for (Line line : Lines)
            if (code.trim().toLowerCase().equals(line.getLineCode().trim().toLowerCase()))
                return line;
        return null;
    }

    public Line searchLineByName(String name){
        for (Line line : Lines)
            if (name.trim().toLowerCase().equals(line.getLineName().trim().toLowerCase()))
                return line;
        return null;
    }
}
