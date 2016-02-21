package com.rajat.moodle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.rajat.moodle.Tools.customadapter_universal;
import com.rajat.moodle.Objects.CourseObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link mycourses_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link mycourses_fragment#//ewInstance} factory method to
 * create an instance of this fragment.
 */
public class universal_fragment extends Fragment implements AbsListView.OnItemClickListener{
    private OnFragmentInteractionListener mListener;
    ArrayList<CourseObject> values=new ArrayList<CourseObject>();
    CourseObject a;
    public void arpit() {
        for (int i = 0; i < 4; i++) {
            a=new CourseObject("cop290","deign practices","computer science","3-0-2",3,1);
            values.add(a);
        }
    }


    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private customadapter_universal mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public universal_fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // TODO: Change Adapter to display your content

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_universal_fragment, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        arpit();
        mAdapter = new customadapter_universal(getActivity(),
                R.layout.universal_layout,values);
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

