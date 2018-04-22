package com.planet.emily.elite.com.emily.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planet.emily.elite.R;
import com.planet.emily.elite.com.emily.planet.CreateNextStepActivity;
import com.planet.emily.elite.com.emily.planet.PlanetActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommunityFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topics, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.linear_jobs)
    public void interPlanet() {
        Intent intent = new Intent(getActivity(), PlanetActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.add_planet)
    public void addPlanet() {
        Intent intent = new Intent(getActivity(), CreateNextStepActivity.class);
        startActivity(intent);
    }


}
