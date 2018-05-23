package com.planet.emily.elite.com.emily.dynamics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.Comment;
import com.planet.emily.elite.com.emily.planet.PlanetActivity;
import com.planet.emily.elite.view.MyCommentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.cv_comment_avatar)
    CircleImageView userAvatar;

    @BindView(R.id.tv_comment_name)
    TextView commenter;

    @BindView(R.id.tv_create_time)
    TextView crateTime;

    @BindView(R.id.tv_comment_content)
    TextView commentContent;

    @BindView(R.id.tv_comment_like)
    TextView like;

    @BindView(R.id.tv_comment_commenter)
    TextView comment;

    @BindView(R.id.hide_down)
    TextView hide_down;

    @BindView(R.id.comment_content)
    EditText comment_content;

    @BindView(R.id.comment_send)
    Button comment_send;

    @BindView(R.id.rl_comment)
    RelativeLayout rl_comment;

    private MyCommentAdapter myCommentAdapter;
    private List<Comment> data;

    private String id;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        getUserData();
        initView();
    }


    private void initView() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String username = intent.getStringExtra("username");
        String time = intent.getStringExtra("time");
        String content = intent.getStringExtra("content");

        Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.im_me)
                .into(userAvatar);

        commenter.setText(username);
        crateTime.setText(time);
        commentContent.setText(content);

        // 初始化评论列表
        ListView comment_list = findViewById(R.id.comment_list);
        // 初始化数据
        data = new ArrayList<>();
        // 初始化适配器
        myCommentAdapter = new MyCommentAdapter(getApplicationContext(), data);
        // 为评论列表设置适配器
        comment_list.setAdapter(myCommentAdapter);
        setListener();
    }

    /**
     * 设置监听
     */
    public void setListener() {
        comment.setOnClickListener(this);
        hide_down.setOnClickListener(this);
        comment_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_comment_commenter:
                rl_comment.setVisibility(View.VISIBLE);
                // 弹出输入法
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                // 显示评论框
                break;
            case R.id.hide_down:
                // 隐藏输入法，然后暂存当前输入框的内容，方便下次使用
                InputMethodManager im = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(comment_content.getWindowToken(), 0);
                break;
            case R.id.comment_send:
                sendComment();
                break;
            default:
                break;
        }
    }

    /**
     * 发送评论
     */
    public void sendComment() {
        if (comment_content.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "评论不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            // 生成评论数据
            Comment comment = new Comment();
            comment.setName(username + "：");
            comment.setContent(comment_content.getText().toString());
            myCommentAdapter.addComment(comment);
            // 发送完，清空输入框
            comment_content.setText("");
            Toast.makeText(getApplicationContext(), "评论成功！", Toast.LENGTH_SHORT).show();
        }
    }

    private void getUserData() {
        SharedPreferences preferences = getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        id = preferences.getString("userId", "");
        username = preferences.getString("userName", "");
    }

    @OnClick(R.id.tv_return)
    public void clickReturn() {
        finish();
    }

    Boolean flag = false;

    @OnClick(R.id.tv_comment_like)
    public void clickLike() {

        if (!flag) {
            like.setBackgroundResource(R.drawable.sel_comment_like);
            flag = true;
        } else {
            like.setBackgroundResource(R.drawable.shape_item_admire);
            flag = false;
        }
    }

    @OnClick(R.id.tv_enter)
    public void clickEnter() {
        Intent intent = new Intent(CommentActivity.this, PlanetActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
