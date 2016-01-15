package jepsen.dk.galgeleg_fragm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
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


public class side2_frag extends Fragment implements View.OnClickListener, SensorEventListener {


    private ImageView img;
    private TextView txt;
    private String bogstav;
    private ViewGroup rod;
    private int[] knap = {R.id.A, R.id.B, R.id.C, R.id.D, R.id.E, R.id.F, R.id.G, R.id.H, R.id.I, R.id.J, R.id.K, R.id.L,
            R.id.M, R.id.N, R.id.O, R.id.P, R.id.Q, R.id.R, R.id.S, R.id.T, R.id.U, R.id.V, R.id.W, R.id.X, R.id.Y, R.id.Z, R.id.Æ, R.id.Ø, R.id.Å};
    Button btn;
    private long lastShaken = System.currentTimeMillis();
    private long cTime;


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {

        rod = (ViewGroup) i.inflate(R.layout.side2_frag, container, false);



        if (savedInstanceState == null) {
            img = (ImageView) rod.findViewById(R.id.mainImgImageView);
            txt = (TextView) rod.findViewById(R.id.ordTextView);
            txt.setText(SingleTon.getGlInstance().getSynligtOrd());
            for (int j = 0; j < knap.length; j++) {
                btn = (Button) rod.findViewById(knap[j]);
                btn.setOnClickListener(this);
            }

            SingleTon.sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
            SingleTon.accelerometer = SingleTon.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            SingleTon.sensorManager.registerListener(this, SingleTon.accelerometer, SensorManager.SENSOR_DELAY_UI);
            SingleTon.vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            lastShaken = System.currentTimeMillis();
            Log.d("TID1", Long.toString(lastShaken));
            Log.d("Sensormanager", "sensor aktiveret");

        }
        return rod;
    }

    @Override
    public void onClick(View v) {

        if(v instanceof Button){
           bogstav = ((Button) v).getText().toString().toLowerCase();
            SingleTon.vibe.vibrate(50);
            v.setSelected(true);
            v.setEnabled(false);
    }

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


    @Override
    public void onSensorChanged(SensorEvent e) {
        double g=9.80665; // normal tyngdeaccelerationen
        double sum=Math.abs(e.values[0])+Math.abs(e.values[1])+Math.abs(e.values[2]);
        cTime = System.currentTimeMillis();
        Log.d("TID2", Long.toString(cTime));
        if(sum>3*g && cTime-lastShaken> 2000){
            lastShaken = cTime;
            SingleTon.vibe.vibrate(300);
            getFragmentManager().popBackStack();
            SingleTon.getGlInstance().nulstil();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new side2_frag())
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onDestroy() {
        System.out.println("OnDestroy() i side2 frag");
        SingleTon.sensorManager.unregisterListener(this, SingleTon.accelerometer);
        super.onDestroy();

    }
}
