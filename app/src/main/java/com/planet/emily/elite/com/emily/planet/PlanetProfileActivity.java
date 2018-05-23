package com.planet.emily.elite.com.emily.planet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.planet.emily.elite.R;

import butterknife.ButterKnife;

public class PlanetProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_profile);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PlanetProfileActivity.this, PlanetActivity.class);
        startActivity(intent);
        finish();
    }
}
