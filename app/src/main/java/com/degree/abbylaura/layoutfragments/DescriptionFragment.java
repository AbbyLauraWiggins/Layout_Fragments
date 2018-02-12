package com.degree.abbylaura.layoutfragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created by abbylaura on 05/02/2018.
 */

public class DescriptionFragment extends Fragment{

    public static DescriptionFragment newInstance(int index){

        DescriptionFragment f = new DescriptionFragment();

        //bundles used to pass data
        Bundle args = new Bundle();
        args.putInt("index", index);

        //assign key fragment
        f.setArguments(args);

        return f;
    }

    public int getShownIndex(){
        return getArguments().getInt("index", 0);


    }

    //handle oncreateview where the layout inflator will put fragmnet on the screen

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        //create scroll view
        ScrollView scroller = new ScrollView(getActivity());

        TextView text = new TextView(getActivity());

        //apply padding to text view
        int padding = (int)
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());

        text.setPadding(padding, padding, padding, padding);

        scroller.addView(text);

        text.setText(RugbyPosInfo.DESCRIPTIONS[getShownIndex()]);

        return scroller;

    }


}

