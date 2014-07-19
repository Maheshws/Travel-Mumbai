package ws.mahesh.travelmumbai;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import ws.mahesh.travelmumbai.metro.MetroFareBase;
import ws.mahesh.travelmumbai.utils.MyTagHandler;

/**
 * Created by Mahesh on 7/9/2014.
 */
public class MetroFragment extends Fragment {
    TextView tokenfare, cardfare, timing;
    Spinner source, destination;
    Button calcFare;
    MetroFareBase metro;

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
        ActionBar ab = getActivity().getActionBar();
        ab.setTitle("Travel Mumbai - Metro");
        tokenfare = (TextView) getActivity().findViewById(R.id.textViewTokenFare);
        cardfare = (TextView) getActivity().findViewById(R.id.textViewCardFare);
        timing = (TextView) getActivity().findViewById(R.id.textViewTiming);
        source = (Spinner) getActivity().findViewById(R.id.spinnerSource);
        destination = (Spinner) getActivity().findViewById(R.id.spinnerDestination);
        calcFare = (Button) getActivity().findViewById(R.id.buttonCalcFare);

        timing.setText(Html.fromHtml(readFromFile("metro/timing.tm"), null, new MyTagHandler()));

        calcFare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateFare();
            }
        });

    }

    private void calculateFare() {
        metro = new MetroFareBase();
        int src = (int) source.getSelectedItemId();
        src -= 1;
        int dest = (int) destination.getSelectedItemId();
        dest -= 1;
        if (src < 0 || dest < 0 || src == dest) {
            Toast.makeText(getActivity(), getResources().getString(R.string.invalid_option_selected), Toast.LENGTH_LONG).show();
        } else {
            cardfare.setText(getResources().getString(R.string.fare_for_smartcard_user) + "  " + metro.getCardFare(src, dest));
            tokenfare.setText(getResources().getString(R.string.fare_for_token_user) + "  " + metro.getTokenFare(src, dest));
        }

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
}
