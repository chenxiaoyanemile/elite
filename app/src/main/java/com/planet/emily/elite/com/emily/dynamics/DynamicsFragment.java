package com.planet.emily.elite.com.emily.dynamics;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.Comment;
import com.planet.emily.elite.bean.UserInfo;
import com.planet.emily.elite.com.emily.dynamics.adapter.DynamicsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class DynamicsFragment extends Fragment {

    @BindView(R.id.layout_dynamics_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_dynamics_item)
    RecyclerView recyclerView;

    private DynamicsRecyclerViewAdapter dynamicsRecyclerViewAdapter;
    ArrayList<Comment> commentArrayList = new ArrayList<>();

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
        dynamicsRecyclerViewAdapter = new DynamicsRecyclerViewAdapter(getActivity());
        initDate();
        initUI();
        return view;
    }

    private void initUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        dynamicsRecyclerViewAdapter = new DynamicsRecyclerViewAdapter(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(dynamicsRecyclerViewAdapter);
        dynamicsRecyclerViewAdapter.setOnItemClickListener(new DynamicsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                //TODO

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
        BmobQuery<Comment> query = new BmobQuery<>();
        UserInfo userInfo = new UserInfo(getUserData());
        query.addWhereEqualTo("commenter", userInfo);
        query.include("commenter");
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                if (e == null) {
                    dynamicsRecyclerViewAdapter.setCommentArrayList(list);

                } else {
                    toast("获取数据失败！");
                }
            }
        });
    }

    private String getUserData() {
        SharedPreferences preferences = getActivity().getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        return preferences.getString("userId", "");
    }

    private void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


}
