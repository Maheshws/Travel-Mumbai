package ws.mahesh.travelmumbai.auto;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ws.mahesh.travelmumbai.R;

/**
 * Created by Mahesh on 7/15/2014.
 */
public class AutoListAdapter extends ArrayAdapter<AutoListItem> {

    private Context context;
    private List<AutoListItem> values;
    private Typeface tf;


    public AutoListAdapter(Context context, int resource, List<AutoListItem> values) {
        super(context, resource, values);
        this.context = context;
        this.values = values;
        tf = Typeface.createFromAsset(context.getAssets(), "font/rupee.ttf");
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.auto_taxi_item, parent, false);
        TextView meter = (TextView) rowView.findViewById(R.id.textViewListMeter);
        TextView distance = (TextView) rowView.findViewById(R.id.textViewListDistance);
        TextView day_fare = (TextView) rowView.findViewById(R.id.textViewListDayFare);
        TextView night_fare = (TextView) rowView.findViewById(R.id.textViewListNightFare);

        day_fare.setTypeface(tf);
        night_fare.setTypeface(tf);

        AutoListItem auto = values.get(position);

        meter.setText(auto.getREADING());
        distance.setText(auto.getDISTANCE());
        day_fare.setText("` " + auto.getDAY_FARE());
        night_fare.setText("` " + auto.getNIGHT_FARE());

        return rowView;
    }
}
