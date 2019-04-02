package gr.aueb.softeng.project1806.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class BusTimetableTest {

    private BusTimetable btt;
    private BusTimetable btt1;
    private Time startTimeFromStart = new Time(14,55,0);
    private int day;
    private Set<Leg> legs = new HashSet<Leg>();


    @Before
    public void setUp() throws Exception {
        btt = new BusTimetable();
        btt1 = new BusTimetable(startTimeFromStart, "Sunday");


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getStartTimeFromStart() {
        Assert.assertEquals(new Time(14,55, 0), btt1.getStartTimeFromStart());
    }

    @Test
    public void setStartTimeFromStart() {
        Time t1 = new Time(16,07,00);
        btt.setStartTimeFromStart(t1);
        Assert.assertEquals(new Time(16,07,00),btt.getStartTimeFromStart());
    }

    @Test
    public void getDay() {
        Assert.assertEquals("Sunday", btt1.getDay());
    }

    @Test
    public void setDay() {
        btt.setDay("Thursday");
        Assert.assertEquals("Thursday",btt.getDay());
    }

    /**
     * Αποθήκευση κενού σκέλους
     */
    @Test
    public void addNullForLeg() {
        BusTimetable btt = new BusTimetable();
        btt.addLeg(null);
        Assert.assertEquals(0,btt.getLegs().size());
        LegBidirectionalAssociationInvariant(btt);
    }

    /**
     * Αποθήκευση σκέλους
     */
    @Test
    public void addLeg() {
        BusTimetable btt = new BusTimetable();
        Leg leg = new Leg();
        leg.addBusTimetable(btt);
        Assert.assertEquals(1, btt.getLegs().size());
        Assert.assertTrue(btt.getLegs().contains(leg));
        LegBidirectionalAssociationInvariant(btt);
    }

    /**
     * Διαγραφή κενού σκέλους
     */
    @Test
    public void removeNullForLeg() {
        BusTimetable btt = new BusTimetable();
        Leg leg = new Leg();
        leg.addBusTimetable(btt);
        btt.removeLeg(null);
        Assert.assertEquals(1, btt.getLegs().size());
        LegBidirectionalAssociationInvariant(btt);
    }

    /**
     * Διαγραφή σκέλους
     */
    @Test
    public void removeLeg() {
        BusTimetable btt = new BusTimetable();
        Leg leg = new Leg();
        leg.addBusTimetable(btt);
        LegBidirectionalAssociationInvariant(btt);
        btt.removeLeg(leg);
        Assert.assertEquals(0, btt.getLegs().size());
        LegBidirectionalAssociationInvariant(btt);

    }

    /**
     * Έλεγχος αμφίδρομης συσχέτισης σκέλους και δρομολογίου
     */
    private void LegBidirectionalAssociationInvariant(BusTimetable btt) {
        for(Leg leg : btt.getLegs()) {
            Assert.assertTrue(leg.getBusTimetableSet().contains(btt));
        }
    }
}