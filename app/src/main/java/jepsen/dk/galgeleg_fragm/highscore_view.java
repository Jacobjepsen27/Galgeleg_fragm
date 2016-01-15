package jepsen.dk.galgeleg_fragm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class highscore_view extends Fragment {

    private ViewGroup rod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        // Inflate the layout for this fragment
//        rod = (ViewGroup)
        return inflater.inflate(R.layout.fragment_highscore_view, container, false);
//        return rod;
    }

}
