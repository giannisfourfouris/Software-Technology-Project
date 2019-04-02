package gr.aueb.softeng.project1806.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import gr.aueb.softeng.project1806.memorydao.BusTimetableDAOMemory;
import gr.aueb.softeng.project1806.memorydao.LegDAOMemory;
import gr.aueb.softeng.project1806.memorydao.LineDAOMemory;
import gr.aueb.softeng.project1806.memorydao.LocationDAOMemory;
import gr.aueb.softeng.project1806.memorydao.MemoryInitializer;
import gr.aueb.softeng.project1806.memorydao.StopDAOMemory;
import gr.aueb.softeng.project1806.model.BusTimetable;
import gr.aueb.softeng.project1806.model.Leg;
import gr.aueb.softeng.project1806.model.Line;
import gr.aueb.softeng.project1806.model.Location;
import gr.aueb.softeng.project1806.model.Stop;

/**
 * Κλάση ελέγχου για τις βασικές πράξεις των αντικειμένων πρόσβασης δεδομένων
 */


public class DAOTest {
    private BusTimetableDAO bustimetableDao;
    private LegDAO legDao;
    private LineDAO lineDao;
    private LocationDAO locationDao;
    private StopDAO stopDao;

    private static final int INITIAL_BUSTIMETABLE_COUNT = 2;
    private static final int INITIAL_lEG_COUNT = 9;
    private static final int INITIAL_LINE_COUNT = 2;
    private static final int INITIAL_LOCATION_COUNT = 10;
    private static final int INITIAL_STOP_COUNT = 10;

    @Before
    public void setUp() {
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        bustimetableDao = new BusTimetableDAOMemory();
        legDao = new LegDAOMemory();
        lineDao = new LineDAOMemory();
        locationDao = new LocationDAOMemory();
        stopDao = new StopDAOMemory();
    }

    /**
     * Αποθήκευση δρομολογίου
     */
    @Test
    public void addBusTimetable() {
        BusTimetable btt = new BusTimetable(new Time(14,55,0), "Monday");
        bustimetableDao.add(btt);
        Assert.assertEquals(INITIAL_BUSTIMETABLE_COUNT + 1, bustimetableDao.findAll().size());
        Assert.assertTrue(bustimetableDao.findAll().contains(btt));
    }

    /**
     * Διαγραφή δρομολογίου
     */
    @Test
    public void removeBusTimetable() {
        List<BusTimetable> allBusTimetables = bustimetableDao.findAll();
        BusTimetable btt = allBusTimetables.get(0);
        bustimetableDao.remove(btt );
        allBusTimetables = bustimetableDao.findAll();
        Assert.assertEquals(INITIAL_BUSTIMETABLE_COUNT - 1, allBusTimetables.size());

    }

    /**
     * Εύρεση καταλόγου δρομολογίων
     */
    @Test
    public void listAllBusTimetables() {
        List<BusTimetable> allBusTimeTables = bustimetableDao.findAll();
        Assert.assertEquals(INITIAL_BUSTIMETABLE_COUNT, allBusTimeTables.size());
    }


    /**
     * Αποθήκευση τοποθεσίας
     */
    @Test
    public void addLocation() {
        Location loc = new Location(44.92, 66.643);
        locationDao.add(loc);
        Assert.assertEquals(INITIAL_LOCATION_COUNT + 1, locationDao.findAll().size());
        Assert.assertTrue(locationDao.findAll().contains(loc));
    }

    /**
     * Διαγραφή τοποθεσίας
     */
    @Test
    public void removeLocation() {
        List<Location> allLocations = locationDao.findAll();
        Location loc = allLocations.get(0);
        locationDao.remove(loc);
        allLocations = locationDao.findAll();
        Assert.assertEquals(INITIAL_LOCATION_COUNT - 1, allLocations.size());

    }

    /**
     * Εύρεση καταλόγου τοποθεσιών
     */
    @Test
    public void listAllLocations() {
        List<Location> allLocations = locationDao.findAll();
        Assert.assertEquals(INITIAL_LOCATION_COUNT, allLocations.size());
    }



    /**
     * Αποθήκευση σκελούς
     */
    @Test
    public void addLeg() {
        Location loc1 = new Location(44.323232, 54.232323);
        Location loc2 = new Location(23.454544, 87.567856);
        Stop stop1 = new Stop(1, "1hstash", loc1);
        Stop stop2 = new Stop(2, "2hstash", loc2);
        Leg leg = new Leg(100,stop1, stop2);
        legDao.add(leg);
        Assert.assertEquals(INITIAL_lEG_COUNT + 1, legDao.findAll().size());
        Assert.assertTrue(legDao.findAll().contains(leg));
    }

    /**
     * Διαγραφή σκέλους
     */
    @Test
    public void removeLeg() {
        List<Leg> allLegs = legDao.findAll();
        Leg leg = allLegs.get(0);
        legDao.remove(leg);
        allLegs = legDao.findAll();
        Assert.assertEquals(INITIAL_lEG_COUNT - 1, allLegs.size());

    }

    /**
     * Εύρεση καταλόγου σκελών
     */
    @Test
    public void listAllLegs() {
        List<Leg> allLegs = legDao.findAll();
        Assert.assertEquals(INITIAL_lEG_COUNT, allLegs.size());
    }


    /**
     * Αποθήκευση στάσης
     */
    @Test
    public void addStop() {
        Location loc = new Location(43.2, 124.4);
        Stop stop = new Stop(4387, "stashtest", loc);
        stopDao.add(stop);
        Assert.assertEquals(INITIAL_STOP_COUNT + 1, stopDao.findAll().size());
        Assert.assertTrue(stopDao.findAll().contains(stop));
    }

    /**
     * Διαγραφή στάσης
     */
    @Test
    public void removeStop() {
        List<Stop> allStops = stopDao.findAll();
        Stop stop = allStops.get(0);
        stopDao.remove(stop);
        allStops= stopDao.findAll();
        Assert.assertEquals(INITIAL_STOP_COUNT - 1, allStops.size());

    }

    /**
     * Εύρεση καταλόγου στάσεων
     */
    @Test
    public void listAllStops() {
        List<Stop> allStops = stopDao.findAll();
        Assert.assertEquals(INITIAL_STOP_COUNT, allStops.size());
    }

    /**
     * Αναζήτηση στασής που υπάρχει στη βάση δεδομένων με βάση το κωδίκο
     */
    @Test
    public void findExistingStopById() {

       Stop stop = stopDao.searchStopByID(1);
        Assert.assertEquals(1 , stop.getStopID());
    }


    /**
     * Αναζήτηση στάσης που δεν υπάρχει στη βάση δεδομένων με βάση το κωδικό
     */
    @Test
    public void findNonExistingStopById() {
        Stop stop = stopDao.searchStopByID(10000);
        Assert.assertNull(stop);
    }

    /**
     * Αναζήτηση στασής που υπάρχει στη βάση δεδομένων με βάση το όνομα
     */
    @Test
    public void findExistingStopByName() {

        Stop stop = stopDao.searchStopByName("1η Στάση");
        Assert.assertEquals("1η Στάση" , stop.getStopName());
    }


    /**
     * Αναζήτηση στάσης που δεν υπάρχει στη βάση δεδομένων με βάση το ονόμα
     */
    @Test
    public void findNonExistingStopByName() {
        Stop stop = stopDao.searchStopByName("otinanai");
        Assert.assertNull(stop);
    }

    /**
     * Αναζήτηση στασής που υπάρχει στη βάση δεδομένων με βάση τοποθεσία
     */
    @Test
    public void findExistingStopByLocation() {

        List<Stop> stop = stopDao.searchStopByLocation(new Location(37.973571, 23.761745));
        Assert.assertEquals(1 ,stop.size()) ;

    }


    /**
     * Αναζήτηση στάσης που δεν υπάρχει στη βάση δεδομένων με βάση τοποθεσία
     */
    @Test
    public void findNonExistingStopByLocation() {
        List<Stop> stop = stopDao.searchStopByLocation(new Location(1,1));
        Assert.assertEquals(0,stop.size());
    }



    /**
     * Αποθήκευση γραμμής
     */
    @Test
    public void addLine() {
        Line line = new Line("A3", "Elhniko-Anthoupoli");
        lineDao.add(line);
        Assert.assertEquals(INITIAL_LINE_COUNT + 1, lineDao.findAll().size());
        Assert.assertTrue(lineDao.findAll().contains(line));
    }

    /**
     * Διαγραφή γραμμής
     */
    @Test
    public void removeLine() {
        List<Line> allLines = lineDao.findAll();
        Line line = allLines.get(0);
        lineDao.remove(line);
        allLines= lineDao.findAll();
        Assert.assertEquals(INITIAL_LINE_COUNT - 1, allLines.size());

    }

    /**
     * Εύρεση καταλόγου γραμμών
     */
    @Test
    public void listAllLines() {
        List<Line> allLines = lineDao.findAll();
        Assert.assertEquals(INITIAL_LINE_COUNT, allLines.size());
    }

    /**
     * Αναζήτηση γραμμής που υπάρχει στη βάση δεδομένων με βάση το κωδίκο
     */
    @Test
    public void findExistingLineByCOde() {

        Line line = lineDao.searchLineByCode("Πράσινη");
        Assert.assertEquals("Πράσινη" , line.getLineCode());
    }


    /**
     * Αναζήτηση γραμμης που δεν υπάρχει στη βάση δεδομένων με βάση το κωδικό
     */
    @Test
    public void findNonExistingLineByCode() {

       Line line = lineDao.searchLineByCode("10000");
        Assert.assertNull(line);
    }

    /**
     * Αναζήτηση γραμμης που υπάρχει στη βάση δεδομένων με βάση το όνομα
     */
    @Test
    public void findExistingLineByName() {

        Line line = lineDao.searchLineByName("Κηφισιά-Πειραιάς");
        Assert.assertEquals("Κηφισιά-Πειραιάς" , line.getLineName());
    }


    /**
     * Αναζήτηση γραμμης που δεν υπάρχει στη βάση δεδομένων με βάση το ονόμα
     */
    @Test
    public void findNonExistingLineByName() {
        Line line = lineDao.searchLineByCode("otinanai");
        Assert.assertNull(line);
    }



}
