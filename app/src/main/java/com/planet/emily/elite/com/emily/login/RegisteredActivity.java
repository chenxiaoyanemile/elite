package com.planet.emily.elite.com.emily.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.dao.UserLogin;

import org.greenrobot.eventbus.EventBus;


public class RegisteredActivity extends AppCompatActivity {

    private EditText input_name;
    private EditText input_password;
    private Button btn_registered;

    private String username;
    private String password;

    private UserLogin mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        initView();
        mUser = new UserLogin();

        btn_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = input_name.getText().toString();
                password = input_password.getText().toString();

                if (isNotEmpty(username) && isNotEmpty(password)) {
                    mUser.setName(username);
                    mUser.setPassword(password);

                    Toast.makeText(RegisteredActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post("你好啊？");
                    startAction();
                } else {
                    if (isEmpty(username) && isNotEmpty(password)) {
                        Toast.makeText(RegisteredActivity.this, "姓名为空", Toast.LENGTH_SHORT).show();
                    }
                    if (isEmpty(password) && isNotEmpty(username)) {
                        Toast.makeText(RegisteredActivity.this, "密码为空", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }

    private void initView() {
        input_name = findViewById(R.id.et_input_name);
        input_password = findViewById(R.id.et_input_password);
        btn_registered = findViewById(R.id.btn_registered);

    }


    private void startAction() {
        Intent intent = new Intent(RegisteredActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * is not null
     *
     * @param s
     * @return
     */
    private boolean isNotEmpty(String s) {
        if (s != null && !s.equals("") || s.length() > 0) {
            return true;
        } else {
            return false;
        }
    }


    private boolean isEmpty(String s) {
        if (isNotEmpty(s)) {
            return false;
        } else {
            return true;
        }
    }
}
