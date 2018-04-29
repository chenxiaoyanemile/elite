package com.planet.emily.elite.com.emily.planet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planet.emily.elite.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class BacklogFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_backlog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


}
