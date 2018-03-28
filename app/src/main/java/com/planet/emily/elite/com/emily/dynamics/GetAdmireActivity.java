package com.planet.emily.elite.com.emily.dynamics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.planet.emily.elite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetAdmireActivity extends AppCompatActivity {

    @BindView(R.id.tv_back_about)
    TextView tv_back_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_admire);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_back_about)
    public void onBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
