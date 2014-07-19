package ws.mahesh.travelmumbai;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ws.mahesh.travelmumbai.taxi.TaxiCompleteFareList;
import ws.mahesh.travelmumbai.taxi.TaxiFareBase;
import ws.mahesh.travelmumbai.utils.TravelInfo;

/**
 * Created by Mahesh on 7/9/2014.
 */
public class TaxiFragment extends Fragment {
    TextView distance, reading, day_fare, night_fare, info_txt;
    Button calcReading, calcDistance, show_chart;
    EditText meter_reading, distance_km;

    TaxiFareBase taxi = new TaxiFareBase();

    public TaxiFragment() {
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
        ActionBar ab = getActivity().getActionBar();
        ab.setTitle("Travel Mumbai - Taxi");
        distance = (TextView) getActivity().findViewById(R.id.textViewDistance);
        reading = (TextView) getActivity().findViewById(R.id.textViewReading);
        day_fare = (TextView) getActivity().findViewById(R.id.textViewDayFare);
        night_fare = (TextView) getActivity().findViewById(R.id.textViewNightFare);
        info_txt= (TextView) getActivity().findViewById(R.id.textViewInformation);

        meter_reading = (EditText) getActivity().findViewById(R.id.editTextMeter);
        distance_km = (EditText) getActivity().findViewById(R.id.editTextDistance);

        calcDistance = (Button) getActivity().findViewById(R.id.buttoncalcDistance);
        calcReading = (Button) getActivity().findViewById(R.id.buttoncalcReading);
        show_chart = (Button) getActivity().findViewById(R.id.buttonChart);

        info_txt.setText(TravelInfo.TAXI_INFO);

        calcDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (distance_km.getText() != null)
                    try {
                        FareonDistance();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Invalid Input", Toast.LENGTH_LONG).show();
                        Log.e("TAXI", "Invalid input");
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
                        Log.e("TAXI", "Invalid input");
                    }

            }
        });
        show_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new TaxiCompleteFareList())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void FareonDistance() {
        double distanced = Double.parseDouble(distance_km.getText().toString());
        taxi.distanceBased(distanced);
        distance.setText("" + taxi.getDistance());
        reading.setText("" + taxi.getReading());
        day_fare.setText("" + taxi.getFare());
        night_fare.setText("" + taxi.getNightFare());
    }

    private void FareonReading() {
        double meterd = Double.parseDouble(meter_reading.getText().toString());
        taxi.readingBased(meterd);
        distance.setText("" + taxi.getDistance());
        reading.setText("" + taxi.getReading());
        day_fare.setText("" + taxi.getFare());
        night_fare.setText("" + taxi.getNightFare());
    }
}
