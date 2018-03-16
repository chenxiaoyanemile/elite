package com.planet.emily.elite.com.emily.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.planet.emily.elite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MessageFragment extends Fragment {

    @BindView(R.id.tv_message_dynamics)
    TextView tv_message_dynamics;
    @BindView(R.id.tv_message_about_me)
    TextView tv_message_about_me;
    @BindView(R.id.tv_message_private)
    TextView tv_message_private;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.tv_message_dynamics)
    public void start_dynamics(){

    }

    @OnClick(R.id.tv_message_about_me)
    public void start_abourt(){

    }

    @OnClick(R.id.tv_message_private)
    public void start_private(){

    }


}
