package com.planet.emily.elite.com.emily.dynamics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planet.emily.elite.R;

public class PrivateMessageFragment extends Fragment {

    public static PrivateMessageFragment newInstance(){
        Bundle args = new Bundle();
        PrivateMessageFragment fragment = new PrivateMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_private_message, container, false);
    }


}
