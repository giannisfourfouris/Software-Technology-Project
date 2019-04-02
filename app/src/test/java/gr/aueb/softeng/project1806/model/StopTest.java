package gr.aueb.softeng.project1806.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class StopTest {

    private Stop stop, stopEmpty;
    private Location loc1;
    private Line line, lineEmpty;
    private Set<Line> lines = new HashSet<Line>();


    @Before
    public void setUp() throws Exception {
        loc1 = new Location(43.2, 124.4);
        stop = new Stop(4387, "stashtest", loc1);
        line = new Line("a67", "a67_kerameikos-sounio");
        stopEmpty = new Stop();
        lineEmpty = new Line();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getStopName() {
        Assert.assertEquals("stashtest", stop.getStopName());
    }

    @Test
    public void setStopName() {
        stop.setStopName("allagmeno");
        Assert.assertEquals("allagmeno", stop.getStopName());
    }

    @Test
    public void getStopID() {
        Assert.assertEquals(4387, stop.getStopID());
    }

    @Test
    public void setStopID() {
        stop.setStopID(4521);
        Assert.assertEquals(4521, stop.getStopID());
    }


    @Test
    public void getLocation() {
        Assert.assertEquals(loc1.latitude, stop.getLocation().latitude, 0);
        Assert.assertEquals(loc1.longitude, stop.getLocation().longitude, 0);
    }

    @Test
    public void setLocation() {
        stop.setLocation(new Location(32.7, 14.67));
        Location loc2 = new Location(32.7, 14.67);
        Assert.assertEquals(loc2.longitude, stop.getLocation().longitude, 0);
        Assert.assertEquals(loc2.latitude, stop.getLocation().latitude, 0);
    }

    /**
     * Προσθήκη γραμμής σε στάση
     */
    @Test
    public void addLine() {
        stop.addLine(line);
        Assert.assertEquals(1, stop.getLines().size());
        Assert.assertTrue(stop.getLines().contains(line));
        lineBidirectionalAssociationInvariant(stop);
    }

    /**
     * Προσθήκη κενής γραμμής σε στάση
     */
    @Test
    public void addLineNull() {
        stop.addLine(null);
        Assert.assertEquals(0, stop.getLines().size());
        lineBidirectionalAssociationInvariant(stop);
    }
    /**
     * Διαγραφή γραμμής από στάση
     */
    @Test
    public void removeLine() {
        stop.addLine(line);
        lineBidirectionalAssociationInvariant(stop);
        Assert.assertEquals(1, stop.getLines().size());
        stop.removeLine(line);
        Assert.assertEquals(0, stop.getLines().size());
        lineBidirectionalAssociationInvariant(stop);
    }
    /**
     * Διαγραφή κενής γραμμής από στάση
     */
    @Test
    public void removeLineNull() {
        Stop stopn = new Stop();
        Line l=new Line();
        stopn.addLine(l);
        stopn.removeLine(null);
        Assert.assertEquals(1, stopn.getLines().size());
        lineBidirectionalAssociationInvariant(stopn);

    }

    /**
     * Συσχέτιση στάση με γραμμής
     */
    private void lineBidirectionalAssociationInvariant(Stop stop) {
        for (Line line : stop.getLines()) {
            Assert.assertTrue(line.getStops().contains(stop));
        }
    }


}
