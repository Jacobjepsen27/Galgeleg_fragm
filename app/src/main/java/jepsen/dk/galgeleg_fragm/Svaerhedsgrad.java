package jepsen.dk.galgeleg_fragm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class Svaerhedsgrad extends Fragment implements View.OnClickListener {

    private ViewGroup rod;

    ListView listView;
    int position;
    Button button1, button2, button3;
    ArrayAdapter<String> adapter;
    String[] valg = {"Let", "Middel", "Svær"};


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

        //listView = getFragmentManager().findFragmentById(R.id.difView);
//        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,valg);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (view == button1) {
//                    Toast.makeText(getContext(), "Sværhedsgrad sat til let", Toast.LENGTH_SHORT).show();
//
//                } else if (view == button2) {
//                    Toast.makeText(getContext(), "Sværhedsgrad sat til middel", Toast.LENGTH_SHORT).show();
//
//                } else if (view == button3) {
//                    Toast.makeText(getContext(), "Sværhedsgrad sat til svær", Toast.LENGTH_SHORT).show();
//                }
//                SingleTon.getGlInstance().saetsvaerhedsgrad(position + 1);
//                SingleTon.getGlInstance().nulstil();
//                getFragmentManager().popBackStack();
//            }
//
//        });

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

}
