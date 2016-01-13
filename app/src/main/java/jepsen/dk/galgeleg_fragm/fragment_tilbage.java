package jepsen.dk.galgeleg_fragm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_tilbage extends Fragment implements View.OnClickListener{

    private Button tilbage;
    private ViewGroup rod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rod = (ViewGroup) inflater.inflate(R.layout.fragment_fragment_tilbage, container, false);
        tilbage = (Button) rod.findViewById(R.id.tilbage);
        tilbage.setOnClickListener(this);
        return rod;
    }

    @Override
    public void onClick(View v) {
        if(v==tilbage){
            Log.d("KLIK", "onClick: virker");
            getParentFragment().getFragmentManager().popBackStack();

        }
    }
}

