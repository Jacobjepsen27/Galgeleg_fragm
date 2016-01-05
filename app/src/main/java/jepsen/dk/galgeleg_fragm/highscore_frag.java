package jepsen.dk.galgeleg_fragm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class highscore_frag extends Fragment {

    private ViewGroup rod;
    private ListView left, right;

    ArrayAdapter<String> adapter1, adapter2;
    String[] valg = {"Let", "Middel", "Svær"};
    String[] valg2 = {"kat", "hund", "rotte"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rod = (ViewGroup) inflater.inflate(R.layout.fragment_highscore_frag, container, false);

        left = (ListView) rod.findViewById(R.id.highViewLeft);
        right = (ListView) rod.findViewById(R.id.highViewRight);

        adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,valg);
        left.setAdapter(adapter1);

        adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,valg2);
        right.setAdapter(adapter2);



        return rod;
    }

}
