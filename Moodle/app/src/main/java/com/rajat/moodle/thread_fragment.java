package com.rajat.moodle;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.rajat.moodle.Objects.CourseThreadObject;
import com.rajat.moodle.Tools.customadapter_threads;
import com.rajat.moodle.Volley.VolleyClick;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link thread_fragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class thread_fragment extends Fragment implements AbsListView.OnItemClickListener{
    Bundle bundle;
    private OnFragmentInteractionListener mListener;
    ArrayList<CourseThreadObject> values=new ArrayList<CourseThreadObject>();

    boolean desc_thread_also=false;
    boolean title_thread_also=false;
    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;


    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private customadapter_threads mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public thread_fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // TODO: Change Adapter to display your content

    }
FloatingActionButton fab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bundle=getArguments();
        values=bundle.getParcelableArrayList("thread");
        View view = inflater.inflate(R.layout.fragment_thread_fragmentlist, container, false);
        fab=(FloatingActionButton)view.findViewById(R.id.fabThread);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddThreadDialog(view.getContext());
            }
        });

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);


        mAdapter = new customadapter_threads(getActivity(),
                R.layout.thread_fragment_layout,values);
        ((AdapterView<ListAdapter>)mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    /*  @Override
      public void onAttach(Activity activity) {
          super.onAttach(activity);
          try {
              mListener = (OnFragmentInteractionListener) activity;
          } catch (ClassCastException e) {
              throw new ClassCastException(activity.toString()
                      + " must implement suooo   OnFragmentInteractionListener");
          }
      }
  */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      //  VolleyClick.viewParticularThread();
        VolleyClick.viewParticularThread(values.get(position).id,values.get(position),getActivity());
        /*Bundle bundle = getArguments();
        boolean grade=bundle.getBoolean("grade");
        if(grade==true){
            VolleyClick.viewCourseGrades(values.get(position).code,getActivity());

           /* Bundle bundle=new Bundle();
            //bundle.put
                    CourseThreadObject[] sd = new CourseThreadObject[2];
            Intent i = new Intent();
            i.putExtra("fds",sd);
            Bundle a=new Bundle();
            a.putSerializable("fds",sd);
            //Intent s=getActivity().getIntent();
            Bundle b=getArguments(); //s.getExtras();
            //b.getExtras();
            CourseThreadObject[] asas=(CourseThreadObject[])b.getSerializable("fds");
        }
        else{}*/
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            //  mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }
    public void AddThreadDialog(final Context context)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.new_thread_lay_dialog, null);
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setTitle("Wanna Add Thread");
        alert.setView(promptView);
        // alert.setIcon(R.drawable.button_login_select);


        final EditText input = (EditText) promptView
                .findViewById(R.id.thread_title);
        final EditText input2 = (EditText) promptView.findViewById(R.id.thread_description);
        input.requestFocus();
        input.setHint("Title");
        // input.setTextColor(context.getResources().getColor(R.color.splashstatus));
        //input.requestFocus();
        input2.setHint("Description");
        input2.setTextColor(context.getResources().getColor(R.color.splashstatus));
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String thread_title = input.getText().toString();
                String thread_desc = input2.getText().toString();
                if (!thread_title.equals("") && !thread_desc.equals("")) {
                    VolleyClick.createNewThread(thread_title, thread_desc, "cop290", getActivity());
                } else if (thread_title.equals("") && !thread_desc.equals("")) {
                    input.setError("Title required");
                } else if (!thread_title.equals("") && thread_desc.equals("")) {
                    input2.setError("Description required");
                } else {
                    input.setError("Title required");
                    input2.setError("Description required");
                }


            }
        });
        final AlertDialog alert1 = alert.create();

        input.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {// TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // TODO Auto-generated method stub
                if (editable.toString().length() != 0 && input2.getText().toString().length()!=0 ) {
                    //title_thread_also=true;
                    alert1.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(true);
                } else {
                    //title_thread_also=false;
                    alert1.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
                }
            }
        });
        input2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {// TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // TODO Auto-generated method stub
                if (editable.toString().length() != 0 && input.getText().toString().length()!=0) {
                    //desc_thread_also=true;
                    alert1.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(true);
                    //alert1.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    //desc_thread_also=false;
                    alert1.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
                    //alert1.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(true);
                }
            }
        });
        alert1.show();
        alert1.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);


    }
}
