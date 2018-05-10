package com.planet.emily.elite.com.emily.dynamics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.Comment;
import com.planet.emily.elite.com.emily.planet.PlanetActivity;
import com.planet.emily.elite.view.MyCommentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.comment)
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
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
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
            case R.id.comment:
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
            comment.setName("评论者" + (data.size() + 1) + "：");
            comment.setContent(comment_content.getText().toString());
            myCommentAdapter.addComment(comment);
            // 发送完，清空输入框
            comment_content.setText("");
            Toast.makeText(getApplicationContext(), "评论成功！", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.tv_return)
    public void clickReturn() {

    }


    @OnClick(R.id.tv_enter)
    public void clickEnter() {
        Intent intent = new Intent(CommentActivity.this, PlanetActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

    }


}
