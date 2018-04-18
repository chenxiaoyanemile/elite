package com.planet.emily.elite.com.emily.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.planet.emily.elite.R;
import com.planet.emily.elite.com.emily.planet.PlanetActivity;

public class CommunityFragment extends Fragment {

    private LinearLayout linear_jobs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topics, container, false);

        linear_jobs = view.findViewById(R.id.linear_jobs);
        linear_jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlanetActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


}
