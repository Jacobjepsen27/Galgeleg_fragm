package jepsen.dk.galgeleg_fragm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class highscore_temp extends Fragment {

    private ViewGroup rod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rod = (ViewGroup) inflater.inflate(R.layout.fragment_highscore_temp, container, false);

        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), R.layout.listview_egetlayout, R.id.listeelem_navn, SingleTon.tempNavnShow) {
            public View getView(int position, View cachedView, ViewGroup parent){
                View view = super.getView(position, cachedView, parent);

                TextView score = (TextView) view.findViewById(R.id.listeelem_score);
                score.setText(SingleTon.tempScoreShow[position]);

                return view;
            }
        };

        ListView listView = (ListView) rod.findViewById(R.id.highViewLeft);
        listView.setAdapter(adapter);

        return rod;
    }

}
