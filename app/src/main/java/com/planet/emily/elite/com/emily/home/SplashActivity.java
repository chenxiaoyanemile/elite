package com.planet.emily.elite.com.emily.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.planet.emily.elite.R;
import com.planet.emily.elite.com.emily.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {


    private static final int SPLASH_DELAY = 2000;

    private final Handler mHandler = new Handler();
    private final Launcher mLauncher = new Launcher();

    private SharedPreferences.Editor editor;

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.postDelayed(mLauncher, SPLASH_DELAY);
    }


    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences sharedPreferences = getSharedPreferences("LoginInfo", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    protected void onStop() {
        mHandler.removeCallbacks(mLauncher);
        super.onStop();
    }

    private void startAction() {
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void startLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        saveIsLogin();
        finish();
    }


    private void saveIsLogin() {
        editor.putInt("counter", 1);
        editor.apply();

    }

    private int getUserData() {
        SharedPreferences preferences = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        return preferences.getInt("counter", -1);


    }

    private void launch() {
        if (!isFinishing()) {
            if (getUserData() == 1) {
                startAction();
            } else {
                startLogin();
            }
        }
    }

    private class Launcher implements Runnable {
        @Override
        public void run() {
            launch();
        }
    }
}
