package com.planet.emily.elite.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.Comment;

import java.util.ArrayList;
import java.util.List;

public class MyCommentAdapter extends BaseAdapter {
    private Context context;
    private List<Comment> commentList = new ArrayList<>();

    public MyCommentAdapter(Context c) {
        this.context = c;
    }


    public void setCommentList(List<Comment> comment) {
        this.commentList = comment;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int i) {
        return commentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        // 重用convertView
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comment_planet, null);
            holder.comment_name = convertView.findViewById(R.id.comment_name);
            holder.comment_content = convertView.findViewById(R.id.comment_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 适配数据
        holder.comment_name.setText(commentList.get(i).getName());
        holder.comment_content.setText(commentList.get(i).getContent());
        return convertView;
    }

    /**
     * 添加一条评论,刷新列表
     *
     * @param comment
     */
    public void addComment(Comment comment) {
        commentList.add(comment);
        notifyDataSetChanged();
    }

    /**
     * 静态类，便于GC回收
     */
    public static class ViewHolder {
        TextView comment_name;
        TextView comment_content;
    }
}
