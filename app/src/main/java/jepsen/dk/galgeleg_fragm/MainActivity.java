package jepsen.dk.galgeleg_fragm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Toolbar toolbar;
    private ProgressBar spinner;
    private SharedPreferences sp;
    private boolean firstStartUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        sp = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
        firstStartUp = sp.getBoolean("startUp", true);
        System.out.println(firstStartUp);
        if(firstStartUp && Galgelogik.network){
            Intent loading = new Intent(this, Loading_activity.class);
            startActivity(loading);
        }
        System.out.println("BLABLAKBKABAA");
        //Hvis der ikke er netværk skal bruger promptes
        if(!Galgelogik.network){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Netværksforbindelse");
            builder.setMessage("Der er ingen netværksforbindelse til rådighed. Diverse funktioner vil ikke være til rådighed.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Programmet fortsættes
                }
            });
            builder.show();
        }

        if(savedInstanceState == null){
            Fragment fragment = new Velkomst_frag();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentindhold, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_ordliste) {
            Toast.makeText(getBaseContext(), "Ordliste valgt", Toast.LENGTH_SHORT).show();
            Intent ordliste = new Intent(this,ordListe_activity.class);
            startActivity(ordliste);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    private void checkNetworkConnectionAvailable() {
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo info = cm.getActiveNetworkInfo();
//        if (info == null){
//            Galgelogik.network=false;
//        } else {
//            Galgelogik.network=true;
//        }
//        NetworkInfo.State network = info.getState();
//        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
}
