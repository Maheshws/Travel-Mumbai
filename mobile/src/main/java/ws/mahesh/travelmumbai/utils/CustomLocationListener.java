package ws.mahesh.travelmumbai.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import ws.mahesh.travelmumbai.local.Base;

/**
 * Created by Mahesh on 8/13/2014.
 */
public class CustomLocationListener implements LocationListener {

    @Override
    public void onLocationChanged(Location loc) {
        Base.lastKnownLon= loc.getLongitude();
        Base.lastKnownLat= loc.getLatitude();
        Base.lastKnownLocation=loc;

        Log.i("Location","Location changed");
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

}
