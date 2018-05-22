package com.planet.emily.elite.com.emily.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.UserInfo;
import com.planet.emily.elite.com.emily.my.AboutActivity;
import com.planet.emily.elite.com.emily.my.CollectionActivity;
import com.planet.emily.elite.com.emily.my.HelpActivity;
import com.planet.emily.elite.com.emily.my.PlanetSortingActivity;
import com.planet.emily.elite.com.emily.my.PublishActivity;
import com.planet.emily.elite.com.emily.my.RuleActivity;
import com.planet.emily.elite.com.emily.my.SetActivity;
import com.planet.emily.elite.com.emily.my.WelcomeActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.QueryListener;
import de.hdodenhof.circleimageview.CircleImageView;


public class MyProfileFragment extends Fragment {

    @BindView(R.id.tv_publish)
    TextView tv_publish;
    @BindView(R.id.tv_collection)
    TextView tv_collection;
    @BindView(R.id.tv_wallet)
    TextView tv_wallet;
    @BindView(R.id.tv_gift)
    TextView tv_gift;
    @BindView(R.id.tv_welcome)
    TextView tv_welcome;
    @BindView(R.id.tv_start)
    TextView tv_start;
    @BindView(R.id.ly_set)
    LinearLayout ly_set;
    @BindView(R.id.tv_help)
    TextView tv_help;
    @BindView(R.id.cv_avatar)
    CircleImageView avatar;
    @BindView(R.id.tv_username)
    TextView username;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        ButterKnife.bind(this, view);
        getData();
        return view;
    }


    private void getData() {

        BmobQuery<UserInfo> bmobQuery = new BmobQuery<UserInfo>();
        bmobQuery.getObject("7e1f860255", new QueryListener<UserInfo>() {
            @Override
            public void done(UserInfo user, BmobException e) {
                if (e == null) {
                    download(user.getPhoto());
                    username.setText(user.getUsername());

                } else {
                    toast("查询失败：" + e.getMessage());
                }
            }
        });
    }

    private void download(BmobFile photo) {
        photo.download(new DownloadFileListener() {
            @Override
            public void done(String path, BmobException e) {
                if (e == null) {
                    showAvatar(path);
                } else {
                    toast("加载图片失败：" + e.getMessage());
                }
            }

            @Override
            public void onProgress(Integer integer, long l) {

            }
        });
    }

    private void showAvatar(String path) {
        Uri uri = Uri.fromFile(new File(path));
        avatar.setImageURI(uri);
    }

    private void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv_publish)
    public void clickPublish() {
        assertPublish();
    }

    @OnClick(R.id.tv_collection)
    public void clickCollection() {
        assertCollection();
    }

    @OnClick(R.id.tv_wallet)
    public void clickWallet() {
        assertWallet();
    }

    @OnClick(R.id.tv_gift)
    public void clickGift() {
        assertGift();
    }

    @OnClick(R.id.tv_welcome)
    public void clickWelcome() {
        assertWelcome();
    }

    @OnClick(R.id.tv_start)
    public void clickStart() {
        assertStart();
    }

    @OnClick(R.id.ly_set)
    public void clickSet() {
        assertSet();
    }

    @OnClick(R.id.tv_help)
    public void clickHelp() {
        assertHelp();
    }

    private void assertHelp() {
        Intent intent = new Intent(getContext(), HelpActivity.class);
        startActivity(intent);
    }


    private void assertPublish() {
        Intent intent = new Intent(getContext(), PublishActivity.class);
        startActivity(intent);
    }

    private void assertCollection() {
        Intent intent = new Intent(getContext(), CollectionActivity.class);
        startActivity(intent);
    }

    private void assertWallet() {
        Intent intent = new Intent(getContext(), RuleActivity.class);
        startActivity(intent);
    }

    private void assertGift() {

        Intent intent = new Intent(getContext(), AboutActivity.class);
        startActivity(intent);

    }

    private void assertSet() {
        Intent intent = new Intent(getContext(), SetActivity.class);
        startActivity(intent);
    }

    private void assertStart() {
        Intent intent = new Intent(getActivity(), PlanetSortingActivity.class);
        startActivity(intent);

    }

    private void assertWelcome() {
        Intent intent = new Intent(getActivity(), WelcomeActivity.class);
        startActivity(intent);

    }

}
