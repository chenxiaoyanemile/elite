package com.planet.emily.elite.com.emily.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.planet.emily.elite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalletActivity extends AppCompatActivity {

    @BindView(R.id.tv_back_wallet)
    TextView tv_back_wallet;

    @BindView(R.id.tv_wallet_details)
    TextView tv_wallet_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_back_wallet)
    public void clickBack(){
        finish();
    }

    @OnClick(R.id.tv_wallet_details)
    public void clickDetails(){

    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
