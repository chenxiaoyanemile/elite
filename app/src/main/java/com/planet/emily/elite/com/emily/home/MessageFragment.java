package com.planet.emily.elite.com.emily.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planet.emily.elite.R;
import com.planet.emily.elite.com.emily.dynamics.AboutFragment;
import com.planet.emily.elite.com.emily.dynamics.DynamicsFragment;
import com.planet.emily.elite.com.emily.dynamics.FragmentAdapter;
import com.planet.emily.elite.com.emily.dynamics.PrivateMessageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MessageFragment extends Fragment {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vp_message)
    ViewPager vp_message;

    private List<Fragment> fragmentList;
    private View view;
    private List<String> titleList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        fragmentList.add(new DynamicsFragment());
        fragmentList.add(new AboutFragment());
        fragmentList.add(new PrivateMessageFragment());
        titleList.add("动态");
        titleList.add("与我相关");
        titleList.add("私信");


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        setupPager();
        return view;
    }


    private void setupPager() {

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        tabLayout.setTabTextColors(Color.parseColor("#ff16b998"), Color.parseColor("#ff16b998"));//设置tab上文字的颜色，第一个参数表示没有选中状态下的文字颜色，第二个参数表示选中后的文字颜色
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ff16b998"));
        vp_message.setAdapter(new FragmentAdapter(getChildFragmentManager(), fragmentList, titleList));
        tabLayout.setupWithViewPager(vp_message);

    }


}
