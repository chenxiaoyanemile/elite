package com.planet.emily.elite.com.emily.planet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.planet.emily.elite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateShareActivity extends AppCompatActivity {

    @BindView(R.id.et_content)
    EditText shareContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_share);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_share_submit)
    public void clickSubmit(){
        String text = shareContent.getText().toString().trim();
        if (!text.isEmpty()){
            //TODO
        }
        else {
            Toast.makeText(this, "提交内容不能为空！", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.tv_back_share)
    public void onBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
