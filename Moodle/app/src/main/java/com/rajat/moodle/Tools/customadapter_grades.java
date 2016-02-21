package com.rajat.moodle.Tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.rajat.moodle.Objects.GradeObject;

import com.rajat.moodle.R;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2/20/2016.
 */
public class customadapter_grades extends ArrayAdapter<GradeObject> {

    private int layout;
    ArrayList<GradeObject> data;

    public customadapter_grades(Context context, int resource, ArrayList<GradeObject> data) {
        super(context, resource,data);
        layout = resource;
        this.data=data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final GradeObject entry = getItem(position);

        ViewHolder mainViewholder =null;

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout,parent,false);

            ViewHolder_grade viewHolder = new ViewHolder_grade();
            viewHolder.assignment_name = (TextView) convertView.findViewById(R.id.assignment_name);
            viewHolder.score = (TextView) convertView.findViewById(R.id.score);
            viewHolder.weightage = (TextView) convertView.findViewById(R.id.weightage);
            viewHolder.assignment_name.setText(data.get(position).name);
            viewHolder.score.setText("Score: "+Double.toString(data.get(position).score)+"/"+data.get(position).out_of);
            viewHolder.weightage.setText("Weightage: "+data.get(position).weightage);

            convertView.setTag(viewHolder);



        return convertView;
    }
}

class ViewHolder_grade{
    TextView assignment_name;
    TextView score;
    TextView weightage;
}
