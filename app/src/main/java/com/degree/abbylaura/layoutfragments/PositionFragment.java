package com.degree.abbylaura.layoutfragments;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ListView;
import android.content.Intent;

/**
 * Created by abbylaura on 03/02/2018.
 *
 * Going to be a listView so will extend listFragment
 */

public class PositionFragment extends ListFragment {

    boolean mDuelPane; //monitor whether we are portrait or landscape
    int mCurCheckPosition = 0; //monitor currently selected item in listView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> connectArrayToListView = new
                ArrayAdapter<String>(getActivity(), //pass context: getActivity
                android.R.layout.simple_list_item_activated_1, //pass default listView item to put data inside of
                RugbyPosInfo.POSITIONS); //pass the actual data for array

        //connect listview to adapter
        setListAdapter(connectArrayToListView);

        //check framelayout with id descriptions actually exists
        View descriptionsFrame = getActivity().findViewById(R.id.descriptions);

        //set value for mDuelPane based off whether we are in portrait or landscape mode
        mDuelPane = descriptionsFrame != null && descriptionsFrame.getVisibility()
                == View.VISIBLE;

        //savedInstanceState - anytime screen is rotated or activity closed, key value pairs are stored
        if(savedInstanceState != null){
            mCurCheckPosition = savedInstanceState.getInt("curChoice, 0");
        }

        if(mDuelPane){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mCurCheckPosition);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        //automatically called by android whenever rotation changes or activity is force-closed
        super.onSaveInstanceState(outState);

        outState.putInt("curChoice", mCurCheckPosition);

    }

    //change pos description data everytime element selected in listview

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // *don't need this line* super.onListItemClick(l, v, position, id);

        showDetails(position);
    }

    void showDetails(int index){
        //index represents most recently clicked on listview item
        //provide reference to description data we want to display

        mCurCheckPosition = index;

        if(mDuelPane){ //if this is true then we are in HORIZONTAL/LANDSCAPE mode
            getListView().setItemChecked(index, true);

            DescriptionFragment description = (DescriptionFragment)
                    getFragmentManager().findFragmentById(R.id.descriptions);

            if(description == null || description.getShownIndex() != index){
                //when description fragment is actually create, the index of the data
                //...is supposed to be shown on screen, so if hasnt been assigned we need to do it

                description = DescriptionFragment.newInstance(index);

                FragmentTransaction ft =
                        getFragmentManager().beginTransaction();

                //replace any other fragment with our proper description fragment
                ft.replace(R.id.descriptions, description);

                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                ft.commit();
            }
        } else { //when we are in VERTICAL/PORTRAIT mode

            Intent intent = new Intent();

            intent.setClass(getActivity(), DescriptionActivity.class);

            intent.putExtra("index", index);

            startActivity(intent);

        }
    }
}




