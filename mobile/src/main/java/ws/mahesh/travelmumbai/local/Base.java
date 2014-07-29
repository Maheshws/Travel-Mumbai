package ws.mahesh.travelmumbai.local;

import android.location.Location;

/**
 * Created by Mahesh on 7/25/2014.
 */
public class Base {

    public static String Destinationvalcode;
    public static String Destinationvaltxt;
    public static String Fast_Trains_checkbox;
    public static String Sourcevalcode;
    public static String Sourcevaltxt;
    public static String deststat;
    public static String detmes;
    public static String info;
    public static double lastKnownLat;
    public static Location lastKnownLocation;
    public static double lastKnownLon;
    public static String latitudesou;
    public static String longitudesou;
    public static String nearbystation;
    public static String route;
    public static String routename;
    public static String startstat;
    public static String textctrip;
    public static int time_in_minutes;
    public static int time_in_minutes_max;
    public static String trainLine = "CR";
    public static String trainkeydd;
    public static String updown;
    public static boolean alltrains;

    static {
        Sourcevaltxt = "temp";
        Sourcevalcode = "temp";
        Destinationvaltxt = "temp";
        Destinationvalcode = "temp";
        Fast_Trains_checkbox = "No";
        trainkeydd = "temp";
        startstat = "temp";
        deststat = "temp";
        detmes = "temp";
        textctrip = "temp";
        latitudesou = "temp";
        longitudesou = "temp";
        nearbystation = "temp";
        route = "1";
        routename = "1";
        updown = "blank";
        info = "callMap";
        trainLine = "CR";
        alltrains = false;
    }

}
