package com.planet.emily.elite.com.emily.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.PlanetInfo;

import java.util.ArrayList;

import cn.bmob.v3.datatype.BmobFile;

public class PlanetRecyclerViewAdapter extends RecyclerView.Adapter<PlanetRecyclerViewAdapter.ViewHolder> {


    private LayoutInflater layoutInflater;

    private Context mContext;


    private ArrayList<PlanetInfo> planetInfos = new ArrayList<>();

    public PlanetRecyclerViewAdapter(Context context) {
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);

    }

    public void setPublishItems(ArrayList<PlanetInfo> planetInfo) {
        this.planetInfos = planetInfo;
        notifyItemMoved(0, planetInfo.size());
    }

    @Override
    public PlanetRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_planet_community, parent, false);
        return new PlanetRecyclerViewAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(PlanetRecyclerViewAdapter.ViewHolder holder, int position) {


        holder.tv_planet_name.setText(planetInfos.get(position).getPlanetName());
        holder.tv_planet_founder.setText(planetInfos.get(position).getUserInfo().getUsername());

        BmobFile file = planetInfos.get(position).getPhoto();
        Glide.with(mContext)
                .load(file.getFileUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.topic_03_1x)
                .into(holder.iv_planet_avatar);

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return planetInfos.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_planet_avatar;
        TextView tv_planet_name;
        TextView tv_planet_founder;


        ViewHolder(View itemView) {
            super(itemView);
            iv_planet_avatar = itemView.findViewById(R.id.iv_planet_avatar);
            tv_planet_name = itemView.findViewById(R.id.tv_planet_name);
            tv_planet_founder = itemView.findViewById(R.id.tv_planet_founder);

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

    private PlanetRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(PlanetRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
