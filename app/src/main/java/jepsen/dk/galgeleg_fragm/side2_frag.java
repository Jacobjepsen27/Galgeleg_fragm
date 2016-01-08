package jepsen.dk.galgeleg_fragm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by camillaolsen on 16/11/15.
 */


public class side2_frag extends Fragment implements View.OnClickListener, View.OnKeyListener {

    private ImageView img;
    private EditText editTxt;
    private TextView txt;
    private TextView bogstaver;
    private String bString;
    private Button gæt;
    private ViewGroup rod;


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {

        rod = (ViewGroup) i.inflate(R.layout.side2_frag, container, false);


        if (savedInstanceState == null) {
            img = (ImageView) rod.findViewById(R.id.mainImgImageView);
            editTxt = (EditText) rod.findViewById(R.id.bogstavEditText);
            editTxt.setOnKeyListener(this);
            txt = (TextView) rod.findViewById(R.id.ordTextView);
            txt.setText(SingleTon.getGlInstance().getSynligtOrd());
            bogstaver = (TextView) rod.findViewById(R.id.tidligereGætTextView2);
            bString = bogstaver.getText().toString();
            gæt = (Button) rod.findViewById(R.id.gætButton);
            gæt.setOnClickListener(this);
            gæt.setAnimation(Velkomst_frag.animation);
        }

        return rod;
    }

    @Override
    public void onClick(View v) {
        String bogstav = editTxt.getText().toString().toLowerCase();
        editTxt.setText(""); // Nulstiller indtastningsfeltet (fjerner bogstav)

        // Gæt på et bogstav og håndter fejlsituationer (ugyldige gæt)
        try {
            SingleTon.getGlInstance().gætBogstav(bogstav);
        } catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show(); // Viser en lille popup med fejltekst fra Galgelogik
            return;
        }

        //test funktioner
        SingleTon.getGlInstance().setStatus(true);
        SingleTon.getGlInstance().setHighscore(100);

        // Opdater listen af gættede bogstaver
        String bogstavGæt = "";
        for (String s  : SingleTon.getGlInstance().getBrugteBogstaver()){
            bogstavGæt += s + ", " ;
        }
        bogstaver.setText(bString + bogstavGæt);

        // Opdater det synlige ord
        txt.setText(SingleTon.getGlInstance().getSynligtOrd());

        // Opdater billede hvis sidste gæt er forkert
        if (!SingleTon.getGlInstance().erSidsteBogstavKorrekt()){
            updateImg();
        }

        // Tjek om spillet er slut
        if (SingleTon.getGlInstance().erSpilletSlut()){
            //Log.d("SCOREN i int", Integer.toString((int) Math.round(SingleTon.getGlInstance().getScore())));
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new AfsluttetSpil_frag())
                    .addToBackStack(null)
                    .commit();
        }
    }

    // Opdaterer billedet mht. antal gæt der er brugt
    public void updateImg(){
        switch (SingleTon.getGlInstance().getAntalForkerteBogstaver()) {
            case 0:
                img.setImageResource(R.drawable.galge);
                break;
            case 1:
                img.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                img.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                img.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                img.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                img.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                img.setImageResource(R.drawable.forkert6);
                break;
        }
    }

    // Klikker på "Gæt"-knappen når der trykkes på "Enter"-knappen på tastaturet
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            gæt.callOnClick(); // Klikker på "Gæt"-knappen
            return true;
        }
        return false;
    }



}
