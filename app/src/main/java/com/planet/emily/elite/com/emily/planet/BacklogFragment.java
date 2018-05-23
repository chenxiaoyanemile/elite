package com.planet.emily.elite.com.emily.planet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.PlanetCard;
import com.planet.emily.elite.bean.PlanetInfo;
import com.planet.emily.elite.com.emily.dynamics.CommentActivity;
import com.planet.emily.elite.com.emily.planet.adapter.MyShareAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.planet.emily.elite.com.emily.planet.PageFragment.ARG_PAGE;


public class BacklogFragment extends Fragment {

    @BindView(R.id.swipe_refresh_share)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_share)
    RecyclerView recyclerViewShare;

    private MyShareAdapter myShareAdapter;
    private List<PlanetCard> planetCardArrayList = new ArrayList<>();

    public static BacklogFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        BacklogFragment backlogFragment = new BacklogFragment();
        backlogFragment.setArguments(args);
        return backlogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_backlog, container, false);
        ButterKnife.bind(this, view);
        myShareAdapter = new MyShareAdapter(getActivity());
        getPlanetShareData();
        initUI();
        return view;
    }


    private void initUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewShare.setLayoutManager(linearLayoutManager);
        recyclerViewShare.setAdapter(myShareAdapter);
        myShareAdapter.setOnItemClickListener(new MyShareAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                String id = planetCardArrayList.get(position).getObjectId();
                String url = planetCardArrayList.get(position).getAuthor().getPhoto().getUrl();
                String username = planetCardArrayList.get(position).getAuthor().getUsername();
                String time = planetCardArrayList.get(position).getCreatedAt();
                String content = planetCardArrayList.get(position).getCardContent();
                Intent in = new Intent(getActivity(), CommentActivity.class);
                in.putExtra("id",id);
                in.putExtra("url",url);
                in.putExtra("username",username);
                in.putExtra("time",time);
                in.putExtra("content",content);
                startActivity(in);

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myShareAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getPlanetShareData() {
        BmobQuery<PlanetCard> query = new BmobQuery<>();
        PlanetInfo planetInfo = new PlanetInfo();
        planetInfo.setObjectId(getDataForPlanet());
        query.addWhereEqualTo("belongPlanet", planetInfo);
        query.include("author");
        query.findObjects(new FindListener<PlanetCard>() {
            @Override
            public void done(List<PlanetCard> list, BmobException e) {
                if (e == null) {
                    planetCardArrayList = list;
                    myShareAdapter.setPlanetCardArrayList(planetCardArrayList);

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
