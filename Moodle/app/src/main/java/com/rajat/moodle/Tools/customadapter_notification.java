package com.rajat.moodle.Tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.rajat.moodle.Objects.NotificationObject;
import com.rajat.moodle.R;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2/20/2016.
 */
public class customadapter_notification extends ArrayAdapter<NotificationObject> {

    private int layout;
    ArrayList<NotificationObject> data;

    public customadapter_notification(Context context, int resource, ArrayList<NotificationObject> data) {
        super(context, resource,data);
        layout = resource;
        this.data=data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout,parent,false);

            ViewHolder_notification viewHolder = new ViewHolder_notification();
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            viewHolder.created_at = (TextView) convertView.findViewById(R.id.created_at);
            viewHolder.seen = (RadioButton) convertView.findViewById(R.id.radioButton);
            viewHolder.description.setText(data.get(position).description);
            viewHolder.created_at.setText(data.get(position).created_at);
            if(data.get(position).is_seen==1)viewHolder.seen.setChecked(true);
            else viewHolder.seen.setChecked(false);
            convertView.setTag(viewHolder);



        return convertView;
    }
}

class ViewHolder_notification{
    TextView description;
    TextView created_at;
    RadioButton seen;
}
