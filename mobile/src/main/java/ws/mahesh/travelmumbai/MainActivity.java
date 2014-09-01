package ws.mahesh.travelmumbai;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

import ws.mahesh.travelmumbai.fragments.AutoFragment;
import ws.mahesh.travelmumbai.fragments.LocalFragment;
import ws.mahesh.travelmumbai.fragments.MetroFragment;
import ws.mahesh.travelmumbai.fragments.MonorailFragment;
import ws.mahesh.travelmumbai.fragments.TaxiFragment;
import ws.mahesh.travelmumbai.local.Base;

public class MainActivity extends ActionBarActivity implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener, LocationListener {

    LocationRequest locationRequest;
    LocationClient locationClient;
    boolean locationEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.start(this);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        locationClient = new LocationClient(this, this, this);
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(1000);

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationEnabled = false;
        } else locationEnabled = true;
        ActionBar actionBar = getSupportActionBar();
        actionBar.show();
        actionBar.setTitle("Travel Mumbai");
    }

    public void setActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.show();
        actionBar.setTitle(title);
    }

    @Override
    public void onStart() {
        locationClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        locationClient.removeLocationUpdates(this);
        locationClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location location = locationClient.getLastLocation();
        if (location != null) {
            Base.lastKnownLon = location.getLongitude();
            Base.lastKnownLat = location.getLatitude();
            Base.lastKnownLocation = location;
        } else if (location == null && locationEnabled) {
            locationClient.requestLocationUpdates(locationRequest, this);
        }
    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            Base.lastKnownLon = location.getLongitude();
            Base.lastKnownLat = location.getLatitude();
            Base.lastKnownLocation = location;
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            ((MainActivity) getActivity()).getSupportActionBar().hide();
            ImageButton metro = (ImageButton) getActivity().findViewById(R.id.imageButtonMetro);
            ImageButton auto = (ImageButton) getActivity().findViewById(R.id.imageButtonAuto);
            ImageButton taxi = (ImageButton) getActivity().findViewById(R.id.imageButtonTaxi);
            ImageButton mono = (ImageButton) getActivity().findViewById(R.id.imageButtonMono);
            ImageButton prefs = (ImageButton) getActivity().findViewById(R.id.imageButtonPreferences);
            ImageButton local = (ImageButton) getActivity().findViewById(R.id.imageButtonLocal);

            local.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).getSupportActionBar().show();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, new LocalFragment())
                            .addToBackStack(null)
                            .commit();
                }
            });

            metro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).getSupportActionBar().show();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, new MetroFragment())
                            .addToBackStack(null)
                            .commit();
                }
            });

            auto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).getSupportActionBar().show();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, new AutoFragment())
                            .addToBackStack(null)
                            .commit();
                }
            });

            taxi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).getSupportActionBar().show();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, new TaxiFragment())
                            .addToBackStack(null)
                            .commit();
                }
            });

            mono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).getSupportActionBar().show();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, new MonorailFragment())
                            .addToBackStack(null)
                            .commit();
                }
            });

            prefs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), PreferencesActivity.class));
                }
            });
        }

    }
}
