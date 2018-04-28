package com.planet.emily.elite.com.emily.my;

import android.content.Intent;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublishActivity extends AppCompatActivity {

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

    private void initDate(){
        String topic = "#求职# #上海#";
        String content = "学习 android 开发近一个月，最近两天写了个练手项目：模仿开发 ofo 共享单车，写了篇博客分享，欢迎大家提意见。";
        String community = "招聘/求职/跳槽";
        String time = "2018-3-28";

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
