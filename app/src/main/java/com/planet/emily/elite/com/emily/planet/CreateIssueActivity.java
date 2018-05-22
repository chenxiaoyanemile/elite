package com.planet.emily.elite.com.emily.planet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.PlanetInfo;
import com.planet.emily.elite.bean.PlanetIssue;
import com.planet.emily.elite.bean.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class CreateIssueActivity extends AppCompatActivity {

    @BindView(R.id.et_required)
    EditText et_required;
    @BindView(R.id.et_summary)
    EditText et_summary;
    @BindView(R.id.et_des)
    EditText et_des;
    @BindView(R.id.et_result)
    EditText et_result;
    @BindView(R.id.et_version)
    EditText et_version;
    @BindView(R.id.et_priority)
    EditText et_priority;
    @BindView(R.id.et_assignee)
    EditText et_assignee;
    @BindView(R.id.btn_submit)
    Button btn_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_issue);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_submit)
    public void clickSubmit() {
        String required = et_required.getText().toString().trim();
        String summary = et_summary.getText().toString().trim();
        String description = et_des.getText().toString().trim();
        String result = et_result.getText().toString().trim();
        String priority = et_priority.getText().toString().trim();
        String assignee = et_assignee.getText().toString().trim();

        if (isNotEmpty(required) && isNotEmpty(summary) && isNotEmpty(description)
                && isNotEmpty(result) && isNotEmpty(priority) && isNotEmpty(assignee)) {

            createProjectIssue(required,summary,description,result,priority,assignee);
            finish();

        } else if (isEmpty(required)) {
            toast("请填写问题类型！");
        } else if (isEmpty(summary)) {
            toast("请填写问题概述！");
        } else if (isEmpty(description)) {
            toast("请填写详细描述！");
        } else if (isEmpty(result)) {
            toast("请填写期待的结果！");
        } else if (isEmpty(priority)) {
            toast("请填写问题优先级！");
        } else if (isEmpty(assignee)) {
            toast("请填写问题解决者！");
        }

    }


    private void createProjectIssue(String required, String summary, String description, String result,String priority,String assignee) {
        UserInfo user = BmobUser.getCurrentUser(UserInfo.class);
        user.setObjectId(getDataForUser());
        PlanetInfo planetInfo = new PlanetInfo();
        planetInfo.setObjectId(getDataForPlanet());
        PlanetIssue planetIssue = new PlanetIssue();
        planetIssue.setAuthor(user);
        planetIssue.setRequired(required);
        planetIssue.setSummary(summary);
        planetIssue.setDescription(description);
        planetIssue.setResult(result);
        planetIssue.setPriority(priority);
        planetIssue.setAssignee(assignee);
        planetIssue.setBelongPlanet(planetInfo);
        planetIssue.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    toast("提交成功!");
                } else {
                    toast("提交失败：" + e.getMessage());
                }
            }
        });
    }

    private String getDataForUser(){
        SharedPreferences preferences = getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        return preferences.getString("userId", "");
    }

    private String getDataForPlanet(){
        SharedPreferences preferences = getSharedPreferences("PlanetInfo", Context.MODE_PRIVATE);
        return preferences.getString("Id", "");
    }


    private boolean isNotEmpty(String s) {
        assert s != null;
        return !s.equals("");
    }


    private boolean isEmpty(String s) {
        return !isNotEmpty(s);
    }

    private void toast(String msg) {
        Toast.makeText(CreateIssueActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
