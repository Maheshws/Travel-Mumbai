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
 * Created by Mahesh on 7/30/2014.
 */
public class LocalViewListView extends Fragment {
    TextView info;
    private DatabaseAdapter dattabase;
    private List<LocalViewItem> local = new ArrayList<LocalViewItem>();
    private  int pos;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.local_view_list_view_fragment, container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        info = (TextView) getActivity().findViewById(R.id.textViewInfo);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Local -> " + Base.Sourcevaltxt + "-" + Base.Destinationvaltxt);
        dattabase = new DatabaseAdapter(getActivity());
        info.setText(Base.detmes);
        populate();

    }

    private void populate() {
        try {
            dattabase.openDataBase();
            local = dattabase.getTrainDetails();
            pos=dattabase.getPosCount2();
            dattabase.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LocalViewAdapter adapter = new LocalViewAdapter(getActivity(), R.layout.local_view_item, local);
        ListView list = (ListView) getActivity().findViewById(R.id.listViewStations);
        list.setAdapter(adapter);
        list.setSelection(pos);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
