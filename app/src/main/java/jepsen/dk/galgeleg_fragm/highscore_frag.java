package jepsen.dk.galgeleg_fragm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class highscore_frag extends Fragment {

    private ViewGroup rod;
    private ListView left, right;

    ArrayAdapter<String> adapter1, adapter2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rod = (ViewGroup) inflater.inflate(R.layout.fragment_highscore_frag, container, false);

        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), R.layout.listview_egetlayout, R.id.listeelem_navn, SingleTon.navnShow) {
            public View getView(int position, View cachedView, ViewGroup parent){
                View view = super.getView(position, cachedView, parent);

                TextView score = (TextView) view.findViewById(R.id.listeelem_score);
                score.setText(SingleTon.scoreShow[position]);

                return view;
            }
        };

        ListView listView = (ListView) rod.findViewById(R.id.highViewLeft);
        listView.setAdapter(adapter);

//        left = (ListView) rod.findViewById(R.id.highViewLeft);
//        right = (ListView) rod.findViewById(R.id.highViewRight);
//
//
//
//
//        adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, SingleTon.navnShow);
//        left.setAdapter(adapter1);
//
//        adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, SingleTon.scoreShow);
//        right.setAdapter(adapter2);

        return rod;
    }

}
