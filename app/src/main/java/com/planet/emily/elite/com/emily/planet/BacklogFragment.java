package com.planet.emily.elite.com.emily.planet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.Comment;
import com.planet.emily.elite.bean.PlanetCard;

import java.util.List;

import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class BacklogFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_backlog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void QueryComment(String ObjectId) {
        BmobQuery<Comment> query = new BmobQuery<Comment>();
        PlanetCard planetCard = new PlanetCard();
        planetCard.setObjectId(ObjectId);
        query.addWhereEqualTo("planetCard", new BmobPointer(planetCard));
        query.include("user,planetCard.author");
        query.findObjects(new FindListener<Comment>() {

            @Override
            public void done(List<Comment> objects, BmobException e) {

            }
        });
    }


}
