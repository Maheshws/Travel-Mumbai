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
 * Created by Mahesh on 7/27/2014.
 */
public class LocalsAdapter extends ArrayAdapter<LocalItem> {
    private Context context;
    private List<LocalItem> values;
    private String dest;

    public LocalsAdapter(Context context, int resource, List<LocalItem> objects) {
        super(context, resource, objects);
        this.context = context;
        values = objects;
        dest=Base.Destinationvaltxt;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.local_listview_item, parent, false);
        TextView startTime = (TextView) rowView.findViewById(R.id.textViewDeptTime);
        TextView reachTime = (TextView) rowView.findViewById(R.id.textViewArrTime);
        TextView srcdest = (TextView) rowView.findViewById(R.id.textViewSrcDest);
        TextView cars = (TextView) rowView.findViewById(R.id.textViewCars);
        TextView speed = (TextView) rowView.findViewById(R.id.textViewSpeed);

        LocalItem local = values.get(position);

        startTime.setText(local.getLEAVES_AT().toUpperCase());
        reachTime.setText("Reaches "+dest+" by : "+local.getREACHES_DEST());
        srcdest.setText(local.getSOURCE().toUpperCase() + " - " + local.getDESTINATION().toUpperCase());
        cars.setText(local.getCARS());
        speed.setText(local.getTYPE().toUpperCase());

        if(local.getTYPE().equalsIgnoreCase("FAST"))
            speed.setTextColor(Color.CYAN);
        return rowView;
    }
}
