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
import com.planet.emily.elite.bean.PlanetProject;
import com.planet.emily.elite.com.emily.planet.adapter.MyVersionAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class ProjectFragment extends Fragment {

    @BindView(R.id.swipe_refresh_version)
    SwipeRefreshLayout swipe_refresh_version;

    @BindView(R.id.recycler_version)
    RecyclerView recycler_version;

    private MyVersionAdapter myVersionAdapter;
    private List<PlanetProject> planetProjectList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        ButterKnife.bind(this, view);
        myVersionAdapter = new MyVersionAdapter(getActivity());
        getProjectIssueData();
        initUI();
        return view;
    }


    private void initUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler_version.setLayoutManager(linearLayoutManager);
        recycler_version.setAdapter(myVersionAdapter);
        myVersionAdapter.setOnItemClickListener(new MyVersionAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
              /*  Intent in = new Intent(getActivity(), CommentActivity.class);
                startActivity(in);*/

            }
        });

        swipe_refresh_version.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myVersionAdapter.notifyDataSetChanged();
                swipe_refresh_version.setRefreshing(false);
            }
        });
    }

    private void getProjectIssueData() {
        BmobQuery<PlanetProject> query = new BmobQuery<>();
        PlanetInfo planetInfo = new PlanetInfo();
        planetInfo.setObjectId(getDataForPlanet());
        query.addWhereEqualTo("belongPlanet", planetInfo);
        query.findObjects(new FindListener<PlanetProject>() {
            @Override
            public void done(List<PlanetProject> list, BmobException e) {
                if (e == null) {
                    myVersionAdapter.setPlanetProjectArrayList(list);

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
