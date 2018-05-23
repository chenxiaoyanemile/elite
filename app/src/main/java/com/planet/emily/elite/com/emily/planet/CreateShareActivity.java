package com.planet.emily.elite.com.emily.planet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.PlanetCard;
import com.planet.emily.elite.bean.PlanetInfo;
import com.planet.emily.elite.bean.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class CreateShareActivity extends AppCompatActivity {

    @BindView(R.id.et_share_content)
    EditText shareContent;
    @BindView(R.id.et_share_title)
    EditText shareTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_share);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_share_submit)
    public void clickSubmit() {
        String text = shareContent.getText().toString().trim();
        String title = shareTitle.getText().toString().trim();
        if (isNotEmpty(text) && isNotEmpty(title)) {
            createProjectIssue(title, text);
        } else if (isEmpty(title)) {
            toast("标题不能为空！");
        } else if (isEmpty(text)) {
            toast("请填写内容！");
        }
    }


    private void createProjectIssue(String title, String content) {
        UserInfo user = BmobUser.getCurrentUser(UserInfo.class);
        user.setObjectId(getDataForUser());
        PlanetInfo planetInfo = new PlanetInfo();
        planetInfo.setObjectId(getDataForPlanet());

        PlanetCard planetCard = new PlanetCard();
        planetCard.setAuthor(user);
        planetCard.setCardTitle(title);
        planetCard.setCardContent(content);
        planetCard.setBelongPlanet(planetInfo);

        planetCard.save(new SaveListener<String>() {
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

    @OnClick(R.id.tv_back_share)
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
        Toast.makeText(CreateShareActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
