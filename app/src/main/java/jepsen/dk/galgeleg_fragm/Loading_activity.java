package jepsen.dk.galgeleg_fragm;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Loading_activity extends AppCompatActivity {

    private ProgressBar spinner;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_activity);

        //img = (ImageView) findViewById(R.id.loadingImageView);
        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SingleTon.doneLoading) {
                    h.removeCallbacks(this);
                    finish();
                } else {
                    h.postDelayed(this, 100);
                }
            }
        }, 100);

    }
}
