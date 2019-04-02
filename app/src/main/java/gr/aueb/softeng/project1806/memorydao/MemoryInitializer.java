package gr.aueb.softeng.project1806.memorydao;

import java.util.List;

import gr.aueb.softeng.project1806.dao.BusTimetableDAO;
import gr.aueb.softeng.project1806.dao.Initializer;
import gr.aueb.softeng.project1806.dao.LegDAO;
import gr.aueb.softeng.project1806.dao.LineDAO;
import gr.aueb.softeng.project1806.dao.LocationDAO;
import gr.aueb.softeng.project1806.dao.StopDAO;
import gr.aueb.softeng.project1806.model.BusTimetable;
import gr.aueb.softeng.project1806.model.Leg;
import gr.aueb.softeng.project1806.model.Line;
import gr.aueb.softeng.project1806.model.Location;
import gr.aueb.softeng.project1806.model.Stop;

public class MemoryInitializer extends Initializer {


    @Override
    protected void eraseData() {
        List<Location> allLocations = getLocationDAO().findAll();
        for(Location loc : allLocations)
            getLocationDAO().remove(loc);

        List<Stop> allStops = getStopDAO().findAll();
        for(Stop stop : allStops)
            getStopDAO().remove(stop);

        List<BusTimetable> allBusTimetables = getBusTimetableDAO().findAll();
        for(BusTimetable btt : allBusTimetables)
            getBusTimetableDAO().remove(btt);

        List<Line> allLines = getLineDAO().findAll();
        for(Line line : allLines)
            getLineDAO().remove(line);

        List<Leg> allLegs = getLegDAO().findAll();
        for(Leg leg : allLegs)
            getLegDAO().remove(leg);
    }

    @Override
    protected LocationDAO getLocationDAO() {
        return new LocationDAOMemory();
    }

    @Override
    protected StopDAO getStopDAO() {
        return new StopDAOMemory();
    }

    @Override
    protected BusTimetableDAO getBusTimetableDAO() {
        return new BusTimetableDAOMemory();
    }

    @Override
    protected LegDAO getLegDAO() {
        return new LegDAOMemory();
    }

    @Override
    protected LineDAO getLineDAO() {
        return new LineDAOMemory();
    }
}
