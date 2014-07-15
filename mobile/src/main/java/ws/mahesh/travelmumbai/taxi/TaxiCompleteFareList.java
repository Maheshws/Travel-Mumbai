package ws.mahesh.travelmumbai.taxi;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ws.mahesh.travelmumbai.R;
import ws.mahesh.travelmumbai.auto.AutoFareBase;

/**
 * Created by Mahesh on 7/15/2014.
 */
public class TaxiCompleteFareList extends Fragment {

    private List<TaxiListItem> auto=new ArrayList<TaxiListItem>();
    TaxiFareBase abc=new TaxiFareBase();

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
        populate();

    }

    private void populate() {
        for(double i=1.0;i<10.0;i=(i+0.1)) {
            abc.readingBased(i);
                auto.add(new TaxiListItem("" + abc.getReading(), "" + abc.getDistance(), "" + abc.getFare(), "" + abc.getNightFare()));
        }
        TaxiListAdapter adapter=new TaxiListAdapter(getActivity(),R.layout.auto_taxi_item,auto);
        ListView list= (ListView) getActivity().findViewById(R.id.listView);
        list.setAdapter(adapter);
    }
}
