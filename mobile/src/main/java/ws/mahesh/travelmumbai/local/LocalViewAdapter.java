package ws.mahesh.travelmumbai.local;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ws.mahesh.travelmumbai.R;

/**
 * Created by Mahesh on 7/30/2014.
 */
public class LocalViewAdapter extends ArrayAdapter<LocalViewItem> {
    private Context context;
    private List<LocalViewItem> values;
    private int pos = 0;

    public LocalViewAdapter(Context context, int resource, List<LocalViewItem> objects) {
        super(context, resource, objects);
        this.context = context;
        values = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.local_view_item, parent, false);
        TextView Time = (TextView) rowView.findViewById(R.id.textViewTime);
        TextView Station = (TextView) rowView.findViewById(R.id.textViewStation);

        LocalViewItem local = values.get(position);

        Time.setText(local.getTIME().toUpperCase());
        Station.setText(local.getSTATION().toUpperCase());

        if (local.getSTATION().equalsIgnoreCase(Base.Sourcevaltxt)) {
            Station.setTextColor(Color.CYAN);
            pos = position;
        }
        return rowView;
    }

    public int getPos() {
        return pos;
    }
}
