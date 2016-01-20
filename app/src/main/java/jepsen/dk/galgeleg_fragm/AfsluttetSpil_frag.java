package jepsen.dk.galgeleg_fragm;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

            again = (Button) rod.findViewById(R.id.againButton);
            again.setOnClickListener(this);
            again.setAnimation(Velkomst_frag.animation);
            status = (TextView) rod.findViewById(R.id.ord);
            hs = (TextView) rod.findViewById(R.id.ord3);
            tabt = (TextView) rod.findViewById(R.id.ordetVar);
            highscore = (Button) rod.findViewById(R.id.highscoreButton);
            highscore.setOnClickListener(this);


            if (SingleTon.getGlInstance().erSpilletTabt()) {
                if(Galgelogik.network){
                    SingleTon.lyd = MediaPlayer.create(this.getContext(), R.raw.sur);
                    SingleTon.lyd.start();
                    tabt.setText("Ordet var: " + SingleTon.getGlInstance().getOrdet() );
                    status.setText("Du har tabt!");
                    hs.setText("Score: " + Long.toString(SingleTon.getGlInstance().getScore()));
                    highscore.setText("Se highscore");
                } else {
                    SingleTon.lyd = MediaPlayer.create(this.getContext(), R.raw.sur);
                    SingleTon.lyd.start();
                    tabt.setText("Ordet var: " + SingleTon.getGlInstance().getOrdet() );
                    status.setText("Du har tabt!");
                    hs.setText("Score: " + Long.toString(SingleTon.getGlInstance().getScore()));
                    highscore.setVisibility(View.INVISIBLE);
                }
            } else if (SingleTon.getGlInstance().erSpilletVundet()) {
                if(Galgelogik.network){
                    if(Galgelogik.inHighscore()==true) {
                        SingleTon.lyd = MediaPlayer.create(this.getContext(), R.raw.glad);
                        SingleTon.lyd.start();
                        tabt.setText("Du gættede: " +SingleTon.getGlInstance().getOrdet() );
                        status.setText("Du har vundet!");
                        hs.setText("Score: " + Long.toString(SingleTon.getGlInstance().getScore()));
                        highscore.setText("Gem highscore");
                    } else{
                        SingleTon.lyd = MediaPlayer.create(this.getContext(), R.raw.glad);
                        SingleTon.lyd.start();
                        tabt.setText("Du gættede: " + SingleTon.getGlInstance().getOrdet() );
                        status.setText("Du har vundet!");
                        hs.setText("Score: " + Long.toString(SingleTon.getGlInstance().getScore()));
                        highscore.setText("Se highscore");
                    }
                } else {
                    SingleTon.lyd = MediaPlayer.create(this.getContext(), R.raw.glad);
                    SingleTon.lyd.start();
                    tabt.setText("Du gættede: " + SingleTon.getGlInstance().getOrdet() );
                    status.setText("Du har vundet!");
                    hs.setText("Score: " + Long.toString(SingleTon.getGlInstance().getScore()));
                    highscore.setVisibility(View.INVISIBLE);
                }
            }


        return rod;
    }

    @Override
    public void onClick(View v) {
        if (v==again){
//            SingleTon.gl.nulstil();
            getFragmentManager().popBackStack();

    } else if (v==highscore){
            if(SingleTon.getGlInstance().erSpilletVundet() && Galgelogik.inHighscore()) {
                SingleTon.tempHighscore("");
                getFragmentManager().popBackStack();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragmentindhold, new highscore_cont())
                            .addToBackStack(null)
                            .commit();
            } else {
                getFragmentManager().popBackStack();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new highscore_view())
                        .addToBackStack(null)
                        .commit();
            }
        }
    }


}
