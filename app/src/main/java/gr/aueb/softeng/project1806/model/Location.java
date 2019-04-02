package gr.aueb.softeng.project1806.model;


import java.io.Serializable;

/**
 * Μία τοποθεσία και οι κατάλληλες μέθοδοι.
 */
public class Location implements Serializable{

    double longitude, latitude;


    /**
     * Κατασκευαστής που δέχεται ως παραμέτρους
     * το γεωγραφικό μήκος και πλάτος της τοποθεσίας.
     *
     * @param longitude γεωγραφικό μήκος.
     * @param latitude  γεωγραφικό πλάτος.
     */
    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }


    /**
     * Επιστρέφει το γεωγραφικό μήκος της τοποθεσίας.
     *
     * @return το γεωγραφικό μήκος.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Θέτει το γεωγραφικό μήκος της τοποθεσίας.
     *
     * @param longitude το γεωγραφικό μήκος.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Επιστρέφει το γεωγραφικό πλάτος της τοποθεσίας.
     *
     * @return το γεωγραφικό πλάτος.
     */
    public double getLatitude() {
        return latitude;
    }


    /**
     * Θέτει το γεωγραφικό πλάτος της τοποθεσίας.
     *
     * @param latitude το γεωγραφικό πλάτος.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    /**
     * Επιστρέφει την απόσταση(σε μέτρα) μεταξύ
     * δύο τοποθεσιών, δεχόμενη ως παράμετρο μια τοποθεσία.
     *
     * @param point η μία εκ των δύο τοποθεσιών.
     * @return την απόσταση μεταξύ των τοποθεσιών.
     */
    public double distanceBetweenLocations(Location point) {

        double lat1, lon1, lat2, lon2;

        lat1 = this.getLatitude();
        lon1 = this.getLongitude();
        lat2 = point.getLatitude();
        lon2 = point.getLongitude();


        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.round(distance);

        return distance;
    }
}
