package com.planet.emily.elite.com.emily.dynamics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.MyDynamics;
import com.planet.emily.elite.bean.UserInfo;
import com.planet.emily.elite.com.emily.dynamics.adapter.DynamicsRecyclerViewAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DynamicsFragment extends Fragment {

    @BindView(R.id.layout_dynamics_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_dynamics_item)
    RecyclerView recyclerView;

    private DynamicsRecyclerViewAdapter dynamicsRecyclerViewAdapter;
    ArrayList<MyDynamics> myDynamics = new ArrayList<>();

    public static DynamicsFragment newInstance() {
        Bundle args = new Bundle();
        DynamicsFragment fragment = new DynamicsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dynamics_fragment, container, false);
        ButterKnife.bind(this, view);
        //initDate();
        initUI();
        return view;
    }

    private void initUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        dynamicsRecyclerViewAdapter = new DynamicsRecyclerViewAdapter(getActivity());
        dynamicsRecyclerViewAdapter.setPublishItems(myDynamics);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(dynamicsRecyclerViewAdapter);
        dynamicsRecyclerViewAdapter.setOnItemClickListener(new DynamicsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent in = new Intent(getActivity(), CommentActivity.class);
                startActivity(in);

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dynamicsRecyclerViewAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void initDate() {

        UserInfo userInfo = new UserInfo();
        String title = "#产品研发#";
        String content = "学习 android 开发近一个月，最近两天写了个练手项目：模仿开发 ofo 共享单车，写了篇博客分享（https://www.jianshu.com/u/fe4c5bb1dc75），欢迎大家提意见。";
        String community = "629实验室";
        String time = "2017-9-19";

        MyDynamics myDynamic = new MyDynamics(userInfo, title, content, community, time);
        myDynamics.add(myDynamic);
    }


}
