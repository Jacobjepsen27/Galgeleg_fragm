package jepsen.dk.galgeleg_fragm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ordListe_activity extends AppCompatActivity{

    private ListView listView;
    private ArrayAdapter<String> adapter;
    public static int pos;
    public String rettetOrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ord_liste_activity);

        adapter = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_list_item_1, SingleTon.getGlInstance().muligeOrd);

        listView = (ListView) findViewById(R.id.ordListe);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "LAAAAANGT KLIK", Toast.LENGTH_SHORT).show();
                pos = position;
                openContextMenu(listView);
                return true;
            }
        });
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);

//        if (v.getId()==R.id.ordListe){
//            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
//            menu.setHeaderTitle("Redigering");
//            menu.add(1, 1, 1, "Ret");
//            menu.add(1,2,1,"Slet");
//            menu.add("Ret");
//            menu.add("Slet");
//        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        AlertDialog.Builder builder = new AlertDialog.Builder(ordListe_activity.this);
        if(item.getItemId()==R.id.ord_ret){
            Toast.makeText(getBaseContext(), "Ret "+pos, Toast.LENGTH_SHORT).show();
            builder.setTitle("Ret ord");
            final EditText input = new EditText(ordListe_activity.this);
            input.setText(SingleTon.getGlInstance().muligeOrd.get(pos).toString());
            builder.setView(input);
            builder.setNegativeButton("Fortryd", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.notifyDataSetChanged();
                }
            });
            builder.setPositiveButton("Bekræft", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    rettetOrd = input.getText().toString();
                    SingleTon.getGlInstance().muligeOrd.set(pos,rettetOrd);
                    adapter.notifyDataSetChanged();
                }
            });
            builder.show();
        } else if(item.getItemId()==R.id.ord_slet){
            Toast.makeText(getBaseContext(), "Slet "+pos, Toast.LENGTH_SHORT).show();
            builder.setTitle("Slet ord");
            builder.setMessage("Ordet: " + "'" + SingleTon.getGlInstance().muligeOrd.get(pos).toString() + "'" + " slettes permanent");
            builder.setNegativeButton("Fortryd", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.notifyDataSetChanged();
                }
            });
            builder.setPositiveButton("Bekræft", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SingleTon.getGlInstance().muligeOrd.remove(pos);
                    adapter.notifyDataSetChanged();
                }
            });
            builder.show();
        }
        return super.onContextItemSelected(item);
    }
}
