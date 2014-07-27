package ws.mahesh.travelmumbai.local;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private List<LocalItem> local=new ArrayList<LocalItem>();

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
        info= (TextView) getActivity().findViewById(R.id.textViewInfo);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Travel Mumbai -> "+Base.Sourcevaltxt+"-"+Base.Destinationvaltxt);
        dattabase = new DatabaseAdapter(getActivity());
        info.setText("Trains from "+Base.Sourcevaltxt+" to "+Base.Destinationvaltxt+" in next 2 hrs");
       populate();
    }

    private void populate() {
        try {
            dattabase.openDataBase();
            dattabase.createTempTimetableTable();
            local = dattabase.getTimeTable();
            if(local==null) {
                Toast.makeText(getActivity(),"No Trains Found",Toast.LENGTH_LONG).show();
                info.setText("No Direct Train available on Selected Route");
                dattabase.close();
                return;
            }
            dattabase.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LocalsAdapter adapter = new LocalsAdapter(getActivity(), R.layout.local_listview_item, local);
        ListView list = (ListView) getActivity().findViewById(R.id.listViewRoute);
        list.setAdapter(adapter);
    }
}
