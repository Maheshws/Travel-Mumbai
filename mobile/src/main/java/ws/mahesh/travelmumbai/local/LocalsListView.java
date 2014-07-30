package ws.mahesh.travelmumbai.local;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ws.mahesh.travelmumbai.MainActivity;
import ws.mahesh.travelmumbai.R;

/**
 * Created by Mahesh on 7/27/2014.
 */
public class LocalsListView extends Fragment {
    TextView info;
    private DatabaseAdapter dattabase;
    private List<LocalsItem> local = new ArrayList<LocalsItem>();
    private ProgressDialog progressBar;
    private int posCount = 0;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.local_trains_list_view_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        info = (TextView) getActivity().findViewById(R.id.textViewInfo);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Local -> " + Base.Sourcevaltxt + "-" + Base.Destinationvaltxt);
        dattabase = new DatabaseAdapter(getActivity());
        if (Base.alltrains)
            info.setText("Trains from " + Base.Sourcevaltxt + " to " + Base.Destinationvaltxt);
        else
            info.setText("Trains from " + Base.Sourcevaltxt + " to " + Base.Destinationvaltxt + " in next 2 hrs");

        progressBar = ProgressDialog.show(getActivity(), "Please Wait", "Loading...");
        new Thread(new Runnable() {
            public void run() {
                populate();
            }
        }).start();


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void populate() {

        try {
            dattabase.openDataBase();
            if (Base.alltrains)
                dattabase.createTempTimetableTableAllTrains();
            else
                dattabase.createTempTimetableTable();
            local = dattabase.getTimeTable();
            posCount = dattabase.getPosCount();
            if (local == null) {
                Toast.makeText(getActivity(), "No Trains Found", Toast.LENGTH_LONG).show();
                info.setText("No Direct Train available on selected route");
                dattabase.close();
                return;
            }
            dattabase.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LocalsAdapter adapter = new LocalsAdapter(getActivity(), R.layout.local_listview_item, local);
                ListView list = (ListView) getActivity().findViewById(R.id.listViewRoute);
                list.setAdapter(adapter);
                list.setSelection(posCount);
                if (list.getCount() < 1)
                    info.setText("No Direct Train available on selected route");
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        LocalsItem current=local.get(i);
                        Base.trainkeydd=current.getTRAIN_ID();
                        Base.detmes=(current.getSOURCE()+" - "+current.getDESTINATION()+" "+current.getTYPE()+" Local\nLeaving "+Base.Sourcevaltxt+" at "+current.getLEAVES_AT()).toUpperCase();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.container, new LocalViewListView())
                                .addToBackStack(null)
                                .commit();
                    }
                });
            }
        });

    }
}
