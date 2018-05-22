package com.planet.emily.elite.com.emily.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.app.MyConstants;
import com.planet.emily.elite.bean.UserInfo;
import com.planet.emily.elite.com.emily.home.HomeActivity;
import com.planet.emily.elite.util.PhotoHelper;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText input_number;
    private EditText input_password;
    private Button btn_login;
    private TextView tv_sign_up;
    private CircleImageView cv_user_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, "889470321947c301aff932fc7d9a9e64");
        initView();
        initEvent();
    }

    private void initView() {
        Intent intent = getIntent();
        String takePhotoPath = intent.getStringExtra("takePhotoPath");
        String password = intent.getStringExtra("password");
        String number = intent.getStringExtra("number");

        input_number = findViewById(R.id.input_number);
        input_password = findViewById(R.id.input_password);
        btn_login = findViewById(R.id.btn_login);
        tv_sign_up = findViewById(R.id.tv_signup);
        cv_user_image = findViewById(R.id.cv_user_image);

        showPhoto(takePhotoPath);
        input_number.setText(number);
        input_password.setText(password);

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
                    toast("登录成功！" + user.getObjectId());
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

    }


    public void showPhoto(String photoPath) {
        if (TextUtils.isEmpty(photoPath)) {
            return;
        }
        Bitmap compressBitmap = PhotoHelper.compressPhoto(photoPath, MyConstants.DEFAULT_AVATAR_WIDTH, MyConstants.DEFAULT_AVATAR_HEIGHT);
        if (compressBitmap != null) {
            cv_user_image.setImageBitmap(compressBitmap);
        }
    }


}
