package com.planet.emily.elite.com.emily.dynamics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.planet.emily.elite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AboutFragment extends Fragment {

    @BindView(R.id.ll_admire)
    LinearLayout ll_admire;

    public static AboutFragment newInstance(){
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.ll_admire)
    public void onClick(){
        Intent intent = new Intent(getActivity(),GetAdmireActivity.class);
        startActivity(intent);

    }


}
