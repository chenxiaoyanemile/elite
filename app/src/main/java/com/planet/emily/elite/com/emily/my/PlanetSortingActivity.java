package com.planet.emily.elite.com.emily.my;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.MyCollectionItem;
import com.planet.emily.elite.bean.MyPlanetItem;
import com.planet.emily.elite.com.emily.my.adapter.MyPlanetSortRecyclerViewAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlanetSortingActivity extends AppCompatActivity {

    @BindView(R.id.recycler_rank_item)
    RecyclerView recycler_rank_item;
    @BindView(R.id.layout_rank_refresh)
    SwipeRefreshLayout layout_rank_refresh;

    private MyPlanetSortRecyclerViewAdapter myPlanetSortRecyclerViewAdapter;

    ArrayList<MyPlanetItem> myPlanetItemsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_sorting);
        ButterKnife.bind(this);
        initUI();
    }


    private void initUI(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        myPlanetSortRecyclerViewAdapter = new MyPlanetSortRecyclerViewAdapter(this);
        recycler_rank_item.setLayoutManager(linearLayoutManager);
        recycler_rank_item.setAdapter(myPlanetSortRecyclerViewAdapter);
        myPlanetSortRecyclerViewAdapter.setOnItemClickListener(new MyPlanetSortRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

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

    private void initData(){
        String sortUrl = "";
        String planetName ="629实验室";
        String planetAvatarUrl ="";
        String founderName = "小智";

        MyPlanetItem myPlanetItem = new MyPlanetItem(sortUrl,planetName,planetAvatarUrl,founderName);
        myPlanetItemsList.add(myPlanetItem);
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
