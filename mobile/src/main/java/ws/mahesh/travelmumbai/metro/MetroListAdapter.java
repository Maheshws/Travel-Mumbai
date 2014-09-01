package ws.mahesh.travelmumbai.metro;

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
 * Created by Mahesh on 7/20/2014.
 */
public class MetroListAdapter extends ArrayAdapter<MetroListItem> {
    private Context context;
    private List<MetroListItem> values;
    private Typeface tf;

    public MetroListAdapter(Context context, int resource, List<MetroListItem> values) {
        super(context, resource, values);
        this.context = context;
        this.values = values;
        tf = Typeface.createFromAsset(context.getAssets(), "font/rupee.ttf");
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.metro_mono_list_item, parent, false);


        TextView dest = (TextView) rowView.findViewById(R.id.textViewDest);
        TextView token = (TextView) rowView.findViewById(R.id.textViewTokenFare);
        TextView card = (TextView) rowView.findViewById(R.id.textViewCardFare);
        MetroListItem metro = values.get(position);

        token.setTypeface(tf);
        card.setTypeface(tf);

        dest.setText(metro.getDESTINATION());
        token.setText("` " + metro.getTOKEN());
        card.setText("` " + metro.getCARD());

        if (position == 0) {
            dest.setTypeface(null, Typeface.BOLD);
            token.setTypeface(null, Typeface.BOLD);
            card.setTypeface(null, Typeface.BOLD);
            token.setText("" + metro.getTOKEN());
            card.setText("" + metro.getCARD());
        }

        if (metro.getCARD().equals("0")) {
            rowView = inflater.inflate(R.layout.metro_mono_current_list_item, parent, false);
            TextView curr = (TextView) rowView.findViewById(R.id.textView);
            curr.setText(metro.getDESTINATION());
        }

        return rowView;
    }
}
