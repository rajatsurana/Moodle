package com.rajat.moodle;

//import android.content.Context;
//import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.rajat.moodle.Objects.CommentsObject;
import com.rajat.moodle.Objects.CourseThreadObject;
import com.rajat.moodle.Tools.customadapter_comments;
//import com.rajat.moodle.Tools.customadapter_universal;
//import com.rajat.moodle.Volley.VolleyClick;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link thread_description_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link thread_description_fragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class thread_description_fragment extends Fragment implements AbsListView.OnItemClickListener{
    Bundle bundle;
    private OnFragmentInteractionListener mListener;
    ArrayList<CommentsObject> values=new ArrayList<CommentsObject>();



    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;
    private TextView title,description,created_at,updated_at;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private customadapter_comments mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public thread_description_fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // TODO: Change Adapter to display your content

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bundle=getArguments();
        CourseThreadObject courseThreadObject=bundle.getParcelable("thread_description");
        values=bundle.getParcelableArrayList("comments");
        View view = inflater.inflate(R.layout.fragment_thread_description_fragment, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        title=(TextView)view.findViewById(R.id.title_thread_1);
        description=(TextView)view.findViewById(R.id.thread_description_1);
        created_at=(TextView)view.findViewById(R.id.create_time_1);
        updated_at=(TextView)view.findViewById(R.id.update_time_1);
        title.setText(courseThreadObject.title);
        description.setText(courseThreadObject.description);
        created_at.setText(courseThreadObject.created_at);
        updated_at.setText(courseThreadObject.updated_at);
        mAdapter = new customadapter_comments(getActivity(),
                R.layout.comment_layout,values);
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
       // Bundle bundle = getArguments();
      //  boolean grade=bundle.getBoolean("grade");
       // if(grade==true){
          //  VolleyClick.viewCourseGrades(values.get(position).code,getActivity());

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
            CourseThreadObject[] asas=(CourseThreadObject[])b.getSerializable("fds");*/
       // }
      //  else{}
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

}
