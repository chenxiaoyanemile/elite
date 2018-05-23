package com.planet.emily.elite.com.emily.planet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyMembersAdapter extends RecyclerView.Adapter<MyMembersAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;


    private List<UserInfo> userInfoArrayList = new ArrayList<>();
    private Context mContext;

    public MyMembersAdapter(Context context) {
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);

    }

    public void setMembersList(List<UserInfo> userInfo) {
        this.userInfoArrayList = userInfo;
        notifyItemMoved(0, userInfoArrayList.size());
    }

    @Override
    public MyMembersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_members, parent, false);
        return new MyMembersAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyMembersAdapter.ViewHolder holder, int position) {


        holder.tv_members_username.setText(userInfoArrayList.get(position).getUsername());
        holder.tv_members_phone.setText(userInfoArrayList.get(position).getMobilePhoneNumber());

        BmobFile file = userInfoArrayList.get(position).getPhoto();
        Glide.with(mContext)
                .load(file.getFileUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.topic_03_1x)
                .into(holder.cv_members_avatar);

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return userInfoArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView cv_members_avatar;
        TextView tv_members_username;
        TextView tv_members_phone;


        ViewHolder(View itemView) {
            super(itemView);
            cv_members_avatar = itemView.findViewById(R.id.cv_members_avatar);
            tv_members_username = itemView.findViewById(R.id.tv_members_username);
            tv_members_phone = itemView.findViewById(R.id.tv_members_phone);

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

    private MyMembersAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(MyMembersAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
