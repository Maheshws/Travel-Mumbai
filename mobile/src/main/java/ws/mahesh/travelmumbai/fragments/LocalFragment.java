package ws.mahesh.travelmumbai.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ws.mahesh.travelmumbai.MainActivity;
import ws.mahesh.travelmumbai.R;
import ws.mahesh.travelmumbai.local.Base;
import ws.mahesh.travelmumbai.local.LocalSelectorFragment;
import ws.mahesh.travelmumbai.misc.MegaBlockInfoActivity;
import ws.mahesh.travelmumbai.misc.WebViewActivity;

/**
 * Created by Mahesh on 7/25/2014.
 */
public class LocalFragment extends Fragment {

    Button MegaBlock, RailMap, WR, CR, HR;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.local_main_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Travel Mumbai - Local");
        MegaBlock = (Button) getActivity().findViewById(R.id.buttonMegaBlockInfo);
        RailMap = (Button) getActivity().findViewById(R.id.buttonRailMap);
        WR = (Button) getActivity().findViewById(R.id.buttonWR);
        CR = (Button) getActivity().findViewById(R.id.buttonCR);
        HR = (Button) getActivity().findViewById(R.id.buttonHR);

        WR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Base.alltrains = false;
                Base.trainLine = "WR";
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new LocalSelectorFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        CR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Base.alltrains = false;
                Base.trainLine = "CR";
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new LocalSelectorFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        HR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Base.alltrains = false;
                Base.trainLine = "HR";
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new LocalSelectorFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });


        MegaBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MegaBlockInfoActivity.class));
            }
        });

        RailMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), WebViewActivity.class);
                i.putExtra("asset", true);
                i.putExtra("location", "railmap/railmap.html");
                startActivity(i);
            }
        });
    }
}
