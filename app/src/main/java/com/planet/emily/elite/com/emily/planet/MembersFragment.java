package com.planet.emily.elite.com.emily.planet;


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
import com.planet.emily.elite.bean.PlanetInfo;
import com.planet.emily.elite.bean.UserInfo;
import com.planet.emily.elite.com.emily.planet.adapter.MyMembersAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class MembersFragment extends Fragment {

    @BindView(R.id.swipe_refresh_members)
    SwipeRefreshLayout swipe_refresh_members;
    @BindView(R.id.recycler_members)
    RecyclerView recycler_members;

    private MyMembersAdapter myMembersAdapter;
    private List<UserInfo> userInfoList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_members, container, false);
        ButterKnife.bind(this, view);
        myMembersAdapter = new MyMembersAdapter(getActivity());
        queryRelation();
        initUI();
        return view;
    }

    private void initUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler_members.setLayoutManager(linearLayoutManager);
        recycler_members.setAdapter(myMembersAdapter);
        myMembersAdapter.setOnItemClickListener(new MyMembersAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

            }
        });

        swipe_refresh_members.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myMembersAdapter.notifyDataSetChanged();
                swipe_refresh_members.setRefreshing(false);
            }
        });
    }

    private void queryRelation() {
        BmobQuery<UserInfo> query = new BmobQuery<>();
        PlanetInfo planetInfo = new PlanetInfo(getDataForPlanet());
        query.addWhereRelatedTo("members", new BmobPointer(planetInfo));
        query.findObjects(new FindListener<UserInfo>() {
            @Override
            public void done(List<UserInfo> list, BmobException e) {
                if (e == null) {
                    userInfoList = list;
                    myMembersAdapter.setMembersList(userInfoList);

                } else {
                    toast("获取数据失败！");
                }
            }
        });
    }

    private String getDataForPlanet() {
        SharedPreferences preferences = getActivity().getSharedPreferences("PlanetInfo", Context.MODE_PRIVATE);
        return preferences.getString("Id", "");
    }


    private void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


}
