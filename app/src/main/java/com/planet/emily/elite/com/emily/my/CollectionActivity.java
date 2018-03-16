package com.planet.emily.elite.com.emily.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.planet.emily.elite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectionActivity extends AppCompatActivity {

    @BindView(R.id.tv_back_collection) TextView tv_back_collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_back_collection)
    public void onBack(){
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
