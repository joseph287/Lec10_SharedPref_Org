package edu.montclair.mobilecomputing.m_alrajab.lec10_sharedpref_org;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static edu.montclair.mobilecomputing.m_alrajab.lec10_sharedpref_org.utils.Utils.getFileByName;
import static edu.montclair.mobilecomputing.m_alrajab.lec10_sharedpref_org.utils.Utils.getListFromFiles;
import static edu.montclair.mobilecomputing.m_alrajab.lec10_sharedpref_org.utils.Utils.getListFromSP;


public class TitlesFragment extends Fragment {
    SharedPreferences sharedPreferences;
    private OnFragmentInteractionListener mListener;
    public TitlesFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_titles, container, false);
        ListView ls=(ListView)view.findViewById(R.id.list_frg);
 // Omitted for now        ls.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,
  //Omitted for now               getListFromSP(getContext(),"Title_")));


        //Files connection to SharedPref
        ls.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,
                getListFromFiles(getContext())));
      ls.setOnItemClickListener(new AdapterView.OnItemClickListener()



      {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
 //Omitted for now                mListener.onFragmentInteraction(getListFromSP(getContext(),"Body_")[i]);

            //Files connection to SharedPref

                mListener.onFragmentInteraction(getFileByName(getContext(), getListFromFiles(getContext())[i]));

            }
        });
        return view;
    }
    public void onButtonPressed(String uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String uri);
    }
}
