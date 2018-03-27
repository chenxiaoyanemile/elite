package com.planet.emily.elite.com.emily.dynamics;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by emily on 2018/3/27
 */

public class FragmentAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragmentList;
    private List<String> titleList;

    public FragmentAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList, List<String> titleList){
        super(fragmentManager);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position){
        return fragmentList.get(position);
    }

    @Override
    public int getCount(){
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return titleList.get(position);
    }

}
