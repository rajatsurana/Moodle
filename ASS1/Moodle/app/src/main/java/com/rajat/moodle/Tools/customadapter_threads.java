package com.rajat.moodle.Tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.rajat.moodle.Objects.CourseThreadObject;

import com.rajat.moodle.R;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2/20/2016.
 */
public class customadapter_threads extends ArrayAdapter<CourseThreadObject> {

    private int layout;
    ArrayList<CourseThreadObject> data;

    public customadapter_threads(Context context, int resource, ArrayList<CourseThreadObject> data) {
        super(context, resource,data);
        layout = resource;
        this.data=data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout,parent,false);

            ViewHolder_thread viewHolder = new ViewHolder_thread();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title_thread);
            viewHolder.thread_description=(TextView)convertView.findViewById(R.id.thread_description) ;
            viewHolder.create_time = (TextView) convertView.findViewById(R.id.create_time);
            viewHolder.update_time = (TextView) convertView.findViewById(R.id.update_time);
         //   viewHolder.no_of_late_days = (TextView) convertView.findViewById(R.id.late_days_allowed);
        //    viewHolder.file = (TextView) convertView.findViewById(R.id.file);

            viewHolder.title.setText(data.get(position).title);
            viewHolder.thread_description.setText("Description: "+data.get(position).description);
            viewHolder.create_time.setText("Created at: "+data.get(position).created_at);
            viewHolder.update_time.setText("Updated at: "+data.get(position).updated_at);
         //   viewHolder.no_of_late_days.setText("No. of late days allowed: "+Integer.toString(data.get(position).late_days_allowed));
           // viewHolder.file.setText(toString(data.get(position).file_));
            convertView.setTag(viewHolder);



        return convertView;
    }
}

class ViewHolder_thread{
    TextView title;
    TextView thread_description;
    TextView create_time;
    TextView update_time;
  //  TextView no_of_late_days;
   // TextView file;
}
