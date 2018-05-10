package com.planet.emily.elite.com.emily.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.planet.emily.elite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends AppCompatActivity {

    @BindView(R.id.tv_cache_size)
    TextView tv_cache_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.ly_setting)
    public void clickSetting() {

    }


    @OnClick(R.id.ly_set_clean)
    public void clickClean() {


    }

    @OnClick(R.id.ly_set_version)
    public void clickVersion() {

    }

    @OnClick(R.id.ly_set_origin)
    public void clickOrigin() {
        Intent intent = new Intent(SetActivity.this, OriginActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_logout)
    public void clickLogout(){

    }

    @OnClick(R.id.tv_back_set)
    public void clickBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
