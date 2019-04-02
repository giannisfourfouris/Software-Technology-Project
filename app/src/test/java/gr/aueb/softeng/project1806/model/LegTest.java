package gr.aueb.softeng.project1806.model;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LegTest {
    Leg leg, leg1;
    Stop stop1 = new Stop();
    Stop stop2 = new Stop();

    @Before
    public void setUp() throws Exception {
        leg = new Leg(100, stop1, stop2);
        leg1 = new Leg();
    }

    @Test
    public void getLegLength() {
        Assert.assertEquals(100, leg.getLegLength());
    }

    @Test
    public void setLegLength() {
        leg1.setLegLength(200);
        Assert.assertEquals(200, leg1.getLegLength());
    }

    @Test
    public void getStartStop() {
        Assert.assertSame(stop1, leg.getStartStop());
    }

    @Test
    public void setStartStop() {
        Stop stop3 = new Stop();
        leg.setStartStop(stop3);
        Assert.assertSame(stop3, leg.getStartStop());
    }

    @Test
    public void getEndStop() {
        Assert.assertSame(stop2, leg.getEndStop());
    }

    @Test
    public void setEndStop() {
        Stop stop4 = new Stop();
        leg.setEndStop(stop4);
        Assert.assertSame(stop4, leg.getEndStop());
    }

    /**
     * Προσθήκη δρομολογίου σε σκέλος
     */
    @Test
    public void addBusTimetable() {
        BusTimetable b1 = new BusTimetable();
        leg.addBusTimetable(b1);
        Assert.assertEquals(1, leg.getBusTimetableSet().size());
        Assert.assertTrue(leg.getBusTimetableSet().contains(b1));
        bustimetableBidirectionalAssociationInvariant(leg);
    }
    /**
     * Προσθήκη κενού δρομολογίου σε σκέλος
     */
    @Test
    public void addBusTimetableNull() {

        leg.addBusTimetable(null);
        Assert.assertEquals(0, leg.getBusTimetableSet().size());
        bustimetableBidirectionalAssociationInvariant(leg);

    }

    /**
     * Διαγραφή δρομολογίου από σκέλος
     */
    @Test
    public void removeBusTimetable() {
        BusTimetable b2 = new BusTimetable();
        leg.addBusTimetable(b2);
        bustimetableBidirectionalAssociationInvariant(leg);
        leg.removeBusTimetable(b2);
        Assert.assertEquals(0, leg.getBusTimetableSet().size());
        bustimetableBidirectionalAssociationInvariant(leg);


    }
    /**
     * Διαγραφή κενού δρομολογίου από σκέλος
     */
    @Test
    public void removeBusTimetableNull() {
        BusTimetable b2 = new BusTimetable();
        leg.addBusTimetable(b2);
        leg.removeBusTimetable(null);
        Assert.assertEquals(1, leg.getBusTimetableSet().size());
        bustimetableBidirectionalAssociationInvariant(leg);


    }
    /**
     * Έλεγχος αμφίδρομης συσχέτισης δρομολογίου και σκέλους
     */
    private void bustimetableBidirectionalAssociationInvariant(Leg leg) {
        for (BusTimetable b1 : leg.getBusTimetableSet()) {
            Assert.assertTrue(b1.getLegs().contains(leg));
        }
    }


}