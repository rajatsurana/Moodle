package com.rajat.moodle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.rajat.moodle.Volley.VolleyClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link details_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link details_fragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class details_fragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    public details_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment details_fragment.
     */
  /*  // TODO: Rename and change types and number of parameters
    public static details_fragment newInstance(String param1, String param2) {
        details_fragment fragment = new details_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/
    TextView course_description,assignment,grades,threads;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle b=getArguments();
        String course_descrip =b.getString("course_description");
        View v=inflater.inflate(R.layout.fragment_details_fragment, container, false);
        After_login.Part=false;
        After_login.List_id=2;
        After_login.depth=2;
        course_description=(TextView) v.findViewById(R.id.detail);
        assignment=(TextView) v.findViewById(R.id.assign);
        grades=(TextView) v.findViewById(R.id.grade);
        threads=(TextView) v.findViewById(R.id.threads);
        course_description.setText("Course Description: "+course_descrip);
        // course_description.setOnClickListener(textviewlistner);
        grades.setOnClickListener(textviewlistner);
        assignment.setOnClickListener(textviewlistner);
        threads.setOnClickListener(textviewlistner);

        return v;
        //TextView assignment_description=(TextView)
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*   @Override
       public void onAttach(Context context) {
           super.onAttach(context);
           if (context instanceof OnFragmentInteractionListener) {
               mListener = (OnFragmentInteractionListener) context;
           } else {
               throw new RuntimeException(context.toString()
                       + " must implement OnFragmentInteractionListener");
           }
       }*/
    View.OnClickListener textviewlistner=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Bundle b=getArguments();
            int id=view.getId();
            if(id==R.id.assign){
                String course_code=b.getString("course_code");
                VolleyClick.listAllCourseAssignment(course_code,getActivity());
            }
            if(id==R.id.grade){
                String course_code=b.getString("course_code");
                VolleyClick.viewCourseGrades(course_code,getActivity());
            }
            if(id==R.id.threads){
                String course_code=b.getString("course_code");
                VolleyClick.viewCourseThreads(course_code,getActivity());
            }
        }
    };
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

        void onFragmentInteraction(Uri uri);
    }

}
