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

import com.netcircle.imageloader.gen.UserLoginDao;
import com.planet.emily.elite.R;
import com.planet.emily.elite.app.MyApplication;
import com.planet.emily.elite.com.emily.home.HomeActivity;
import com.planet.emily.elite.dao.UserLogin;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText input_name;
    private EditText input_password;
    private Button btn_login;
    private TextView tv_sign_up;

    private UserLoginDao mUserDao;

    private String username = "emily";
    private String password = "123456";

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        preferences = getSharedPreferences("isLogin", Context.MODE_PRIVATE);

        mUserDao = MyApplication.getInstances().getDaoSession().getUserLoginDao();
        getData();
        initEvent();
    }

    private void initView() {
        input_name = findViewById(R.id.input_name);
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
                if (isNotEmpty(username)) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    saveIsLogin();
                } else {
                    Toast.makeText(LoginActivity.this, "你未注册！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_signup:
                if (isNotEmpty(username)) {
                    Toast.makeText(LoginActivity.this, "你已注册！", Toast.LENGTH_SHORT).show();
                } else {
                    startAction();
                }
                break;
        }
    }

    public void getData() {
        List<UserLogin> users = mUserDao.loadAll();
        String userName = " ";
        if (users.size() != 0){
            for (int i = 0; i < users.size(); i++) {
                userName += users.get(i).getName()+",";
                username = users.get(i).getName();
                password = users.get(i).getPassword();
                Log.d("TAG",username);
            }
            if (userName.equals(null)) {
                Toast.makeText(LoginActivity.this, "请先注册！", Toast.LENGTH_SHORT).show();
            } else {
                input_name.setText(username);
                input_password.setText(password);
                Log.d("TAG",username);
            }
        }

    }

    private boolean isNotEmpty(String s) {
        return s != null && !s.equals("");
    }

    public void startAction() {
        Intent intent = new Intent(LoginActivity.this, RegisteredActivity.class);
        startActivity(intent);
    }

    private void saveIsLogin(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLogin", true);
        editor.apply();
    }

}
