package com.gs.h2oapp.Action;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gs.h2oapp.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //Get History Locations on DataBase and Search shop by the first location;
        /*
         Case null try get Location by GPS, if this disabled show message to user enabled;
         If enable get Location and save in DataBase and get Shop by this Location;
         Else show display where user enter with address, system get address Location search
         Shops with this Location, and save this Location in DataBase*/
    }
}
