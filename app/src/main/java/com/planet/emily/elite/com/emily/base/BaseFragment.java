package com.planet.emily.elite.com.emily.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by emily on 2018/3/27
 */

public class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    protected boolean getBooleanExtra(String key) {
        return getArguments().getBoolean(key, false);
    }

    protected String getStringExtra(String key) {
        return getArguments().getString(key, "");
    }

    protected int getIntExtra(String key, int defaultValue) {
        return getArguments().getInt(key, defaultValue);
    }

    protected int getIntExtra(String key) {
        return getArguments().getInt(key, 0);
    }

    protected long getLongExtra(String key) {
        return getArguments().getLong(key, 0);
    }

    protected Serializable getSerializableExtra(String key) {
        return getArguments().getSerializable(key);
    }


}
