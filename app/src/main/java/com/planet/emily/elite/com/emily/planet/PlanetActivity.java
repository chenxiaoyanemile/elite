package com.planet.emily.elite.com.emily.planet;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jaeger.library.StatusBarUtil;
import com.planet.emily.elite.R;
import com.planet.emily.elite.com.emily.planet.adapter.ViewPagerAdapter;
import com.planet.emily.elite.event.PlanetEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlanetActivity extends AppCompatActivity {

    private LinearLayout head_layout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private CoordinatorLayout root_layout;
    private TextView tv_planet_name;
    private TextView tv_planet_description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);
        initUI();
        EventBus.getDefault().register(this);

    }

    private void initUI() {
        AppBarLayout app_bar_layout = findViewById(R.id.app_bar_layout);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        head_layout = findViewById(R.id.head_layout);
        root_layout = findViewById(R.id.root_layout);
        tv_planet_name = findViewById(R.id.tv_planet_name);
        tv_planet_description = findViewById(R.id.tv_planet_description);

        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        mCollapsingToolbarLayout = findViewById(R.id
                .collapsing_toolbar_layout);
        app_bar_layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -head_layout.getHeight() / 2) {
                    mCollapsingToolbarLayout.setTitle("629实验室");
                } else {
                    mCollapsingToolbarLayout.setTitle(" ");
                }
            }
        });
        TabLayout toolbar_tab = findViewById(R.id.toolbar_tab);
        ViewPager main_vp_container = findViewById(R.id.main_vp_container);


        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new BacklogFragment());
        fragments.add(new ProjectFragment());
        fragments.add(new IssuesFragment());
        fragments.add(new ManagementFragment());
        fragments.add(new MembersFragment());

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        main_vp_container.setAdapter(vpAdapter);
        main_vp_container.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener
                (toolbar_tab));
        toolbar_tab.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener
                (main_vp_container));

        loadBlurAndSetStatusBar();

    }

    /**
     * 设置毛玻璃效果和沉浸状态栏
     */
    private void loadBlurAndSetStatusBar() {
        StatusBarUtil.setTranslucent(PlanetActivity.this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
        Glide.with(this).load(R.mipmap.bg_login_fragment_header).bitmapTransform(new BlurTransformation(this, 100))
                .into(new SimpleTarget<GlideDrawable>() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                            GlideDrawable> glideAnimation) {
                        head_layout.setBackground(resource);
                        root_layout.setBackground(resource);
                    }
                });

        Glide.with(this).load(R.mipmap.bg_login_fragment_header).bitmapTransform(new BlurTransformation(this, 100))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                            GlideDrawable> glideAnimation) {
                        mCollapsingToolbarLayout.setContentScrim(resource);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.action_settings:
                assertSetPlanetProfile();
                break;
        }
        if (!msg.equals("")) {
            Toast.makeText(PlanetActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void assertSetPlanetProfile(){
        Intent intent = new Intent(PlanetActivity.this, PlanetProfileActivity.class);
        startActivity(intent);
        finish();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPlanetEvent(PlanetEvent event) {
        String planetName = event.getPlanetName();
        String planetDes = event.getPlanetDescription();
        tv_planet_name.setText(planetName);
        tv_planet_description.setText(planetDes);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
