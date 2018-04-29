package com.planet.emily.elite.com.emily.planet;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planet.emily.elite.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class ManagementFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_management, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.tv_share)
    public void clickShare(){

        Intent intent = new Intent(getActivity(),CreateShareActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.tv_version)
    public void clickVersion(){
        Intent intent = new Intent(getActivity(),CreateVersionActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_issue)
    public void clickIssue(){
        Intent intent = new Intent(getActivity(),CreateIssueActivity.class);
        startActivity(intent);
    }



}
