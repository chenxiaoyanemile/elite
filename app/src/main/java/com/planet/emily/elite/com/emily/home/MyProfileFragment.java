package com.planet.emily.elite.com.emily.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.planet.emily.elite.R;
import com.planet.emily.elite.com.emily.my.CollectionActivity;
import com.planet.emily.elite.com.emily.my.PublishActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyProfileFragment extends Fragment {

    @BindView(R.id.tv_publish)
    TextView tv_publish;
    @BindView(R.id.tv_collection)
    TextView tv_collection;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.tv_publish)
    public void clickPublish() {
        assertPublish();
    }

    @OnClick(R.id.tv_collection)
    public void tv_collection() {
        assertCollection();
    }


    private void assertPublish() {
        Intent intent = new Intent(getContext(), PublishActivity.class);
        startActivity(intent);
    }

    private void assertCollection() {
        Intent intent = new Intent(getContext(), CollectionActivity.class);
        startActivity(intent);
    }

}
