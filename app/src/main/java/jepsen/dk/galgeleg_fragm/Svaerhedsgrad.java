package jepsen.dk.galgeleg_fragm;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class Svaerhedsgrad extends Fragment implements View.OnClickListener, Runnable {

    private ViewGroup rod;

    ListView listView;
    int position;
    Button button1, button2, button3;
    ArrayAdapter<String> adapter;
    String[] valg = {"Let", "Middel", "Svær"};
    private ImageView galgeImg;
    private Handler handler = new Handler();
    private int count;
    private boolean countMode;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rod = (ViewGroup) i.inflate(R.layout.fragment_svaerhedsgrad, container, false);


        button1 = (Button) rod.findViewById(R.id.knapLet);
        button1.setOnClickListener(this);
        button2 = (Button) rod.findViewById(R.id.knapMedium);
        button2.setOnClickListener(this);
        button3 = (Button) rod.findViewById(R.id.knapSvaer);
        button3.setOnClickListener(this);

        galgeImg = (ImageView) rod.findViewById(R.id.galgeImageView);
        galgeImg.setImageResource(R.drawable.forkert4);
        count = -1;
        countMode = true;
        return rod;
    }
    public void onClick(View view) {
        if (view == button1) {
            Toast.makeText(getContext(), "Sværhedsgrad sat til let", Toast.LENGTH_SHORT).show();
            position = 1;

        } else if (view == button2) {
            Toast.makeText(getContext(), "Sværhedsgrad sat til middel", Toast.LENGTH_SHORT).show();
            position = 2;

        } else if (view == button3) {
            Toast.makeText(getContext(), "Sværhedsgrad sat til svær", Toast.LENGTH_SHORT).show();
            position = 3;
        }
        SingleTon.getGlInstance().saetsvaerhedsgrad(position);
        SingleTon.getGlInstance().nulstil();
        getFragmentManager().popBackStack();
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
