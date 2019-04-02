package gr.aueb.softeng.project1806.model;

import org.junit.*;


import java.util.HashSet;
import java.util.Set;


public class LineTest {

    Line line,linenull;
    private String lineCode, lineName;
    private Set<BusTimetable> timetables;
    private Stop start;
    private Stop end;
    private Set<Stop> stops = new HashSet<Stop>();
    private Set<Line> lines = new HashSet<Line>();
    ;

    @Before
    public void setUp() throws Exception {
         linenull = new Line();
        Stop stop = new Stop();
        line = new Line("A3", "Elhniko-Anthoupoli");
        Set<BusTimetable> timetables = new HashSet<BusTimetable>();
        start = new Stop(1, "Lotsou", new Location(2340, 1234));
        end = new Stop(2, "Parathira", new Location(2500, 8904));
        line.setStart(start);
        line.setEnd(end);
        lines.add(line);


    }


    @Test
    public void getLineCode() {
        Assert.assertEquals("A3", line.getLineCode());
    }

    @Test
    public void setLineCode() {
        linenull.setLineCode("A2");
        Assert.assertEquals("A2", linenull.getLineCode());
    }

    @Test
    public void getLineName() {
        Assert.assertEquals("Elhniko-Anthoupoli", line.getLineName());
    }

    @Test
    public void setLineName() {
        line.setLineName("Elhniko-Syntagma");
        Assert.assertEquals("Elhniko-Syntagma", line.getLineName());
    }

    @Test
    public void getStart() {
        Assert.assertSame(start, line.getStart());
    }

    @Test
    public void setStart() {
        Stop start1 = new Stop(73, "Astunomia", new Location(4490, 5566));
        line.setStart(start1);
        Assert.assertSame(start1, line.getStart());


    }

    @Test
    public void getEnd() {
        Assert.assertSame(end, line.getEnd());
    }

    @Test
    public void setEnd() {
        Stop end1 = new Stop(5, "Dafni", new Location(2110, 3004));
        line.setEnd(end1);
        Assert.assertSame(end1, line.getEnd());
    }


    /**
     * Προσθήκη κενής στάσης σε μια γραμμή
     */

    @Test
    public void addStopNull() {
        Line linec=new Line();
        linec.addStop(null);
        Assert.assertEquals(0, linec.getStops().size());
        stopBidirectionalAssociationInvariant(linec);
    }

    /**
     * Προσθήκη στάσης σε μια γραμμή
     */
    @Test
    public void addStop() {

        Stop stop1 = new Stop(3, "Astunomia", new Location(4490, 5566));
        line.addStop(stop1);
        Assert.assertEquals(1, line.getStops().size());
        Assert.assertTrue(line.getStops().contains(stop1));
        stopBidirectionalAssociationInvariant(line);

    }
    /**
     * Διαγραφή στάσης από μια γραμμή
     */

    @Test
    public void removeStop() {
        Stop stop = new Stop();
        line.addStop(stop);
        stopBidirectionalAssociationInvariant(line);
        Assert.assertEquals(1, line.getStops().size());
        line.removeStop(stop);
        stopBidirectionalAssociationInvariant(line);
        Assert.assertEquals(0, line.getStops().size());


    }
    @Test


    /**
     * Διαγραφή κενής στάσης από μια γραμμή
     */
    public void removeStopNull() {
        Stop stop = new Stop();
        line.addStop(stop);
        line.removeStop(null);
        stopBidirectionalAssociationInvariant(line);
        Assert.assertEquals(1, line.getStops().size());


    }



    /**
     * Προσθήκη δρομολογίου σε μια γραμμή
     */

    @Test
    public void addBusTimetable() {
        BusTimetable b1=new BusTimetable();
        line.addBusTimetable(b1);
        Assert.assertEquals(1, line.getTimetables().size());
        Assert.assertTrue(line.getTimetables().contains(b1));

    }


    /**
     * Προσθήκη κενού δρομολογίου σε μια γραμμή
     */
    @Test
    public void addBusTimetableNull() {

        line.addBusTimetable(null);
        Assert.assertEquals(0, line.getTimetables().size());


    }

    /**
     * Διαγραφή δρομολογίου από μια γραμμή
     */
    @Test
    public void removeBusTimetable() {
        BusTimetable b1=new BusTimetable();
        line.addBusTimetable(b1);
        Assert.assertEquals(1, line.getTimetables().size());
        line.removeBusTimetable(b1);
        Assert.assertEquals(0, line.getTimetables().size());
        Assert.assertFalse(line.getTimetables().contains(b1));

    }

    /**
     * Διαγραφή κενού δρομολογίου από μια γραμμή
     */
    @Test
    public void removeBusTimetableNull() {
        BusTimetable b1=new BusTimetable();
        line.addBusTimetable(b1);
        Assert.assertEquals(1, line.getTimetables().size());
        line.removeBusTimetable(null);
        Assert.assertEquals(1, line.getTimetables().size());
        Assert.assertTrue(line.getTimetables().contains(b1));

    }



    /**
     * Συσχέτιση γραμμής με στάση
     */

    private void stopBidirectionalAssociationInvariant(Line line) {
        for (Stop stop : line.getStops()) {
            Assert.assertTrue(stop.getLines().contains(line));
        }
    }


}