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
import com.planet.emily.elite.bean.PlanetIssue;
import com.planet.emily.elite.com.emily.planet.adapter.MyIssueAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.planet.emily.elite.com.emily.planet.PageFragment.ARG_PAGE;


public class IssuesFragment extends Fragment {

    @BindView(R.id.swipe_refresh_issue)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_issue)
    RecyclerView recyclerView;

    private MyIssueAdapter myIssueAdapter;
    private List<PlanetIssue> planetIssueList = new ArrayList<>();


    public static IssuesFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        IssuesFragment issuesFragment = new IssuesFragment();
        issuesFragment.setArguments(args);
        return issuesFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_issues, container, false);
        ButterKnife.bind(this, view);
        myIssueAdapter = new MyIssueAdapter(getActivity());
        getPlanetIssueData();
        initUI();
        return view;
    }


    private void initUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myIssueAdapter);
        myIssueAdapter.setOnItemClickListener(new MyIssueAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
              /*  Intent in = new Intent(getActivity(), CommentActivity.class);
                startActivity(in);*/

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myIssueAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getPlanetIssueData() {
        BmobQuery<PlanetIssue> query = new BmobQuery<>();
        PlanetInfo planetInfo = new PlanetInfo();
        planetInfo.setObjectId(getDataForPlanet());
        query.addWhereEqualTo("belongPlanet", planetInfo);
        query.include("author");
        query.findObjects(new FindListener<PlanetIssue>() {
            @Override
            public void done(List<PlanetIssue> list, BmobException e) {
                if (e == null) {
                    planetIssueList = list;
                    myIssueAdapter.setPublishItems(planetIssueList);

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
