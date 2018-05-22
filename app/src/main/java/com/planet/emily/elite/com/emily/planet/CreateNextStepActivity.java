package com.planet.emily.elite.com.emily.planet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.app.MyConstants;
import com.planet.emily.elite.bean.PlanetInfo;
import com.planet.emily.elite.bean.UserInfo;
import com.planet.emily.elite.com.emily.base.TakePhotoDialog;
import com.planet.emily.elite.util.PhotoHelper;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class CreateNextStepActivity extends AppCompatActivity {

    @BindView(R.id.cv_planet_avatar)
    CircleImageView planetAvatar;
    @BindView(R.id.et_planet_name)
    EditText planetName;
    @BindView(R.id.et_description)
    EditText planetDescription;

    private String type;

    private TakePhotoDialog takePhotoDialog;
    private PhotoHelper photoHelper;
    private String takePhotoPath = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_next_step);
        ButterKnife.bind(this);
        photoHelper = new PhotoHelper();
    }


    @OnClick(R.id.cv_planet_avatar)
    public void clickAvatar() {
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

    private void hideDialog() {
        if (takePhotoDialog != null) {
            takePhotoDialog.dismissAllowingStateLoss();
        }
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
            planetAvatar.setImageBitmap(compressBitmap);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (takePhotoPath != null) {
            outState.putString(PhotoHelper.TAKE_PHOTO_PATH, takePhotoPath);
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rb_study:
                if (checked)
                    type = "科研";
                break;
            case R.id.rb_product:
                if (checked)
                    type = "产品";
                break;
            case R.id.rb_development:
                if (checked)
                    type = "研发";
                break;
            case R.id.rb_algorithm:
                if (checked)
                    type = "算法";
                break;
            case R.id.rb_network:
                if (checked)
                    type = "网络";
                break;
        }
    }

    @OnClick(R.id.tv_create_finish)
    public void createPlanet() {
        String name = planetName.getText().toString().trim();
        String description = planetDescription.getText().toString().trim();

        if (!name.isEmpty() && type != null && !description.isEmpty() && !takePhotoPath.isEmpty()) {
            savePhoto(name, takePhotoPath, description, type);
        } else if (takePhotoPath.isEmpty()) {
            toast("请选择星球头像！");
        } else if (name.isEmpty()) {
            toast("请填写星球名字！");
        } else if (type == null) {
            toast("请选择星球类型！");
        } else {
            toast("请填写完整信息！");
        }

    }

    private String getUserId() {
        SharedPreferences preferences = getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        String id = preferences.getString("userId", "");
        Log.d("userId", "userId" + id);
        return id;

    }

    public void savePhoto(final String name, final String takePhotoPath, final String des, final String type) {
        final BmobFile file = new BmobFile(new File(takePhotoPath));
        file.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    createPlanets(name, file, des, type);
                }

            }
        });
    }

    private void createPlanets(String planetName, BmobFile photo, String planetDescription, String type) {
        UserInfo user = BmobUser.getCurrentUser(UserInfo.class);
        user.setObjectId(getUserId());
        PlanetInfo planetInfo = new PlanetInfo();
        planetInfo.setPhoto(photo);
        planetInfo.setPlanetName(planetName);
        planetInfo.setPlanetDescription(planetDescription);
        planetInfo.setType(type);
        planetInfo.setUserInfo(user);
        planetInfo.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    toast("创建成功!");
                    startAction();
                } else {
                    toast("创建失败：" + e.getMessage());
                }
            }
        });
    }

    private void toast(String msg) {
        Toast.makeText(CreateNextStepActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv_back_home)
    public void onBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void startAction() {
        Intent intent = new Intent(CreateNextStepActivity.this, PlanetActivity.class);
        startActivity(intent);
        finish();
    }
}
