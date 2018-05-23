package com.planet.emily.elite.com.emily.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.PlanetInfo;
import com.planet.emily.elite.com.emily.my.adapter.MyPlanetSortRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class PlanetSortingActivity extends AppCompatActivity {

    @BindView(R.id.recycler_rank_item)
    RecyclerView recycler_rank_item;
    @BindView(R.id.layout_rank_refresh)
    SwipeRefreshLayout layout_rank_refresh;

    private MyPlanetSortRecyclerViewAdapter myPlanetSortRecyclerViewAdapter;

    List<PlanetInfo> myPlanetItemsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_sorting);
        ButterKnife.bind(this);
        myPlanetSortRecyclerViewAdapter = new MyPlanetSortRecyclerViewAdapter(this);
        initData();
        initUI();
    }


    private void initUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        myPlanetSortRecyclerViewAdapter = new MyPlanetSortRecyclerViewAdapter(this);
        recycler_rank_item.setLayoutManager(linearLayoutManager);
        recycler_rank_item.setAdapter(myPlanetSortRecyclerViewAdapter);
        myPlanetSortRecyclerViewAdapter.setOnItemClickListener(new MyPlanetSortRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                String id = myPlanetItemsList.get(position).getObjectId();
                Intent intent = new Intent(PlanetSortingActivity.this, AddPlanetActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                finish();

            }
        });
        layout_rank_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myPlanetSortRecyclerViewAdapter.notifyDataSetChanged();
                layout_rank_refresh.setRefreshing(false);
            }
        });

    }

    private void initData() {


        BmobQuery<PlanetInfo> query = new BmobQuery<>();
        query.findObjects(new FindListener<PlanetInfo>() {
            @Override
            public void done(List<PlanetInfo> list, BmobException e) {
                if (e == null) {
                    myPlanetItemsList = list;
                    myPlanetSortRecyclerViewAdapter.setCollectionItems(myPlanetItemsList);

                } else {
                    toast("获取数据失败！");
                }
            }
        });


    }


    private void toast(String msg) {
        Toast.makeText(PlanetSortingActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv_back_rank)
    public void onBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
