package com.planet.emily.elite.com.emily.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.PlanetInfo;
import com.planet.emily.elite.com.emily.home.adapter.PlanetRecyclerViewAdapter;
import com.planet.emily.elite.com.emily.planet.CreateNextStepActivity;
import com.planet.emily.elite.com.emily.planet.PlanetActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.content.Context.MODE_PRIVATE;

public class CommunityFragment extends Fragment {

    @BindView(R.id.swipe_refresh_planet)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_planet)
    RecyclerView recyclerView;

    private PlanetRecyclerViewAdapter planetRecyclerViewAdapter;

    private List<PlanetInfo> planetInfoList = new ArrayList<>();

    private SharedPreferences.Editor editor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topics, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PlanetInfo", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        ButterKnife.bind(this, view);
        planetRecyclerViewAdapter = new PlanetRecyclerViewAdapter(getActivity());
        initData();
        initUI();
        return view;
    }


    private void initUI() {
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(planetRecyclerViewAdapter);
        planetRecyclerViewAdapter.setOnItemClickListener(new PlanetRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                String name = planetInfoList.get(position).getPlanetName();
                String description = planetInfoList.get(position).getPlanetDescription();
                String id = planetInfoList.get(position).getObjectId();
                savePlanetInfo(id, name, description);
                Intent intent = new Intent(getActivity(), PlanetActivity.class);
                startActivity(intent);

            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                planetRecyclerViewAdapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });

    }

    private void savePlanetInfo(String Id, String planetName, String planetDes) {
        editor.putString("Id", Id);
        editor.putString("planetName", planetName);
        editor.putString("planetDes", planetDes);
        editor.apply();

    }

    private void initData() {
        BmobQuery<PlanetInfo> query = new BmobQuery<>();
        query.findObjects(new FindListener<PlanetInfo>() {
            @Override
            public void done(List<PlanetInfo> list, BmobException e) {
                if (e == null) {
                    planetInfoList = list;
                    planetRecyclerViewAdapter.setPublishItems(planetInfoList);

                } else {
                    toast("获取数据失败！");
                }
            }
        });
    }


    @OnClick(R.id.iv_add_planet)
    public void addPlanet() {
        Intent intent = new Intent(getActivity(), CreateNextStepActivity.class);
        startActivity(intent);
    }

    private void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

}
