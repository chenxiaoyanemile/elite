package com.planet.emily.elite.com.emily.planet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.PlanetInfo;
import com.planet.emily.elite.bean.PlanetProject;
import com.planet.emily.elite.bean.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class CreateVersionActivity extends AppCompatActivity {

    @BindView(R.id.et_input_version)
    EditText inputVersion;
    @BindView(R.id.et_input_status)
    EditText inputStatus;
    @BindView(R.id.et_description)
    EditText inputDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_version);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.tv_version_submit,R.id.btn_version_submit})
    public void clickSubmit() {

        String version = inputVersion.getText().toString().trim();
        String status = inputStatus.getText().toString().trim();
        String description = inputDescription.getText().toString().trim();

        if (isNotEmpty(version) && isNotEmpty(status) && isNotEmpty(description)) {
            createProjectVersion(version, status, description);

        } else if (isEmpty(version)) {
            toast("请填写版本号！");
        } else if (isEmpty(status)) {
            toast("请填写版本的状态！");
        } else if (isEmpty(description)) {
            toast("请填写版本的描述！");
        }

    }

    private void createProjectVersion(String version, String status, String des) {
        UserInfo user = BmobUser.getCurrentUser(UserInfo.class);
        user.setObjectId(getDataForUser());
        PlanetInfo planetInfo = new PlanetInfo();
        planetInfo.setObjectId(getDataForPlanet());

        PlanetProject planetProject = new PlanetProject();
        planetProject.setFounder(user);
        planetProject.setVersion(version);
        planetProject.setStatus(status);
        planetProject.setDescription(des);
        planetProject.setBelongPlanet(planetInfo);
        planetProject.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    toast("提交成功!");
                    finish();
                } else {
                    toast("提交失败：" + e.getMessage());
                }
            }
        });
    }


    private String getDataForUser() {
        SharedPreferences preferences = getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        return preferences.getString("userId", "");
    }

    private String getDataForPlanet() {
        SharedPreferences preferences = getSharedPreferences("PlanetInfo", Context.MODE_PRIVATE);
        return preferences.getString("Id", "");
    }

    @OnClick(R.id.tv_back_version)
    public void onBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private boolean isNotEmpty(String s) {
        assert s != null;
        return !s.equals("");
    }


    private boolean isEmpty(String s) {
        return !isNotEmpty(s);
    }

    private void toast(String msg) {
        Toast.makeText(CreateVersionActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
