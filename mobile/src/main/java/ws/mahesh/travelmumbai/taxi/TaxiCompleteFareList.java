package ws.mahesh.travelmumbai.taxi;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ws.mahesh.travelmumbai.MainActivity;
import ws.mahesh.travelmumbai.R;

/**
 * Created by Mahesh on 7/15/2014.
 */
public class TaxiCompleteFareList extends Fragment {

    TaxiFareBase abc = new TaxiFareBase();
    private List<TaxiListItem> auto = new ArrayList<TaxiListItem>();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.auto_taxi_listview_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Travel Mumbai - Taxi Fare List");
        populate();

    }

    private void populate() {
        for (double i = 1.0; i < 10.0; i = (i + 0.1)) {
            abc.readingBased(i);
            String tempd = "" + abc.getDistance();
            if (tempd.length() < 4)
                tempd = tempd + "0";
            auto.add(new TaxiListItem("" + abc.getReading(), "" + tempd, "" + abc.getFare(), "" + abc.getNightFare()));
        }
        TaxiListAdapter adapter = new TaxiListAdapter(getActivity(), R.layout.auto_taxi_item, auto);
        ListView list = (ListView) getActivity().findViewById(R.id.listView);
        list.setAdapter(adapter);
    }
}
