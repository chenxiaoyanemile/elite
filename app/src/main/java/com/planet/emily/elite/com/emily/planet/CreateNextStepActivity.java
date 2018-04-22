package com.planet.emily.elite.com.emily.planet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.planet.emily.elite.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateNextStepActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_next_step);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_create_finish)
    public void createPlanet() {
        //TODO
    }

    @OnClick(R.id.tv_back_home)
    public void onBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
