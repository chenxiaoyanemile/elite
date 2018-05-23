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
import com.planet.emily.elite.bean.PlanetCard;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyShareAdapter extends RecyclerView.Adapter<MyShareAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;


    private List<PlanetCard> planetCardArrayList = new ArrayList<>();


    private Context mContext;

    public MyShareAdapter(Context context) {
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);

    }

    public void setPlanetCardArrayList(List<PlanetCard> planetCards) {
        this.planetCardArrayList = planetCards;
        notifyItemMoved(0, planetCardArrayList.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_issues, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyShareAdapter.ViewHolder holder, int position) {

        holder.time.setText(planetCardArrayList.get(position).getCreatedAt());
        holder.username.setText(planetCardArrayList.get(position).getAuthor().getUsername());
        holder.content.setText(planetCardArrayList.get(position).getCardContent());

        holder.itemView.setTag(position);

        String url = planetCardArrayList.get(position).getAuthor().getPhoto().getUrl();
        Glide.with(mContext)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.im_me)
                .into(holder.author_avatar);

    }

    @Override
    public int getItemCount() {
        return planetCardArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView author_avatar;
        TextView username;
        TextView time;
        TextView content;


        ViewHolder(View itemView) {
            super(itemView);
            author_avatar = itemView.findViewById(R.id.iv_share_user_avatar);
            username = itemView.findViewById(R.id.tv_share_author_name);
            time = itemView.findViewById(R.id.tv_share_time);
            content = itemView.findViewById(R.id.tv_share_content);

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
