package ws.mahesh.travelmumbai;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        ActionBar actionBar = getActionBar();
        actionBar.show();
        actionBar.setTitle("Travel Mumbai");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            ActionBar ab = getActivity().getActionBar();
            ab.setTitle("Travel Mumbai");

            ImageButton metro = (ImageButton) getActivity().findViewById(R.id.imageButtonMetro);
            ImageButton auto = (ImageButton) getActivity().findViewById(R.id.imageButtonAuto);
            ImageButton taxi = (ImageButton) getActivity().findViewById(R.id.imageButtonTaxi);
            ImageButton mono = (ImageButton) getActivity().findViewById(R.id.imageButtonMono);
            ImageButton prefs = (ImageButton) getActivity().findViewById(R.id.imageButtonPreferences);

            metro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, new MetroFragment())
                            .addToBackStack(null)
                            .commit();
                }
            });

            auto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, new AutoFragment())
                            .addToBackStack(null)
                            .commit();
                }
            });

            taxi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, new TaxiFragment())
                            .addToBackStack(null)
                            .commit();
                }
            });

            mono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     getFragmentManager().beginTransaction()
                            .replace(R.id.container, new MonorailFragment())
                            .addToBackStack(null)
                            .commit();
                }
            });

            prefs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   startActivity(new Intent(getActivity(),PreferencesActivity.class));
                }
            });
        }

    }
}
