package com.rajat.moodle.Tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rajat.moodle.Objects.CommentsObject;
import com.rajat.moodle.Objects.UsersObject;
import com.rajat.moodle.R;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2/23/2016.
 */

    public class customadapter_comments extends ArrayAdapter<CommentsObject> {

        private int layout;
        ArrayList<CommentsObject> data;
    ArrayList<UsersObject> user_id;

        public customadapter_comments(Context context, int resource, ArrayList<CommentsObject> data,ArrayList<UsersObject> user_id) {
            super(context, resource,data);
            layout = resource;
            this.data=data;
            this.user_id=user_id;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            final CommentsObject entry = getItem(position);

            ViewHolder mainViewholder =null;

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout,parent,false);

            ViewHolder_coments viewHolder = new ViewHolder_coments();
            viewHolder.name = (TextView) convertView.findViewById(R.id.comment_name);
            viewHolder.description = (TextView) convertView.findViewById(R.id.comment_description);
            viewHolder.created_at = (TextView) convertView.findViewById(R.id.comment_created_at);
            viewHolder.name.setText(user_id.get(position).getFirst_name()+" "+user_id.get(position).getLast_name());
            viewHolder.description.setText("Description: "+data.get(position).description);
            viewHolder.created_at.setText("Created at: "+data.get(position).created_at);

            convertView.setTag(viewHolder);



            return convertView;
        }
    }

    class ViewHolder_coments{
        TextView name;
        TextView description;
        TextView created_at;

    }

