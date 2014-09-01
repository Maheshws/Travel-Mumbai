package ws.mahesh.travelmumbai.fragments;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ws.mahesh.travelmumbai.MainActivity;
import ws.mahesh.travelmumbai.R;
import ws.mahesh.travelmumbai.auto.AutoCompleteFareListFragment;
import ws.mahesh.travelmumbai.auto.AutoFareBase;
import ws.mahesh.travelmumbai.utils.TravelInfo;

/**
 * Created by Mahesh on 7/9/2014.
 */
public class AutoFragment extends Fragment {
    TextView distance, reading, day_fare, night_fare, info_txt;
    Button calcReading, calcDistance, show_chart;
    EditText meter_reading, distance_km;


    AutoFareBase auto = new AutoFareBase();


    public AutoFragment() {
        super();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.auto_taxi_fare_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Travel Mumbai - Auto");

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "font/rupee.ttf");

        distance = (TextView) getActivity().findViewById(R.id.textViewDistance);
        reading = (TextView) getActivity().findViewById(R.id.textViewReading);
        day_fare = (TextView) getActivity().findViewById(R.id.textViewDayFare);
        night_fare = (TextView) getActivity().findViewById(R.id.textViewNightFare);
        info_txt = (TextView) getActivity().findViewById(R.id.textViewInformation);

        day_fare.setTypeface(tf);
        night_fare.setTypeface(tf);

        meter_reading = (EditText) getActivity().findViewById(R.id.editTextMeter);
        distance_km = (EditText) getActivity().findViewById(R.id.editTextDistance);

        calcDistance = (Button) getActivity().findViewById(R.id.buttoncalcDistance);
        calcReading = (Button) getActivity().findViewById(R.id.buttoncalcReading);
        show_chart = (Button) getActivity().findViewById(R.id.buttonChart);

        info_txt.setText(TravelInfo.AUTO_INFO);

        calcDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (distance_km.getText() != null)
                    try {
                        FareonDistance();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Invalid Input", Toast.LENGTH_LONG).show();
                        Log.e("AUTO", "Invalid input");
                    }
            }
        });

        calcReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (meter_reading.getText() != null)
                    try {
                        FareonReading();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Invalid Input", Toast.LENGTH_LONG).show();
                        Log.e("AUTO", "Invalid input");
                    }

            }
        });

        show_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new AutoCompleteFareListFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void FareonDistance() {
        double distanced = Double.parseDouble(distance_km.getText().toString());
        auto.distanceBased(distanced);
        distance.setText("" + auto.getDistance());
        reading.setText("" + auto.getReading());
        day_fare.setText("` " + auto.getFare());
        night_fare.setText("` " + auto.getNightFare());
    }

    private void FareonReading() {
        double meterd = Double.parseDouble(meter_reading.getText().toString());
        auto.readingBased(meterd);
        distance.setText("" + auto.getDistance());
        reading.setText("" + auto.getReading());
        day_fare.setText("` " + auto.getFare());
        night_fare.setText("` " + auto.getNightFare());
    }
}
