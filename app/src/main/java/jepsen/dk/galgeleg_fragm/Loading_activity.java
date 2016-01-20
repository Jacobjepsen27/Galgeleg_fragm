package jepsen.dk.galgeleg_fragm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Loading_activity extends AppCompatActivity {

    private ProgressBar spinner;
    private SharedPreferences sp;
    private boolean firstStartUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_activity);

        if(savedInstanceState==null) {
            System.out.println("FØRSTE");

            sp = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
            firstStartUp = sp.getBoolean("startUp", true);
            System.out.println(firstStartUp);
            System.out.println(Galgelogik.network);
            //Hvis der ikke er netværk skal bruger promptes
            if (firstStartUp) {
                if(Galgelogik.network) {
                    System.out.println("FØRSTE2");
                    spinner = (ProgressBar) findViewById(R.id.progressBar);
                    spinner.setVisibility(View.VISIBLE);

                    final Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (SingleTon.doneLoading) {
                                h.removeCallbacks(this);
                                finish();
                                Intent intent = new Intent(Loading_activity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                h.postDelayed(this, 100);
                            }
                        }
                    }, 100);
                } else {
                    finish();
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                }
            } else if(!firstStartUp){
                System.out.println("FØRSTE3");
                finish();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
