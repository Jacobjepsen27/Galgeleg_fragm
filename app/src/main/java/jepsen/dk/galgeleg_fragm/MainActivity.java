package jepsen.dk.galgeleg_fragm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        System.out.println("MAIN");

        //Hvis der ikke er netværk skal bruger promptes
        if(!Galgelogik.network){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Netværksforbindelse");
            builder.setMessage("Der er ingen netværksforbindelse til rådighed. Alle funktioner vil ikke være tilgængelige.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Programmet fortsættes
                }
            });
            builder.show();
        }
//        if(firstStartUp && Galgelogik.network){
//            Intent loading = new Intent(this, Loading_activity.class);
//            startActivity(loading);
//        }
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
            Intent ordliste = new Intent(this,ordListe_activity.class);
            startActivity(ordliste);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
