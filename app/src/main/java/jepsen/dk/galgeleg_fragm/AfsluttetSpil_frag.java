package jepsen.dk.galgeleg_fragm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Ebbe on 16-11-2015.
 */
public class AfsluttetSpil_frag extends Fragment implements View.OnClickListener{

    private ViewGroup rod;
    private Button again, highscore;
    private TextView status, tabt, hs;

    public View onCreateView(LayoutInflater i, ViewGroup container,Bundle savedInstanceState) {

        rod = (ViewGroup) i.inflate(R.layout.afsluttetspil_frag, container, false);



            again = (Button) rod.findViewById(R.id.againButton);
            again.setOnClickListener(this);
            again.setAnimation(Velkomst_frag.animation);
            status = (TextView) rod.findViewById(R.id.ord);
            hs = (TextView) rod.findViewById(R.id.ord3);
            tabt = (TextView) rod.findViewById(R.id.ordetVar);
            highscore = (Button) rod.findViewById(R.id.highscoreButton);
            highscore.setOnClickListener(this);

        Log.d("HIGHSCORE", Long.toString(SingleTon.getGlInstance().getScore()));
            if (SingleTon.getGlInstance().erSpilletTabt()) {
                tabt.setText("Ordet var: " + "\n" + SingleTon.getGlInstance().getOrdet() );
                status.setText("Du har tabt!");
                hs.setText("Du fik " + Long.toString(SingleTon.getGlInstance().getScore()) + " point");
                highscore.setText("Se highscore");
            } else if (SingleTon.getGlInstance().erSpilletVundet()) {
                if(Galgelogik.inHighscore()==true) {
                    tabt.setText("Du gættede:" + "\n" + SingleTon.getGlInstance().getOrdet() );
                    status.setText("Du har vundet!");
                    hs.setText("Du fik " + Long.toString(SingleTon.getGlInstance().getScore()) + " point");
                    highscore.setText("Gem highscore");
                }
                else{ tabt.setText("Du gættede:" + "\n" + SingleTon.getGlInstance().getOrdet() );
                    status.setText("Du har vundet!");
                    hs.setText("Du fik " + Long.toString(SingleTon.getGlInstance().getScore()) + " point");
                    highscore.setText("Se highscore");
                }
            }


        return rod;
    }

    @Override
    public void onClick(View v) {
        if (v==again){
            //Velkomst_frag.gl.saetsvaerhedsgrad(0);
            SingleTon.getGlInstance().nulstil();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new Velkomst_frag())
                    .addToBackStack(null)
                    .commit();

    } else if (v==highscore){
            if(SingleTon.getGlInstance().erSpilletVundet() && Galgelogik.inHighscore()) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragmentindhold, new highscore_cont())
                            .addToBackStack(null)
                            .commit();
            } else {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new highscore_view())
                        .addToBackStack(null)
                        .commit();
            }
        }
}
}
