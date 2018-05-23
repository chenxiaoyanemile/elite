package com.planet.emily.elite.com.emily.dynamics.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.Comment;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import de.hdodenhof.circleimageview.CircleImageView;

public class DynamicsRecyclerViewAdapter extends RecyclerView.Adapter<DynamicsRecyclerViewAdapter.ViewHolder> {


    private LayoutInflater layoutInflater;

    private Context mContext;


    private List<Comment> commentArrayList = new ArrayList<>();

    public DynamicsRecyclerViewAdapter(Context context) {
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);

    }

    public void setCommentArrayList(List<Comment> comments) {
        this.commentArrayList = comments;
        notifyItemMoved(0, comments.size());
    }

    @Override
    public DynamicsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_dynamics_message, parent, false);
        return new DynamicsRecyclerViewAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(DynamicsRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.tv_author_name.setText(commentArrayList.get(position).getCommenter().getUsername());
        holder.item_content.setText(commentArrayList.get(position).getContent());
        holder.item_time.setText(commentArrayList.get(position).getCreatedAt());
        holder.item_planet.setText(commentArrayList.get(position).getBelongCard().getCardTitle());

        BmobFile file = commentArrayList.get(position).getCommenter().getPhoto();
        Glide.with(mContext)
                .load(file.getFileUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.im_me)
                .into(holder.cv_author_avatar);

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView cv_author_avatar;
        TextView tv_author_name;
        TextView item_content;
        TextView item_time;
        TextView item_planet;


        ViewHolder(View itemView) {
            super(itemView);
            cv_author_avatar = itemView.findViewById(R.id.cv_author_avatar);
            tv_author_name = itemView.findViewById(R.id.tv_author_name);
            item_content = itemView.findViewById(R.id.tv_content);
            item_time = itemView.findViewById(R.id.tv_time);
            item_planet = itemView.findViewById(R.id.tv_planet_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClick(v, (int) v.getTag());
                    }
                }
            });
        }
    }


    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
