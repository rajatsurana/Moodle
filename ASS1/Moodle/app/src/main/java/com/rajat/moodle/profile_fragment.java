package com.rajat.moodle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class profile_fragment extends Fragment {

    View view;
    EditText first_n,
            last_n, email, username,
            entry_num;
    String sfirst_n,
            slast_n, semail, susername,
            sentry_num;
    Button apply;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        After_login.List_id=4;
        After_login.Part=true;
    }

    public void initializeViews(){
        first_n = (EditText)view.findViewById(R.id.editText_name);

        email = (EditText)view.findViewById(R.id.editText_email);
        username = (EditText)view.findViewById(R.id.editText_username);
        entry_num = (EditText)view.findViewById(R.id.editText_entry);
        apply = (Button) view.findViewById(R.id.button_apply);

        apply.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                onApplyClick();
            }
        });

    }

    public void onApplyClick(){


    }
    public profile_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile_fragment, container, false);



        return view;
    }

}
