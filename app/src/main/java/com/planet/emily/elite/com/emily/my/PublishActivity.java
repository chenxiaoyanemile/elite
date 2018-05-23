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
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.PlanetCard;
import com.planet.emily.elite.bean.UserInfo;
import com.planet.emily.elite.com.emily.dynamics.CommentActivity;
import com.planet.emily.elite.com.emily.my.adapter.MyPublishRecyclerViewAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
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

    List<PlanetCard> planetCardArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        myPublishRecyclerViewAdapter = new MyPublishRecyclerViewAdapter(this);
        getUserData();
        initUI();
    }

    private void initUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
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

    private void getUserData() {
        SharedPreferences preferences = getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        String path = preferences.getString("takePhotoPath", "");
        String userName = preferences.getString("userName", "");
        String id = preferences.getString("userId", "");
        tv_publish_username.setText(userName);
        showAvatar(path);
        initDate(id);

    }


    private void showAvatar(String path) {
        Uri uri = Uri.fromFile(new File(path));
        cv_publish_avatar.setImageURI(uri);
    }

    private void initDate(String userId) {
        BmobQuery<PlanetCard> query = new BmobQuery<>();
        UserInfo userInfo = new UserInfo(userId);
        query.addWhereEqualTo("author", userInfo);
        query.include("belongPlanet");
        query.findObjects(new FindListener<PlanetCard>() {
            @Override
            public void done(List<PlanetCard> list, BmobException e) {
                if (e == null) {
                    planetCardArrayList = list;
                    myPublishRecyclerViewAdapter.setPlanetCardArrayList(planetCardArrayList);

                } else {
                    toast("获取数据失败！");
                }
            }
        });

    }


    private void toast(String msg) {
        Toast.makeText(PublishActivity.this, msg, Toast.LENGTH_SHORT).show();
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
