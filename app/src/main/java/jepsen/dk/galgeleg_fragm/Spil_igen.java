package jepsen.dk.galgeleg_fragm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Spil_igen extends Fragment implements View.OnClickListener{

    private ViewGroup rod;
    private Button again;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        rod = (ViewGroup) inflater.inflate(R.layout.fragment_spil_igen, container, false);

        again = (Button) rod.findViewById(R.id.playAgain);
        again.setOnClickListener(this);

        return rod;
    }

    @Override
    public void onClick(View v) {
        if(v==again){
            SingleTon.getGlInstance().nulstil();
            SingleTon.hentScore();
            getParentFragment().getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new Velkomst_frag())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
