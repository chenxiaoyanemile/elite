package com.planet.emily.elite.com.emily.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.PlanetIssue;
import com.planet.emily.elite.bean.UserInfo;
import com.planet.emily.elite.com.emily.dynamics.CommentActivity;
import com.planet.emily.elite.com.emily.my.adapter.MyCollectionRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class CollectionActivity extends AppCompatActivity {

    @BindView(R.id.tv_back_collection)
    TextView tv_back_collection;

    @BindView(R.id.layout_collection_refresh)
    SwipeRefreshLayout layout_collection_refresh;

    @BindView(R.id.recycler_collection_item)
    RecyclerView recycler_collection_item;

    private MyCollectionRecyclerViewAdapter myCollectionRecyclerViewAdapter;
    List<PlanetIssue> planetIssueList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
        myCollectionRecyclerViewAdapter = new MyCollectionRecyclerViewAdapter(this);
        initDate();
        initUI();
    }

    private void initUI() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
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
        BmobQuery<PlanetIssue> query = new BmobQuery<>();
        UserInfo userInfo = new UserInfo(getUserId());
        query.addWhereEqualTo("author", userInfo);
        query.include("belongPlanet");
        query.findObjects(new FindListener<PlanetIssue>() {
            @Override
            public void done(List<PlanetIssue> list, BmobException e) {
                if (e == null) {
                    planetIssueList = list;
                    myCollectionRecyclerViewAdapter.setCollectionItems(planetIssueList);

                } else {
                    toast("获取数据失败！");
                }
            }
        });
    }

    private String getUserId() {
        SharedPreferences preferences = getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        return preferences.getString("userId", "");
    }

    private void toast(String msg) {
        Toast.makeText(CollectionActivity.this, msg, Toast.LENGTH_SHORT).show();
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
