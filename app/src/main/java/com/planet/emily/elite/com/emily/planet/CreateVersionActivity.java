package com.planet.emily.elite.com.emily.planet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import com.planet.emily.elite.R;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateVersionActivity extends AppCompatActivity {

    @BindView(R.id.et_input_version)
    EditText inputVersion;
    @BindView(R.id.spinner_status)
    Spinner status;
    @BindView(R.id.et_input_time)
    EditText inputTime;
    @BindView(R.id.et_description)
    EditText description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_version);
    }

    @OnClick(R.id.tv_version_submit)
    public void clickSubmit(){

    }
    @OnClick(R.id.tv_back_version)
    public void onBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
