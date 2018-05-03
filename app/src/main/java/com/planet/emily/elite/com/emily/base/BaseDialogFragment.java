package com.planet.emily.elite.com.emily.base;


import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.planet.emily.elite.R;

import java.util.Objects;

public class BaseDialogFragment extends DialogFragment {

    @Override
    public void show(FragmentManager manager, String tag) {
        boolean mDismissed = false;
        boolean mShownByMe = true;
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawableResource(R.color.green);
    }

    public int getDefaultDialogWidth() {
        return getResources().getDimensionPixelSize(R.dimen.dialog_default_width);
    }
}
