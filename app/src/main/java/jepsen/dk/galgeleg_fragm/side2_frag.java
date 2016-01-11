package jepsen.dk.galgeleg_fragm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by camillaolsen on 16/11/15.
 */


public class side2_frag extends Fragment implements View.OnClickListener{

    private ImageView img;
    private EditText editTxt;
    private TextView txt;
    private TextView bogstaver;
    private String bString;
    private String bogstav;
    // private Button bogstav;
    private ViewGroup rod;
    private int[] knap = {R.id.A, R.id.B, R.id.C, R.id.D, R.id.E, R.id.F, R.id.G, R.id.H, R.id.I, R.id.J, R.id.K, R.id.L,
            R.id.M, R.id.N, R.id.O, R.id.P, R.id.Q, R.id.R, R.id.S, R.id.T, R.id.U, R.id.V, R.id.W, R.id.X, R.id.Y, R.id.Z, R.id.Æ, R.id.Ø, R.id.Å};
    private ArrayList <Button> knapper = new ArrayList<Button>();
    Button btn;




    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {

        rod = (ViewGroup) i.inflate(R.layout.side2_frag, container, false);



        if (savedInstanceState == null) {
            Log.d("HALLOOOOOO", "printer - IKKE");
            img = (ImageView) rod.findViewById(R.id.mainImgImageView);
           // editTxt = (EditText) rod.findViewById(R.id.bogstavEditText);
           // editTxt.setOnKeyListener(this);
            txt = (TextView) rod.findViewById(R.id.ordTextView);
            txt.setText(SingleTon.getGlInstance().getSynligtOrd());
            //bogstaver = (TextView) rod.findViewById(R.id.tidligereGætTextView2);
            //bString = bogstaver.getText().toString();
            for (int j = 0; j < knap.length; j++) {
                btn = (Button) rod.findViewById(knap[j]);
                btn.setOnClickListener(this);
                knapper.add(btn);
            }

            Log.d("FØRBOGSTAVER", "printer - IKKE");
        }

        return rod;
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        //bogstav = Integer.toString(letter);
        Log.d("JJJJ", "HEJ");

        if(v instanceof Button){
            Log.d("KKKK", "LOL");
           bogstav = ((Button) v).getText().toString().toLowerCase();
            v.setSelected(true);

    }

        //String bogstav = editTxt.getText().toString().toLowerCase();
                //editTxt.setText(""); // Nulstiller indtastningsfeltet (fjerner bogstav)

                // Gæt på et bogstav og håndter fejlsituationer (ugyldige gæt)
                try {
                    SingleTon.getGlInstance().gætBogstav(bogstav);
                } catch (Exception e){
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show(); // Viser en lille popup med fejltekst fra Galgelogik
                    return;
                }

                //test funktioner
                //SingleTon.getGlInstance().setStatus(true);
                //SingleTon.getGlInstance().setHighscore(100);

                // Opdater listen af gættede bogstaver
                String bogstavGæt = "";
                for (String s  : SingleTon.getGlInstance().getBrugteBogstaver()){
                    bogstavGæt += s + ", " ;
                }
                //bogstaver.setText(bString + bogstavGæt);

                // Opdater det synlige ord
                txt.setText(SingleTon.getGlInstance().getSynligtOrd());

                // Opdater billede hvis sidste gæt er forkert
                if (!SingleTon.getGlInstance().erSidsteBogstavKorrekt()){
                    updateImg();
                }

                // Tjek om spillet er slut
                if (SingleTon.getGlInstance().erSpilletSlut()){
                    //Log.d("SCOREN i int", Integer.toString((int) Math.round(SingleTon.getGlInstance().getScore())));
                    getFragmentManager().popBackStack();
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











}
