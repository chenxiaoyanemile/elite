package com.planet.emily.elite.com.emily.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.planet.emily.elite.R;
import com.planet.emily.elite.app.MyConstants;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import devlight.io.library.ntb.NavigationTabBar;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bmob.initialize(this,"889470321947c301aff932fc7d9a9e64");

        initUI();
    }

    private void initUI() {

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CommunityFragment());
        fragments.add(new MessageFragment());
        fragments.add(new MyProfileFragment());
        VPFragmentAdapter adapter = new VPFragmentAdapter(getSupportFragmentManager(), fragments);

        final ViewPager viewPager = findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(adapter);

        final String[] colors = getResources().getStringArray(R.array.vertical_ntb);

        final NavigationTabBar navigationTabBar = findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.sel_groups_tab_item),
                        Color.parseColor(colors[0]))
                        .title(MyConstants.MESSAGE_BAR)
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.sel_dynamics_tab_item),
                        Color.parseColor(colors[0]))
                        .title(MyConstants.TOPIC_BAR)
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.sel_me_tab_item),
                        Color.parseColor(colors[0]))
                        .title(MyConstants.MY_PROFILE_BAR)
                        .build()
        );
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);

        navigationTabBar.setBehaviorEnabled(true);

        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {
            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
                model.hideBadge();
            }
        });
    }

}
