package com.planet.emily.elite.com.emily.dynamics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planet.emily.elite.R;

import butterknife.ButterKnife;

public class DynamicsFragment extends Fragment {

    public static DynamicsFragment newInstance(){
        Bundle args = new Bundle();
        DynamicsFragment fragment = new DynamicsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_dynamics_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }




}
