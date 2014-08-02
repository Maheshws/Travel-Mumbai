package ws.mahesh.travelmumbai.local;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ws.mahesh.travelmumbai.MainActivity;
import ws.mahesh.travelmumbai.R;

/**
 * Created by Mahesh on 8/2/2014.
 */
public class LocalFareFragment extends Fragment {
    private TextView JC1,JC2,MC1,MC2,QC1,QC2,HC1,HC2,YC1,YC2,infoText;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.local_fare_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Travel Mumbai - Local Fare");
        infoText= (TextView) getActivity().findViewById(R.id.textViewStationsInfo);
        JC1= (TextView) getActivity().findViewById(R.id.textViewFareJC1);
        JC2= (TextView) getActivity().findViewById(R.id.textViewFareJC2);
        MC1= (TextView) getActivity().findViewById(R.id.textViewFareMC1);
        MC2= (TextView) getActivity().findViewById(R.id.textViewFareMC2);
        QC1= (TextView) getActivity().findViewById(R.id.textViewFareQC1);
        QC2= (TextView) getActivity().findViewById(R.id.textViewFareQC2);
        HC1= (TextView) getActivity().findViewById(R.id.textViewFareHC1);
        HC2= (TextView) getActivity().findViewById(R.id.textViewFareHC2);
        YC1= (TextView) getActivity().findViewById(R.id.textViewFareYC1);
        YC2= (TextView) getActivity().findViewById(R.id.textViewFareYC2);

        infoText.setText("Fare for "+Base.Sourcevaltxt+" to "+Base.Destinationvaltxt);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "font/rupee.ttf");

        JC1.setTypeface(tf);
        JC2.setTypeface(tf);
        MC1.setTypeface(tf);
        MC2.setTypeface(tf);
        QC1.setTypeface(tf);
        QC2.setTypeface(tf);
        HC1.setTypeface(tf);
        HC2.setTypeface(tf);
        YC1.setTypeface(tf);
        YC2.setTypeface(tf);

        int id=(int)Base.distancebtn/5;

        JC1.setText("` "+Base.FARE[id][0]);
        JC2.setText("` "+Base.FARE[id][1]);
        MC1.setText("` "+Base.FARE[id][2]);
        MC2.setText("` "+Base.FARE[id][3]);
        QC1.setText("` "+Base.FARE[id][4]);
        QC2.setText("` "+Base.FARE[id][5]);
        HC1.setText("` "+Base.FARE[id][6]);
        HC2.setText("` "+Base.FARE[id][7]);
        YC1.setText("` "+Base.FARE[id][8]);
        YC2.setText("` "+Base.FARE[id][9]);

    }
}
