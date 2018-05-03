package com.planet.emily.elite.com.emily.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.planet.emily.elite.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TakePhotoDialog extends BaseDialogFragment {

    public interface Callback {
        void takePhoto();

        void choosePhoto();
    }

    Callback callback;

    public static TakePhotoDialog newInstance(Callback callback) {
        TakePhotoDialog f = new TakePhotoDialog();
        f.setCallback(callback);
        return f;
    }

    @Override
    public void onStart() {
        super.onStart();
        int width = getDefaultDialogWidth();
        int height = getResources().getDimensionPixelSize(R.dimen.take_photo_dialog_height);
        getDialog().getWindow().setLayout(width, height);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_take_photo_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.take_photo_btn)
    public void takePhoto() {
        callback.takePhoto();
    }

    @OnClick(R.id.choose_photo_btn)
    public void choosePhoto() {
        callback.choosePhoto();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
