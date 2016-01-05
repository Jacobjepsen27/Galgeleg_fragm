package jepsen.dk.galgeleg_fragm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class highscore_frag extends Fragment {

    private ViewGroup rod;
    private ListView left, right;

    ArrayAdapter<String> adapter1, adapter2;

    String[] navn;
    String[] score;
    Integer[] scoreInt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rod = (ViewGroup) inflater.inflate(R.layout.fragment_highscore_frag, container, false);

        left = (ListView) rod.findViewById(R.id.highViewLeft);
        right = (ListView) rod.findViewById(R.id.highViewRight);


        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("HiScore");
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    scoreInt = new Integer[scoreList.size()];
                    score = new String[scoreList.size()];
                    navn = new String[scoreList.size()];

                    for (int i = 0; scoreList.size() > i; i++) {
                        scoreInt[i] = scoreList.get(i).getInt("score");
                        navn[i] = scoreList.get(i).getString("navn");
                    }

//                    boolean swapped = true;
//                    int j = 0;
//                    int tmpI;
//                    String tmpS;
//                    while (swapped) {
//                        swapped = false;
//                        j++;
//                        for (int i = 0; i < scoreInt.length - j; i++) {
//                            if (scoreInt[i] < scoreInt[i + 1]) {
//                                tmpI = scoreInt[i+1];
//                                tmpS = navn[i+1];
//                                scoreInt[i+1] = scoreInt[i];
//                                navn[i+1] = navn[i];
//                                scoreInt[i] = tmpI;
//                                navn[i] = tmpS;
//                                swapped = true;
//                            }
//                        }
//                    }

                    for (int i = 0; scoreInt.length > i; i++) {
                        score[i] = scoreInt[i].toString();
                    }

                    String [] navnShow = {navn[0],navn[1],navn[2],navn[3],navn[4],navn[5]};
                    String [] scoreShow = {score[0],score[1],score[2],score[3],score[4],score[5]};

                    adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, navnShow);
                    left.setAdapter(adapter1);

                    adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, scoreShow);
                    right.setAdapter(adapter2);

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        return rod;
    }

}
