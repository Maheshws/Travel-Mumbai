package ws.mahesh.travelmumbai.local;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import ws.mahesh.travelmumbai.MainActivity;
import ws.mahesh.travelmumbai.R;

/**
 * Created by Mahesh on 7/26/2014.
 */
public class LocalSelectorFragment extends Fragment {

    private DatabaseAdapter dattabase;
    ArrayAdapter<String> destinationadapter;
    ArrayAdapter<String> sourceadapter;

    Spinner src,dest;
    ImageButton locsrc,locdest;
    Button findTrains;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.local_spinner_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        src= (Spinner) getActivity().findViewById(R.id.spinnerSource);
        dest= (Spinner) getActivity().findViewById(R.id.spinnerDestination);
        locsrc= (ImageButton) getActivity().findViewById(R.id.imageButtonLocsrc);
        locdest= (ImageButton) getActivity().findViewById(R.id.imageButtonLocdest);
        findTrains= (Button) getActivity().findViewById(R.id.buttonFindTrains);


        dattabase = new DatabaseAdapter(getActivity());

        try {
            dattabase.openDataBase();
            String[] arrayOfString = dattabase.getAllStations();
            sourceadapter=new ArrayAdapter<String>(getActivity(),R.layout.local_spinner_item,arrayOfString);
            src.setAdapter(sourceadapter);
            destinationadapter=new ArrayAdapter<String>(getActivity(),R.layout.local_spinner_item,arrayOfString);
            dest.setAdapter(destinationadapter);
            dattabase.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        locsrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dattabase.openDataBase();
                    dattabase.searchStationNearby();
                    dattabase.close();
                    src.setSelection(((ArrayAdapter) src.getAdapter()).getPosition(Base.nearbystation));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        locdest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dattabase.openDataBase();
                    dattabase.searchStationNearby();
                    dattabase.close();
                    dest.setSelection(((ArrayAdapter) dest.getAdapter()).getPosition(Base.nearbystation));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        findTrains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateQuery();
            }
        });

    }

    private void validateQuery() {
        Time now = new Time();
        now.setToNow();
        Base.Sourcevaltxt = src.getSelectedItem().toString();
        Base.Destinationvaltxt = dest.getSelectedItem().toString();
        Base.time_in_minutes = now.minute + now.hour * 60;
        Base.time_in_minutes_max = 120 + Base.time_in_minutes;

        if (Base.Sourcevaltxt.equals(Base.Destinationvaltxt))
        {
            Toast.makeText(getActivity(), "Select different source/destination stations", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getLocation();
    }

    private void getLocation() {
        int i=0;
        LocationManager localLocationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        Base.lastKnownLocation = localLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (Base.lastKnownLocation != null)
        {
            Base.lastKnownLat = Base.lastKnownLocation.getLatitude();
            Base.lastKnownLon = Base.lastKnownLocation.getLongitude();
        }
        do
        {
            if(i==10)
                return;
            Base.lastKnownLocation = localLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            i++;
        } while (Base.lastKnownLocation == null);
        Base.lastKnownLat = Base.lastKnownLocation.getLatitude();
        Base.lastKnownLon = Base.lastKnownLocation.getLongitude();
    }
}
