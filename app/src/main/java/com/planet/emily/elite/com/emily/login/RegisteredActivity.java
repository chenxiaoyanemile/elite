package com.planet.emily.elite.com.emily.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.app.MyConstants;
import com.planet.emily.elite.bean.UserInfo;
import com.planet.emily.elite.com.emily.base.TakePhotoDialog;
import com.planet.emily.elite.util.PhotoHelper;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import de.hdodenhof.circleimageview.CircleImageView;


public class RegisteredActivity extends AppCompatActivity {

    @BindView(R.id.et_input_name)
    EditText input_name;
    @BindView(R.id.et_input_password)
    EditText input_password;
    @BindView(R.id.et_input_number)
    EditText et_input_number;
    @BindView(R.id.btn_registered)
    Button btn_registered;
    @BindView(R.id.cv_take_photo)
    CircleImageView iv_photo;

    private String username;
    private String password;
    private String number;



    private TakePhotoDialog takePhotoDialog;
    private PhotoHelper photoHelper;
    private String takePhotoPath;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);
        Bmob.initialize(this, "889470321947c301aff932fc7d9a9e64");

        photoHelper = new PhotoHelper();

        getPermissions();

        btn_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = input_name.getText().toString().trim();
                password = input_password.getText().toString().trim();
                number = et_input_number.getText().toString().trim();

                if (isNotEmpty(username) && isNotEmpty(password) && isNotEmpty(number) && isNotEmpty(takePhotoPath)) {
                    savePhoto();
                } else {
                    if (isEmpty(username) && isNotEmpty(password) && isNotEmpty(number) && isNotEmpty(takePhotoPath)) {
                        toast("姓名不能为空！");
                    }
                    if (isEmpty(password) && isNotEmpty(username) && isNotEmpty(number) && isNotEmpty(takePhotoPath)) {
                        toast("密码不能为空！");
                    }
                    if (isEmpty(number) && isNotEmpty(password) && isNotEmpty(username) && isNotEmpty(takePhotoPath)) {
                        toast("电话号码不能为空！");
                    }
                    if (isEmpty(takePhotoPath) && isNotEmpty(password) && isNotEmpty(username) && isNotEmpty(number)) {
                        toast("请选择美美的头像哦！");
                    }

                }

            }
        });
    }

    public void getPermissions() {
        if (ContextCompat.checkSelfPermission(RegisteredActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(RegisteredActivity.this,
                    Manifest.permission.CAMERA)) {


            } else {

                ActivityCompat.requestPermissions(RegisteredActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                }
            }

        }
    }

    @OnClick(R.id.cv_take_photo)
    public void takePhotoDialog() {
        FragmentManager fm = getSupportFragmentManager();
        takePhotoDialog = (TakePhotoDialog) fm.findFragmentByTag("takePhoto");
        if (takePhotoDialog == null) {
            takePhotoDialog = TakePhotoDialog.newInstance(new TakePhotoDialog.Callback() {
                @Override
                public void takePhoto() {
                    Intent takePhotoIntent = photoHelper.getTakePhotoIntent();
                    if (takePhotoIntent != null) {
                        takePhotoPath = photoHelper.getPhotoPath();
                        startActivityForResult(takePhotoIntent, PhotoHelper.REQUEST_TAKE_PHOTO);
                    }
                    hideDialog();
                }

                @Override
                public void choosePhoto() {
                    Intent choosePhotoIntent = photoHelper.getChoosePhotoIntent();
                    if (choosePhotoIntent != null) {
                        startActivityForResult(choosePhotoIntent, PhotoHelper.REQUEST_CHOOSE_PHOTO);
                    }
                    hideDialog();
                }
            });

        }
        takePhotoDialog.show(fm, "takePhoto");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        hideDialog();
        if (requestCode == PhotoHelper.REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            takePhoto();
        } else if (requestCode == PhotoHelper.REQUEST_CHOOSE_PHOTO && resultCode == RESULT_OK) {
            choosePhoto(data.getData());
        }
    }

    public void takePhoto() {
        if (!TextUtils.isEmpty(takePhotoPath)) {
            showPhoto(takePhotoPath);
        }
    }

    public void choosePhoto(Uri data) {
        String takePhotoPath = photoHelper.getPathFromMediaUri(data, getBaseContext());
        if (!TextUtils.isEmpty(takePhotoPath)) {
            showPhoto(takePhotoPath);
        }
    }

    public void showPhoto(String photoPath) {
        if (TextUtils.isEmpty(photoPath)) {
            return;
        }
        Bitmap compressBitmap = PhotoHelper.compressPhoto(photoPath, MyConstants.DEFAULT_AVATAR_WIDTH, MyConstants.DEFAULT_AVATAR_HEIGHT);
        if (compressBitmap != null) {

            showPhotoWindow(compressBitmap);
        }
    }

    private void showPhotoWindow(Bitmap photoBitmap) {

        iv_photo.setImageBitmap(photoBitmap);
    }

    private void hideDialog() {
        if (takePhotoDialog != null) {
            takePhotoDialog.dismissAllowingStateLoss();
        }
    }

    public void savePhoto(){
        final BmobFile file = new BmobFile(new File(takePhotoPath));
        file.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null){
                    sendRegisterMsg(file);
                }

            }
        });
    }


    private void sendRegisterMsg(BmobFile file) {
        UserInfo user = new UserInfo();
        user.setUsername(username);
        user.setPassword(password);
        user.setMobilePhoneNumber(number);
        user.setPhoto(file);
        user.signUp(new SaveListener<UserInfo>() {
            @Override
            public void done(UserInfo s, BmobException e) {
                if (e == null) {
                    toast("注册成功!");
                    startAction();
                } else {
                    toast("注册失败" + e.toString());
                }
            }
        });

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (takePhotoPath != null) {
            outState.putString(PhotoHelper.TAKE_PHOTO_PATH, takePhotoPath);
        }
    }


    private void toast(String msg) {
        Toast.makeText(RegisteredActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    private void startAction() {
        Intent intent = new Intent(RegisteredActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    private boolean isNotEmpty(String s) {
        assert s != null;
        return !s.equals("");
    }


    private boolean isEmpty(String s) {
        return !isNotEmpty(s);
    }
}
