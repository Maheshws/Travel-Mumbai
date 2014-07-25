package ws.mahesh.travelmumbai.monorail;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
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
public class MonoListAdapter extends ArrayAdapter<MonoListItem> {
    private Context context;
    private List<MonoListItem> values;

    public MonoListAdapter(Context context, int resource, List<MonoListItem> values) {
        super(context, resource, values);
        this.context = context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.metro_mono_list_item, parent, false);
        TextView dest = (TextView) rowView.findViewById(R.id.textViewDest);
        TextView token = (TextView) rowView.findViewById(R.id.textViewTokenFare);
        TextView card = (TextView) rowView.findViewById(R.id.textViewCardFare);

        MonoListItem mono = values.get(position);

        if (position == 0) {
            dest.setTypeface(null, Typeface.BOLD);
            token.setTypeface(null, Typeface.BOLD);
            card.setTypeface(null, Typeface.BOLD);
        }

        dest.setText(mono.getDESTINATION());
        token.setText(mono.getTOKEN());
        card.setText(mono.getCARD());
        if(mono.getCARD().equals("Rs.0"))
        {
            rowView = inflater.inflate(R.layout.metro_mono_current_list_item, parent, false);
            TextView curr = (TextView) rowView.findViewById(R.id.textView);
            curr.setText(mono.getDESTINATION());
        }
        return rowView;
    }
}
