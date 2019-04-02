package gr.aueb.softeng.project1806.dao;


import java.sql.Time;
import java.util.Calendar;

import gr.aueb.softeng.project1806.model.BusTimetable;
import gr.aueb.softeng.project1806.model.Leg;
import gr.aueb.softeng.project1806.model.Line;
import gr.aueb.softeng.project1806.model.Location;
import gr.aueb.softeng.project1806.model.Stop;

/**
 * Κλάση που αναλαμβάνει να αρχικοποιήσει δεδομένα της βάσης δεδομένων
 * για να μπορούν να γίνουν οι απαραίτητες λειτουργίες του λογισμικού και
 * οι απαραίτητοι έλεγχοι
 */


public abstract class Initializer {

    /**
     * Μέθοδος για διαγραφή όλων των δεδομένων απο τη βάση
     */
    protected abstract void eraseData();

    public void prepareData() {

        //Διαγράφουμε όλα τα δεδομένα της βάσης πριν γίνει η εισαγωγή νέων
        eraseData();

        LocationDAO locationDAO = getLocationDAO();

        Location loc1 = new Location(38.055874, 23.760772); //panagiotis
        Location loc2 = new Location(38.026500, 23.787736); //zisis
        Location loc3 = new Location(37.941309, 23.754832); //giannis
        Location loc4 = new Location(37.973571, 23.761745);
        Location loc5 = new Location(37.942070, 23.751098); //giannis
        Location loc6 = new Location(37.997609, 23.739466); //konta troias
        Location loc7 = new Location(38.055340, 23.764482); //panagiotis
        Location loc8 = new Location(37.995759, 23.736224); //troias
        Location loc9 = new Location(37.994205, 23.732004); //kentriko ktirio
        Location loc10 = new Location(38.026605, 23.787613); //zisis

        locationDAO.add(loc1);
        locationDAO.add(loc2);
        locationDAO.add(loc3);
        locationDAO.add(loc4);
        locationDAO.add(loc5);
        locationDAO.add(loc6);
        locationDAO.add(loc7);
        locationDAO.add(loc8);
        locationDAO.add(loc9);
        locationDAO.add(loc10);


        StopDAO stopDAO = getStopDAO();

        Stop stop1 = new Stop(1, "1η Στάση", loc1);
        Stop stop2 = new Stop(2, "2η Στάση", loc2);
        Stop stop3 = new Stop(3, "3η Στάση", loc3);
        Stop stop4 = new Stop(4, "4η Στάση", loc4);
        Stop stop5 = new Stop(5, "5η Στάση", loc5);
        Stop stop6 = new Stop(6, "6η Στάση", loc6);
        Stop stop7 = new Stop(7, "7η Στάση", loc7);
        Stop stop8 = new Stop(8, "8η Στάση", loc8);
        Stop stop9 = new Stop(9, "9η Στάση", loc9);
        Stop stop10 = new Stop(10, "10η Στάση", loc10);

        LegDAO legDAO = getLegDAO();

        Leg leg1 = new Leg(200, stop1, stop2);
        Leg leg2 = new Leg(150, stop2, stop3);
        Leg leg3 = new Leg(300, stop3, stop4);
        Leg leg4 = new Leg(100, stop4, stop5);
        Leg leg5 = new Leg(250, stop5, stop6);
        Leg leg6 = new Leg(220, stop6, stop7);
        Leg leg7 = new Leg(170, stop7, stop8);
        Leg leg8 = new Leg(350, stop8, stop9);
        Leg leg9 = new Leg(190, stop9, stop10);

        legDAO.add(leg1);
        legDAO.add(leg2);
        legDAO.add(leg3);
        legDAO.add(leg4);
        legDAO.add(leg5);
        legDAO.add(leg6);
        legDAO.add(leg7);
        legDAO.add(leg8);
        legDAO.add(leg9);


        BusTimetableDAO busTimetableDAO = getBusTimetableDAO();

        BusTimetable btt1 = new BusTimetable(new Time(05, 20, 00), "Δευτέρα");
        BusTimetable btt2 = new BusTimetable(new Time(05, 00, 00), "Τρίτη");
        BusTimetable btt3 = new BusTimetable(new Time(05, 38, 00), "Κάθε μέρα");

        busTimetableDAO.add(btt1);
        busTimetableDAO.add(btt2);

        LineDAO lineDAO = getLineDAO();

        Line line1 = new Line("Πράσινη", "Κηφισιά-Πειραιάς");
        Line line2 = new Line("Κόκκινη", "Ανθούπολη-Ελληνικό");


        line1.addStop(stop1);
        stop1.addLine(line1);

        line1.addStop(stop3);
        stop3.addLine(line1);

        line1.addStop(stop5);
        stop5.addLine(line1);

        line1.addStop(stop8);
        stop8.addLine(line1);

        line1.addStop(stop7);
        stop7.addLine(line1);



        line2.addStop(stop2);
        stop2.addLine(line2);

        line2.addStop(stop4);
        stop4.addLine(line2);

        line2.addStop(stop6);
        stop6.addLine(line2);

        line2.addStop(stop9);
        stop9.addLine(line2);

        line2.addStop(stop10);
        stop10.addLine(line2);


        line1.addBusTimetable(btt1);
        line2.addBusTimetable(btt2);
        line2.addBusTimetable(btt3);

        lineDAO.add(line1);
        lineDAO.add(line2);




        stopDAO.add(stop1);
        stopDAO.add(stop2);
        stopDAO.add(stop3);
        stopDAO.add(stop4);
        stopDAO.add(stop5);
        stopDAO.add(stop6);
        stopDAO.add(stop7);
        stopDAO.add(stop8);
        stopDAO.add(stop9);
        stopDAO.add(stop10);



    }


    protected abstract BusTimetableDAO getBusTimetableDAO();

    protected abstract LineDAO getLineDAO();

    protected abstract StopDAO getStopDAO();

    protected abstract LegDAO getLegDAO();

    protected abstract LocationDAO getLocationDAO();


}
