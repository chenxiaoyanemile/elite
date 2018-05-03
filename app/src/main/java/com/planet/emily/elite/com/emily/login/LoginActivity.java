package com.planet.emily.elite.com.emily.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.UserInfo;
import com.planet.emily.elite.com.emily.home.HomeActivity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText input_number;
    private EditText input_password;
    private Button btn_login;
    private TextView tv_sign_up;


    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, "889470321947c301aff932fc7d9a9e64");
        initView();
        preferences = getSharedPreferences("isLogin", Context.MODE_PRIVATE);

        initEvent();
    }

    private void initView() {
        input_number = findViewById(R.id.input_number);
        input_password = findViewById(R.id.input_password);
        btn_login = findViewById(R.id.btn_login);
        tv_sign_up = findViewById(R.id.tv_signup);
    }


    private void initEvent() {
        btn_login.setOnClickListener(this);
        tv_sign_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String phone_number = input_number.getText().toString().trim();
                String password = input_password.getText().toString().trim();
                if (isNotEmpty(phone_number) && isNotEmpty(password)) {
                    Login(phone_number, password);
                } else if (isNotEmpty(password) && isEmpty(phone_number)) {
                    toast("请输入手机号码！");
                } else if (isNotEmpty(phone_number) && isEmpty(password)) {
                    toast("请输入密码！");

                }
                break;
            case R.id.tv_signup:
                startAction();
                break;
        }
    }


    public void Login(String phone, String psw) {
        UserInfo.loginByAccount(phone, psw, new LogInListener<UserInfo>() {
            @Override
            public void done(UserInfo user, BmobException e) {
                if (user != null) {
                    Log.d("Bmob", user.toString());
                    toast("登录成功！");
                    startHome();
                    saveIsLogin();
                } else {
                    toast("用户未注册！" + e.toString());
                }

            }
        });
    }

    private boolean isNotEmpty(String s) {
        return s != null && !s.equals("");
    }

    private boolean isEmpty(String s) {
        return !isNotEmpty(s);
    }

    private void toast(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void startAction() {
        Intent intent = new Intent(LoginActivity.this, RegisteredActivity.class);
        startActivity(intent);
        finish();
    }

    public void startHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveIsLogin() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLogin", true);
        editor.apply();
    }

}
