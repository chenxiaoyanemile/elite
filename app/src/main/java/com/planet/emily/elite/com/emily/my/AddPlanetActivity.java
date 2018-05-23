package com.planet.emily.elite.com.emily.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.PlanetInfo;
import com.planet.emily.elite.bean.UserInfo;
import com.planet.emily.elite.com.emily.planet.PlanetActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class AddPlanetActivity extends AppCompatActivity {

    @BindView(R.id.et_input_welcome_code)
    EditText et_input_welcome_code;
    @BindView(R.id.tv_welcome_submit)
    TextView tv_welcome_submit;

    String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_planet);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        objectId = intent.getStringExtra("id");
    }

    @OnClick(R.id.tv_welcome_submit)
    public void clickSubmit() {
        String welcomeCode = et_input_welcome_code.getText().toString().trim();
        if (isNotEmpty(welcomeCode)) {
            if (welcomeCode.equals(objectId)) {
                addRelationForPlanetAndUser(objectId);
            } else {
                toast("邀请码不正确！");
            }
        } else {
            toast("邀请码不能为空！");
        }
    }

    private void addRelationForPlanetAndUser(String PlanetId) {
        PlanetInfo planetInfo = new PlanetInfo(PlanetId);
        BmobRelation relation = new BmobRelation();
        relation.add(new UserInfo(getUserId()));
        planetInfo.setMembers(relation);
        planetInfo.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    toast("恭喜加入成功！");
                    Intent intent = new Intent(AddPlanetActivity.this, PlanetActivity.class);
                    intent.putExtra("ObjectId", objectId);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }


    private String getUserId() {
        SharedPreferences preferences = getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        String id = preferences.getString("userId", "");
        return id;

    }

    private boolean isNotEmpty(String s) {
        assert s != null;
        return !s.equals("");
    }


    private void toast(String msg) {
        Toast.makeText(AddPlanetActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
