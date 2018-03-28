package com.planet.emily.elite.com.emily.my;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.planet.emily.elite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPublishDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_back_publish)
    TextView tv_back_publish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_publish_details);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_back_publish)
    public void onBack() {
        Intent intent = new Intent(MyPublishDetailsActivity.this,PublishActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyPublishDetailsActivity.this,PublishActivity.class);
        startActivity(intent);
        finish();
    }
}
