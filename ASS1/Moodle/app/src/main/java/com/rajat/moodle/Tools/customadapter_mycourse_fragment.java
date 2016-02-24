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
public class customadapter_mycourse_fragment extends ArrayAdapter<CourseObject> {

    private int layout;
    ArrayList<CourseObject> data;

    public customadapter_mycourse_fragment(Context context, int resource, ArrayList<CourseObject> data) {
        super(context, resource,data);
        layout = resource;
        this.data=data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final CourseObject entry = getItem(position);
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout,parent,false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.course_name);
            viewHolder.code = (TextView) convertView.findViewById(R.id.course_code);
            viewHolder.l_t_p = (TextView) convertView.findViewById(R.id.course_l_t_p);
            viewHolder.code.setText(data.get(position).code);
            viewHolder.name.setText(data.get(position).name_course);
            viewHolder.l_t_p.setText(data.get(position).l_t_p);

            convertView.setTag(viewHolder);



        return convertView;
    }
}

class ViewHolder{
    TextView code;
    TextView name;
    TextView l_t_p;
}
