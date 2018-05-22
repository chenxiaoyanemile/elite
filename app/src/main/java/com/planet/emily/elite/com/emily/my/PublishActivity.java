package com.planet.emily.elite.com.emily.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.MyPublishItem;
import com.planet.emily.elite.com.emily.dynamics.CommentActivity;
import com.planet.emily.elite.com.emily.my.adapter.MyPublishRecyclerViewAdapter;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PublishActivity extends AppCompatActivity {

    @BindView(R.id.cv_publish_avatar)
    CircleImageView cv_publish_avatar;

    @BindView(R.id.tv_publish_username)
    TextView tv_publish_username;

    @BindView(R.id.tv_publish_back)
    TextView tv_publish_back;

    @BindView(R.id.recycler_publish_item)
    RecyclerView recycler_publish_item;

    @BindView(R.id.layout_swipe_refresh)
    SwipeRefreshLayout layout_swipe_refresh;

    private MyPublishRecyclerViewAdapter myPublishRecyclerViewAdapter;

    ArrayList<MyPublishItem> publishItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        getUserData();
        initDate();
        initUI();
    }

    private void initUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        myPublishRecyclerViewAdapter = new MyPublishRecyclerViewAdapter(this);
        myPublishRecyclerViewAdapter.setPublishItems(publishItems);
        recycler_publish_item.setLayoutManager(linearLayoutManager);
        recycler_publish_item.setAdapter(myPublishRecyclerViewAdapter);
        myPublishRecyclerViewAdapter.setOnItemClickListener(new MyPublishRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent in = new Intent(PublishActivity.this, CommentActivity.class);
                startActivity(in);
                finish();

            }
        });

        layout_swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myPublishRecyclerViewAdapter.notifyDataSetChanged();
                layout_swipe_refresh.setRefreshing(false);
            }
        });

    }

    private void getUserData(){
        SharedPreferences preferences=getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        String path = preferences.getString("takePhotoPath", "");
        String userName = preferences.getString("userName", "");

        tv_publish_username.setText(userName);
        showAvatar(path);

    }


    private void showAvatar(String path) {
        Uri uri = Uri.fromFile(new File(path));
        cv_publish_avatar.setImageURI(uri);
    }

    private void initDate(){
        String topic = "#产品研发团队#";
        String content = "学习 android 开发近一个月，最近两天写了个练手项目：模仿开发 ofo 共享单车，写了篇博客分享（https://www.jianshu.com/u/fe4c5bb1dc75），欢迎大家提意见。";
        String community = "629实验室";
        String time = "2017-9-19";

        MyPublishItem publishItem = new MyPublishItem(topic,content,community,time);
        publishItems.add(publishItem);
    }

    @OnClick(R.id.tv_publish_back)
    public void onBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
