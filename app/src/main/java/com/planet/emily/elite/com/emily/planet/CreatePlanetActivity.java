package com.planet.emily.elite.com.emily.planet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.planet.emily.elite.R;

import butterknife.ButterKnife;

public class CreatePlanetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_planet);
        ButterKnife.bind(this);
    }


}
