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
    public static final int FARE[][]={{50,5,340,100,915,270,1820,540,3640,1080},{50,5,340,100,915,270,1820,540,3640,1080},{70,10,485,130,1325,360,2645,720,5285,1440},{105,10,560,130,1535,360,3065,720,6125,1440},{105,10,650,215,1770,590,3540,1180,7075,2360},{135,10,660,215,1800,590,3595,1180,7190,2360},{140,15,745,215,2025,590,4045,1180,8085,2360},{145,15,815,215,2220,590,4435,1180,8870,2360},{145,15,900,215,2445,590,4885,1180,9765,2360},{160,15,975,300,2655,820,5305,1640,10605,3275},{165,15,1090,315,2970,865,5940,1730,11875,3455},{170,20,1170,315,3180,865,6360,1730,12715,3455},{170,20,1180,315,3210,865,6415,1730,12825,3455},{180,20,1260,315,3435,865,6860,1730,13725,3455},{190,20,1345,400,3655,1095,7310,2190,14620,4375},{195,20,1425,400,3865,1095,7730,2190,15460,4375},{205,25,1505,400,4090,1095,8180,2190,16355,4375},{210,25,1585,400,4300,1095,8600,2190,17195,4375},{210,25,1595,400,4330,1095,8655,2190,17305,4375},{220,30,1670,400,4540,1095,9075,2190,18145,4375},{225,30,1790,500,4870,1370,9735,2735,19470,5475},{235,30,1795,500,4885,1370,9765,2735,19530,5475},{235,30,1880,500,5110,1370,10215,2735,20425,5475},{245,30,1955,500,5320,1370,10635,2735,21265,5475},{245,35,1970,500,5360,1370,10715,2735,21430,5475},{250,35,2045,500,5555,1370,11110,2735,22215,5475},{265,35,2060,500,5600,1370,11195,2735,22385,5475},{265,35,2140,585,5810,1600,11615,3195,23225,6390},{275,35,2220,585,6030,1600,12060,3195,24120,6390},{280,35,2225,585,6045,1600,12090,3195,24175,6390}};

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
