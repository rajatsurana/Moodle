package com.rajat.moodle.Tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.rajat.moodle.Objects.AssignmentObject;
import com.rajat.moodle.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2/20/2016.
 */
public class customadapter_assignment extends ArrayAdapter<AssignmentObject> {

    private int layout;
    ArrayList<AssignmentObject> data;

    public customadapter_assignment(Context context, int resource, ArrayList<AssignmentObject> data) {
        super(context, resource,data);
        layout = resource;
        this.data=data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout,parent,false);

            ViewHolder_assignment viewHolder = new ViewHolder_assignment();
            viewHolder.assignment = (TextView) convertView.findViewById(R.id.assignment);
            viewHolder.assignment_description=(TextView)convertView.findViewById(R.id.assignment_description) ;
            viewHolder.created_at = (TextView) convertView.findViewById(R.id.create);
            viewHolder.deadline = (TextView) convertView.findViewById(R.id.deadline);
            viewHolder.no_of_late_days = (TextView) convertView.findViewById(R.id.late_days_allowed);
            viewHolder.file = (TextView) convertView.findViewById(R.id.file);

            viewHolder.assignment.setText(data.get(position).name);
            viewHolder.assignment_description.setText("Description: "+data.get(position).description);
            viewHolder.created_at.setText("Created at: "+data.get(position).created_at);
            viewHolder.deadline.setText("Deadline: "+data.get(position).deadline);
            viewHolder.no_of_late_days.setText("No. of late days allowed: "+Integer.toString(data.get(position).late_days_allowed));
           // viewHolder.file.setText(toString(data.get(position).file_));
            convertView.setTag(viewHolder);



        return convertView;
    }
}

class ViewHolder_assignment{
    TextView assignment;
    TextView assignment_description;
    TextView created_at;
    TextView deadline;
    TextView no_of_late_days;
    TextView file;
}
