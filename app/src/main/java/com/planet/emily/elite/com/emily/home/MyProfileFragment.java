package com.planet.emily.elite.com.emily.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.planet.emily.elite.R;
import com.planet.emily.elite.com.emily.my.CollectionActivity;
import com.planet.emily.elite.com.emily.my.PlanetSortingActivity;
import com.planet.emily.elite.com.emily.my.PublishActivity;
import com.planet.emily.elite.com.emily.my.WalletActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyProfileFragment extends Fragment {

    @BindView(R.id.tv_publish)
    TextView tv_publish;
    @BindView(R.id.tv_collection)
    TextView tv_collection;
    @BindView(R.id.tv_wallet)
    TextView tv_wallet;
    @BindView(R.id.tv_gift)
    TextView tv_gift;
    @BindView(R.id.tv_welcome)
    TextView tv_welcome;
    @BindView(R.id.tv_start)
    TextView tv_start;
    @BindView(R.id.ly_set)
    LinearLayout ly_set;
    @BindView(R.id.tv_help)
    TextView tv_help;


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
    public void clickCollection() {
        assertCollection();
    }

    @OnClick(R.id.tv_wallet)
    public void clickWallet(){
        assertWallet();
    }

    @OnClick(R.id.tv_gift)
    public void clickGift(){
        assertGift();
    }

    @OnClick(R.id.tv_welcome)
    public void clickWelcome(){
        assertWelcome();
    }

    @OnClick(R.id.tv_start)
    public void clickStart(){
        assertStart();
    }

    @OnClick(R.id.ly_set)
    public void clickSet(){
        assertSet();
    }


    private void assertPublish() {
        Intent intent = new Intent(getContext(), PublishActivity.class);
        startActivity(intent);
    }

    private void assertCollection() {
        Intent intent = new Intent(getContext(), CollectionActivity.class);
        startActivity(intent);
    }

    private void assertWallet(){
        Intent intent = new Intent(getContext(), WalletActivity.class);
        startActivity(intent);
    }

    private void assertGift(){

    }

    private void assertSet(){

    }

    private void assertStart(){
        Intent intent = new Intent(getActivity(), PlanetSortingActivity.class);
        startActivity(intent);

    }

    private void assertWelcome(){

    }

}
