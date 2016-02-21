package com.rajat.moodle.Tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rajat.moodle.Objects.CourseObject;
import com.rajat.moodle.R;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2/20/2016.
 */
public class customadapter_universal extends ArrayAdapter<CourseObject> {

    private int layout;
    ArrayList<CourseObject> data;

    public customadapter_universal(Context context, int resource, ArrayList<CourseObject> data) {
        super(context, resource,data);
        layout = resource;
        this.data=data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(layout,parent,false);

        ViewHolder_universal viewHolder = new ViewHolder_universal();
        viewHolder.name = (TextView) convertView.findViewById(R.id.course_name_1);
        viewHolder.code = (TextView) convertView.findViewById(R.id.course_code_1);
        viewHolder.code.setText(data.get(position).code);
        viewHolder.name.setText(data.get(position).name_course);

        convertView.setTag(viewHolder);



        return convertView;
    }
}

class ViewHolder_universal{
    TextView code;
    TextView name;
}
