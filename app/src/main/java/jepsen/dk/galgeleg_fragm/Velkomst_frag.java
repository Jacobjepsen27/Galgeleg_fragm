package jepsen.dk.galgeleg_fragm;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;

import com.parse.ParseObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class Velkomst_frag extends Fragment implements View.OnClickListener, Runnable {

    private Button start, difficult, seHS;
    private ImageView galgeImg;
    private Handler handler = new Handler();
    private int count;
    private boolean countMode;
    private Animation fadeIn, fadeOut;
    private ViewGroup rod;
    static AnimationSet animation;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container,Bundle savedInstanceState) {

        rod = (ViewGroup) i.inflate(R.layout.velkomst_frag, container, false);

        if(savedInstanceState == null) {
            difficult = (Button) rod.findViewById(R.id.difButton);
            difficult.setOnClickListener(this);

            seHS = (Button) rod.findViewById(R.id.HSbutton);
            seHS.setOnClickListener(this);

            start = (Button) rod.findViewById(R.id.startButton);
            galgeImg = (ImageView) rod.findViewById(R.id.galgeImageView);
            galgeImg.setImageResource(R.drawable.forkert4);
            count = -1;
            countMode = true;



            start.setOnClickListener(this);
        }
        return rod;
    }

    @Override
    public void onClick(View v) {
        if (v==start){
            count = -1;
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new side2_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (v==difficult){
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new Svaerhedsgrad())
                    .addToBackStack(null)
                    .commit();
        } else if(v==seHS){
            SingleTon.getGlInstance().setHScontext(true);
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, new highscore_view())
                .addToBackStack(null)
                .commit();

    }

    }


    @Override
    public void run() {
        switch (count){
            case -1:

                galgeImg.setImageResource(R.drawable.galge);
                break;
            case 0:
                galgeImg.setImageResource(R.drawable.forkert1);
                break;
            case 1:
                galgeImg.setImageResource(R.drawable.forkert2);
                break;
            case 2:
                galgeImg.setImageResource(R.drawable.forkert3);
                break;
            case 3:
                galgeImg.setImageResource(R.drawable.forkert4);
                break;
            case 4:
                galgeImg.setImageResource(R.drawable.forkert5);
                break;
            case 5:
                galgeImg.setImageResource(R.drawable.forkert6);
                break;
        }
        count();
        startRunning(500);
    }

    // Kører run efter "ms" milisekunder
    public void startRunning(int ms) {
        handler.postDelayed(this, ms);
    }

    public void count(){
        if (count<=-1){
            countMode = true;
        } else if (count>=5){
            countMode = false;
        }

        if (countMode)
            count++;
        else
            count--;
    }

    @Override
    public void onResume(){
        super.onResume();
        startRunning(500);
    }

    @Override
    public void onStop(){
        super.onStop();
        handler.removeCallbacks(this);
    }


}
