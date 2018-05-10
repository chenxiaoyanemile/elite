package com.planet.emily.elite.com.emily.my;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.planet.emily.elite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpActivity extends AppCompatActivity {

    @BindView(R.id.et_input_feedback)
    EditText et_input_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_back_help)
    public void clickBack() {
        finish();
    }

    @OnClick(R.id.tv_help_submit)
    public void clickSubmit() {
        String feedbackText = et_input_feedback.getText().toString().trim();
        if (feedbackText.isEmpty()){
            toast("请输入内容！");
        }
        else {
            toast("提交成功！");
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void toast(String msg) {
        Toast.makeText(HelpActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

}
