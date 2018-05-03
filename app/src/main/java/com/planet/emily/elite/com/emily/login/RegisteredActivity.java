package com.planet.emily.elite.com.emily.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.planet.emily.elite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
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


    BmobUser user = new BmobUser();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);
        Bmob.initialize(this, "889470321947c301aff932fc7d9a9e64");



        btn_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = input_name.getText().toString().trim();
                password = input_password.getText().toString().trim();
                number = et_input_number.getText().toString().trim();

                if (isNotEmpty(username) && isNotEmpty(password) && isNotEmpty(number)) {

                    sendRegisterMsg();

                } else {
                    if (isEmpty(username) && isNotEmpty(password) && isNotEmpty(number)) {
                        Toast.makeText(RegisteredActivity.this, "姓名不能为空！", Toast.LENGTH_SHORT).show();
                    }
                    if (isEmpty(password) && isNotEmpty(username) && isNotEmpty(number)) {
                        Toast.makeText(RegisteredActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                    }
                    if (isEmpty(number) && isNotEmpty(password) && isNotEmpty(username)) {
                        Toast.makeText(RegisteredActivity.this, "电话号码不能为空！", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }

/*
    @OnClick(R.id.cv_take_photo)
    public void takePhoto() {
        FragmentManager fm = getSupportFragmentManager();
        takePhotoDialog = (TakePhotoDialog) fm.findFragmentByTag("takePhoto");
        if (takePhotoDialog == null) {
            takePhotoDialog = TakePhotoDialog.newInstance(new TakePhotoDialog.Callback() {
                @Override
                public void takePhoto() {

                    rxPermissions
                            .request(Manifest.permission.CAMERA)
                            .subscribe(new Subscriber<Boolean>() {
                                @Override
                                public void onCompleted() {
                                    Intent takePhotoIntent = photoHelper.getTakePhotoIntent();
                                    if (takePhotoIntent != null) {
                                        takePhotoPath = photoHelper.getPhotoPath();
                                        startActivityForResult(takePhotoIntent, PhotoHelper.REQUEST_TAKE_PHOTO);
                                    }
                                    hideDialog();
                                }

                                @Override
                                public void onError(Throwable e) {

                                    showPhotoAccessDialog = true;

                                }

                                @Override
                                public void onNext(Boolean granted) {
                                    if (granted) {
                                        // All requested permissions are granted
                                    } else {
                                        // At least one permission is denied
                                    }
                                }
                            });

                }

                @Override
                public void choosePhoto() {
                    rxPermissions
                            .request(Manifest.permission.CAMERA)
                            .subscribe(new Subscriber<Boolean>() {
                                @Override
                                public void onCompleted() {
                                    Intent takePhotoIntent = photoHelper.getTakePhotoIntent();
                                    if (takePhotoIntent != null) {
                                        takePhotoPath = photoHelper.getPhotoPath();
                                        startActivityForResult(takePhotoIntent, PhotoHelper.REQUEST_TAKE_PHOTO);
                                    }
                                    hideDialog();
                                }

                                @Override
                                public void onError(Throwable e) {

                                    showPhotoAccessDialog = true;

                                }

                                @Override
                                public void onNext(Boolean granted) {
                                    if (granted) {
                                        // All requested permissions are granted
                                    } else {
                                        // At least one permission is denied
                                    }
                                }
                            });
                }
            });
        }
        takePhotoDialog.show(fm, "takePhoto");
    }*/

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        hideDialog();
        if (requestCode == PhotoHelper.REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            takePhoto();
        } else if (requestCode == PhotoHelper.REQUEST_CHOOSE_PHOTO && resultCode == RESULT_OK) {
            choosePhoto(data.getData());
        }
    }

    public void choosePhoto(Uri data) {
        takePhotoPath = photoHelper.getPathFromMediaUri(data, getBaseContext());
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
    }*/

    private void sendRegisterMsg() {
        user.setUsername(username);
        user.setPassword(password);
        user.setMobilePhoneNumber(number);
        user.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser s, BmobException e) {
                if (e == null) {
                    toast("注册成功!");
                    startAction();
                } else {
                    toast("注册失败" + e.toString());
                }
            }
        });

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
