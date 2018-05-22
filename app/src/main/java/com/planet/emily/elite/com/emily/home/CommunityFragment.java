package com.planet.emily.elite.com.emily.home;

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
import com.planet.emily.elite.bean.PlanetInfo;
import com.planet.emily.elite.com.emily.home.adapter.PlanetRecyclerViewAdapter;
import com.planet.emily.elite.com.emily.planet.CreateNextStepActivity;
import com.planet.emily.elite.com.emily.planet.PlanetActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommunityFragment extends Fragment {

    @BindView(R.id.swipe_refresh_planet)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_planet)
    RecyclerView recyclerView;

    private PlanetRecyclerViewAdapter planetRecyclerViewAdapter;
    ArrayList<PlanetInfo> planetInfoArrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topics, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    private void initUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        planetRecyclerViewAdapter = new PlanetRecyclerViewAdapter(getActivity());
        planetRecyclerViewAdapter.setPublishItems(planetInfoArrayList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(planetRecyclerViewAdapter);
        planetRecyclerViewAdapter.setOnItemClickListener(new PlanetRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
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


    @OnClick(R.id.iv_add_planet)
    public void addPlanet() {
        Intent intent = new Intent(getActivity(), CreateNextStepActivity.class);
        startActivity(intent);
    }


}
