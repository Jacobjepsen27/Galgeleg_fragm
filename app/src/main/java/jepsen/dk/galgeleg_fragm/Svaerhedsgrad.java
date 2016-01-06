package jepsen.dk.galgeleg_fragm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Svaerhedsgrad extends Fragment {

    private ViewGroup rod;

    ListView listView;
    ArrayAdapter<String> adapter;
    String[] valg = {"Let", "Middel", "Svær"};

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rod = (ViewGroup) i.inflate(R.layout.fragment_svaerhedsgrad, container, false);

        listView = (ListView) rod.findViewById(R.id.difView);
        //listView = getFragmentManager().findFragmentById(R.id.difView);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,valg);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(getContext(), "Sværhedsgrad sat til let", Toast.LENGTH_SHORT).show();

                } else if (position == 1) {
                    Toast.makeText(getContext(), "Sværhedsgrad sat til middel", Toast.LENGTH_SHORT).show();

                } else if (position == 2) {
                    Toast.makeText(getContext(), "Sværhedsgrad sat til svær", Toast.LENGTH_SHORT).show();
                }
                SingleTon.getGlInstance().saetsvaerhedsgrad(position + 1);

                getFragmentManager().popBackStack();


                //getActivity().getSupportFragmentManager().beginTransaction()
                 //       .replace(R.id.fragmentindhold, new Velkomst_frag())
                   //     .addToBackStack(null)
                     //   .commit();
            }
        });

        return rod;
    }


}
