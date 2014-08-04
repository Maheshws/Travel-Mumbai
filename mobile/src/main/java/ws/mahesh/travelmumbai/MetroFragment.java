package ws.mahesh.travelmumbai;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.location.LocationManager;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import ws.mahesh.travelmumbai.local.Base;
import ws.mahesh.travelmumbai.metro.MetroFareBase;
import ws.mahesh.travelmumbai.metro.MetroListAdapter;
import ws.mahesh.travelmumbai.metro.MetroListItem;
import ws.mahesh.travelmumbai.metro.MetroStations;
import ws.mahesh.travelmumbai.utils.MyTagHandler;
import ws.mahesh.travelmumbai.utils.StationFinder;

/**
 * Created by Mahesh on 7/9/2014.
 */
public class MetroFragment extends Fragment {
    Spinner source;
    Button moreInfo;
    ImageButton getLoc;
    MetroFareBase metro=new MetroFareBase();
    private List<MetroListItem> metroItem=new ArrayList<MetroListItem>();

    public MetroFragment() {
        super();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.metro_fare_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Travel Mumbai - Metro");

        final StationFinder sf=new StationFinder();

        getLocation();

        source = (Spinner) getActivity().findViewById(R.id.spinnerSource);

        moreInfo = (Button) getActivity().findViewById(R.id.buttonmoreInfo);

        getLoc= (ImageButton) getActivity().findViewById(R.id.imageButtonLoc);


        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Metro Information");
                alertDialogBuilder.setMessage(Html.fromHtml(readFromFile("metro/timing.tm"), null, new MyTagHandler()));
                alertDialogBuilder.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        source.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                new Thread(new Runnable() {
                    public void run() {
                        setValues();
                    }
                }).start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        getLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
                source.setSelection(sf.getNearbyMetroStation(Base.lastKnownLat,Base.lastKnownLon)+1);
            }
        });

        source.setSelection(sf.getNearbyMetroStation(Base.lastKnownLat,Base.lastKnownLon)+1);

    }

    private void setValues() {
        int current=source.getSelectedItemPosition();
        if(current>0)
            calculateFare(current-1);
    }

    private void calculateFare(int src) {
        metroItem.clear();
        metroItem.add(new MetroListItem("Destination","Token Fare","Card Fare"));
        for(int i=0;i< MetroStations.COUNT;i++) {
            metroItem.add(new MetroListItem(MetroStations.STATIONS[i], "" + metro.getTokenFare(src, i), "" + metro.getCardFare(src, i)));
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MetroListAdapter adapter=new MetroListAdapter(getActivity(),R.layout.metro_mono_list_item,metroItem);
                ListView list = (ListView) getActivity().findViewById(R.id.listView1);
                list.setAdapter(adapter);
            }
        });

    }

    private String readFromFile(String fname) {
        String str = "";
        try {
            InputStream in = getActivity().getAssets().open(fname);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            str = new String(buffer);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    private void getLocation() {
        int i = 0;
        LocationManager localLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        do {
            if (i == 10)
                break;
            Base.lastKnownLocation = localLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            i++;
        } while (Base.lastKnownLocation == null);
        Base.lastKnownLat = Base.lastKnownLocation.getLatitude();
        Base.lastKnownLon = Base.lastKnownLocation.getLongitude();

        Base.lastKnownLocation = localLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (Base.lastKnownLocation != null) {
            Base.lastKnownLat = Base.lastKnownLocation.getLatitude();
            Base.lastKnownLon = Base.lastKnownLocation.getLongitude();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getLocation();
    }
}
