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
import com.planet.emily.elite.bean.MyCollectionItem;
import com.planet.emily.elite.com.emily.dynamics.CommentActivity;
import com.planet.emily.elite.com.emily.my.adapter.MyCollectionRecyclerViewAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectionActivity extends AppCompatActivity {

    @BindView(R.id.tv_back_collection)
    TextView tv_back_collection;

    @BindView(R.id.layout_collection_refresh)
    SwipeRefreshLayout layout_collection_refresh;

    @BindView(R.id.recycler_collection_item)
    RecyclerView recycler_collection_item;

    private MyCollectionRecyclerViewAdapter myCollectionRecyclerViewAdapter;
    ArrayList<MyCollectionItem> collectionItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
        initDate();
        initUI();
    }

    private void initUI() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        myCollectionRecyclerViewAdapter = new MyCollectionRecyclerViewAdapter(this);
        myCollectionRecyclerViewAdapter.setCollectionItems(collectionItems);
        recycler_collection_item.setLayoutManager(linearLayoutManager);
        recycler_collection_item.setAdapter(myCollectionRecyclerViewAdapter);
        myCollectionRecyclerViewAdapter.setOnItemClickListener(new MyCollectionRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent in = new Intent(CollectionActivity.this, CommentActivity.class);
                startActivity(in);
                finish();

            }
        });

        layout_collection_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myCollectionRecyclerViewAdapter.notifyDataSetChanged();
                layout_collection_refresh.setRefreshing(false);
            }
        });

    }

    private void initDate() {
        String content = "学习 android 开发近一个月，最近两天写了个练手项目：模仿开发 ofo 共享单车，写了篇博客分享，欢迎大家提意见。";
        String community = "招聘/求职/跳槽";
        String author = "小之";
        String time = "2018-3-28";

        MyCollectionItem collectionItem = new MyCollectionItem(content, community, author, time);
        collectionItems.add(collectionItem);
    }


    @OnClick(R.id.tv_back_collection)
    public void onBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
