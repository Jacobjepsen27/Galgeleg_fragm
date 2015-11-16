package jepsen.dk.galgeleg_fragm;

import android.content.Intent;
import android.os.AsyncTask;
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

        rod = (ViewGroup) i.inflate (R.layout.side2_frag, container, false);


        if (savedInstanceState == null) {
            img = (ImageView) rod.findViewById(R.id.mainImgImageView);
            editTxt = (EditText) rod.findViewById(R.id.bogstavEditText);
            editTxt.setOnKeyListener(this);
            txt = (TextView) rod.findViewById(R.id.ordTextView);
            bogstaver = (TextView) rod.findViewById(R.id.tidligereGætTextView);
            bString = bogstaver.getText().toString();
            gæt = (Button) rod.findViewById(R.id.gætButton);
            gæt.setOnClickListener(this);
            gæt.setAnimation(Velkomst_frag.animation);

            //Velkomst_akt.gl.nulstil(); // Fra version 1
            hentOrd(); // Version 2 vha. asynctask

            txt.setText("");
            Toast.makeText(this, "Henter tekst fra the webz", Toast.LENGTH_SHORT).show();
        }
    }
}
    private void hentOrd(){
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    Velkomst_frag.gl.hentOrdFraDr();
                    return "Ordene blev hentet!";
                } catch (Exception e){
                    return "Der skete en fejl!";
                }
            }

            @Override
            protected void onPostExecute(Object result){
                txt.setText(Velkomst_frag.gl.getSynligtOrd());
            }
        }.execute();
    }

    @Override
    public void onClick(View v) {
        String bogstav = editTxt.getText().toString().toLowerCase();
        editTxt.setText(""); // Nulstiller indtastningsfeltet (fjerner bogstav)

        // Gæt på et bogstav og håndter fejlsituationer (ugyldige gæt)
        try {
            Velkomst_frag.gl.gætBogstav(bogstav);
        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show(); // Viser en lille popup med fejltekst fra Galgelogik
            return;
        }

        // Opdater listen af gættede bogstaver
        String bogstavGæt = "";
        for (String s  : Velkomst_frag.gl.getBrugteBogstaver()){
            bogstavGæt += s + ", " ;
        }
        bogstaver.setText(bString + bogstavGæt);

        // Opdater det synlige ord
        txt.setText(Velkomst_frag.gl.getSynligtOrd());

        // Opdater billede hvis sidste gæt er forkert
        if (!Velkomst_frag.gl.erSidsteBogstavKorrekt()){
            updateImg();
        }

        // Tjek om spillet er slut
        if (Velkomst_frag.gl.erSpilletSlut()){
        //    Intent i = new Intent(this, side3_akt.class);
         //   startActivity(i);
         //   overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
         //   finish(); // Slutter aktiviteten så man ikke kan trykke på "tilbage"-knap for at se denne aktivitet
        }
    }

    // Opdaterer billedet mht. antal gæt der er brugt
    public void updateImg(){
        switch (Velkomst_frag.gl.getAntalForkerteBogstaver()) {
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
