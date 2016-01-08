package jepsen.dk.galgeleg_fragm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class name_frag extends Fragment implements View.OnClickListener{

    public ViewGroup rod;

    private Button gem;
    private TextView name;
    private String navn;
    private long score;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rod = (ViewGroup) inflater.inflate(R.layout.fragment_name_frag, container, false);

        name = (TextView) rod.findViewById(R.id.highscoreName);

        gem = (Button) rod.findViewById(R.id.saveButton);
        gem.setOnClickListener(this);

        return rod;
    }

    @Override
    public void onClick(View v) {

        if(v==gem){
            navn = name.getText().toString();
            score = SingleTon.getGlInstance().getScore();

            ParseObject gameScore = new ParseObject("HiScore");
            gameScore.put("navn", navn);
            gameScore.saveInBackground();
            gameScore.put("score", score);
            gameScore.saveInBackground();
            Log.d("Data", "DATA BURDE VÆRE GEMT");

            getParentFragment().getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new highscore_viewTemp())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
