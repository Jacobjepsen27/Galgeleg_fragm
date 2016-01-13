package jepsen.dk.galgeleg_fragm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class forsideHS_frag extends Fragment {

    private ViewGroup rod;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rod = (ViewGroup) inflater.inflate(R.layout.fragment_forside_hs_frag, container, false);
        return rod;
    }

}
