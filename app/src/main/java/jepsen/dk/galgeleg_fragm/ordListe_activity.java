package jepsen.dk.galgeleg_fragm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ordListe_activity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ord_liste_activity);

        adapter = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_list_item_1, SingleTon.getGlInstance().muligeOrd);

        listView = (ListView) findViewById(R.id.ordListe);
        listView.setAdapter(adapter);

    }
}
