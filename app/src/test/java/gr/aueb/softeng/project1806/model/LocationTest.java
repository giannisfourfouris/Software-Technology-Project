package gr.aueb.softeng.project1806.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    private Location loc;


    @Before
    public void setUp() throws Exception {
        loc = new Location(44.92, 66.643);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getLongitude() {
        Assert.assertEquals(44.92, loc.getLongitude(),0);
    }

    @Test
    public void setLongitude() {
        loc.setLongitude(45.00);
        Assert.assertEquals(45.00, loc.getLongitude(),0);
    }

    @Test
    public void getLatitude() {
        Assert.assertEquals(66.643, loc.getLatitude(),0);
    }

    @Test
    public void setLatitude() {
        loc.setLatitude(67.001);
        Assert.assertEquals(67.001, loc.getLatitude(),0);
    }
    /**
     * Υπολογισμός απόστασης μεταξύ δύο τοποθεσιών
     */
    @Test
    public void distanceBetweenLocations() {
        Location loc2 = new Location(45.14,67.11);
        Assert.assertEquals(52809,loc.distanceBetweenLocations(loc2),1);
    }
}