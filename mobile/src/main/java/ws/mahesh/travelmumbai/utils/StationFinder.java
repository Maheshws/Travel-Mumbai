package ws.mahesh.travelmumbai.utils;

import ws.mahesh.travelmumbai.metro.MetroStations;
import ws.mahesh.travelmumbai.monorail.MonoStations;

/**
 * Created by Mahesh on 7/29/2014.
 */
public class StationFinder {
    public int getNearbyMetroStation(double x1, double y1) {
        int station = 0;
        double distance = 0.0;
        double minDistance = 9999.0;
        double x2, y2;
        for (int i = 0; i < MetroStations.COUNT; i++) {
            x2 = MetroStations.LOCATIONS[i][0];
            y2 = MetroStations.LOCATIONS[i][1];
            distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
            if (distance < minDistance) {
                station = i;
                minDistance = distance;
            }
        }
        return station;
    }

    public int getNearbyMonoStation(double x1, double y1) {
        int station = 0;
        double distance = 0.0;
        double minDistance = 9999.0;
        double x2, y2;
        for (int i = 0; i < MonoStations.COUNT; i++) {
            x2 = MonoStations.LOCATIONS[i][0];
            y2 = MonoStations.LOCATIONS[i][1];
            distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
            if (distance < minDistance) {
                station = i;
                minDistance = distance;
            }
        }
        return station;
    }
}
