package com.planet.emily.elite.com.emily.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.planet.emily.elite.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlanetSortingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_sorting);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.tv_back_rank)
    public void onBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
