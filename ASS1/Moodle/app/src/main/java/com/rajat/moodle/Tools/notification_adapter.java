package com.rajat.moodle.Tools;

/**
 * Created by Rajat on 23-02-2016.
 */



        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.RadioButton;
        import android.widget.TextView;
        import com.rajat.moodle.Objects.AssignmentObject;
        import com.rajat.moodle.Objects.NotificationObject;
        import com.rajat.moodle.R;

        import org.w3c.dom.Text;

        import java.util.ArrayList;

/**
 * Created by Lenovo on 2/20/2016.
 */
public class notification_adapter extends ArrayAdapter<NotificationObject> {

    private int layout;
    ArrayList<NotificationObject> data;

    public notification_adapter(Context context, int resource, ArrayList<NotificationObject> data) {
        super(context, resource,data);
        layout = resource;
        this.data=data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(layout,parent,false);

        ViewHolder_notification viewHolder = new ViewHolder_notification();
        viewHolder.created_time = (TextView) convertView.findViewById(R.id.created_at);
        viewHolder.notification_description=(TextView)convertView.findViewById(R.id.notification_description) ;
        //viewHolder.seen = (RadioButton) convertView.findViewById(R.id.radioButton);


        viewHolder.created_time.setText(data.get(position).created_at);
        viewHolder.notification_description.setText(""+data.get(position).description);

       // viewHolder.file.setText(toString(data.get(position).file_));
        convertView.setTag(viewHolder);



        return convertView;
    }
}

class ViewHolder_notification{
    TextView created_time;
    TextView notification_description;
    //RadioButton seen;
}
