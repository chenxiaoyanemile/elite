package com.planet.emily.elite.com.emily.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.planet.emily.elite.R;

import butterknife.ButterKnife;

public class MyPublishDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_publish_details);
        ButterKnife.bind(this);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyPublishDetailsActivity.this, PublishActivity.class);
        startActivity(intent);
        finish();
    }
}
