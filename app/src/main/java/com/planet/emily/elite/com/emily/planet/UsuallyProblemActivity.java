package com.planet.emily.elite.com.emily.planet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.planet.emily.elite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsuallyProblemActivity extends AppCompatActivity {

    @BindView(R.id.tv_back_collection)
    TextView tv_back_collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usually_problem);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_back_collection)
    public void onBack() {
        Intent intent = new Intent(UsuallyProblemActivity.this, CreatePlanetActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UsuallyProblemActivity.this, CreatePlanetActivity.class);
        startActivity(intent);
        finish();
    }
}
