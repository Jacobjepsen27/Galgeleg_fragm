package jepsen.dk.galgeleg_fragm;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.prefs.Preferences;


/**
 * A simple {@link Fragment} subclass.
 */
public class name_frag extends Fragment implements View.OnClickListener{

    public ViewGroup rod;
    private Button gem;
    private TextView name;
    private String navn;
    private long score;

    SharedPreferences sp;
    String userNameString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rod = (ViewGroup) inflater.inflate(R.layout.fragment_name_frag, container, false);

        sp = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        userNameString = sp.getString("minNøgle", "Brugernavn");


        name = (EditText) rod.findViewById(R.id.highscoreName);
        name.setText(userNameString);

        gem = (Button) rod.findViewById(R.id.saveButton);
        gem.setOnClickListener(this);

        return rod;
    }

    @Override
    public void onClick(View v) {

        if(v==gem){

            navn = name.getText().toString();
            score = SingleTon.getGlInstance().getScore();

            sp.edit().putString("minNøgle", navn).commit();

            ParseObject gameScore = new ParseObject("HiScore");
            gameScore.put("navn", navn);
            gameScore.saveInBackground();
            gameScore.put("score", score);
            gameScore.saveInBackground();
            Log.d("Data", "DATA BURDE VÆRE GEMT");

            SingleTon.tempHighscore(navn);

            getParentFragment().getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new highscore_viewTemp())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
