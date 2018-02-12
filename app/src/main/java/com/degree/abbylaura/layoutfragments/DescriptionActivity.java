package com.degree.abbylaura.layoutfragments;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by abbylaura on 05/02/2018.
 */

public class DescriptionActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //check if device is in landscape mode, and if so shut this activity
        if(getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }


        if(savedInstanceState == null){
            DescriptionFragment description = new DescriptionFragment();

            //get bundle of key value pairs
            description.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction().add(android.R.id.content,
                    description).commit();
        }
    }




}
