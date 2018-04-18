package com.planet.emily.elite.com.emily.planet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planet.emily.elite.R;

import java.util.ArrayList;
import java.util.List;


public class PageFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int mPage = getArguments().getInt(ARG_PAGE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        RecyclerView lv = (RecyclerView) view.findViewById(R.id.lv);
        // 创建一个线性布局管理器

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        // 设置布局管理器

        lv.setLayoutManager(layoutManager);

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
        lv.setAdapter(new MyAdapter(list));

        return view;
    }


}
