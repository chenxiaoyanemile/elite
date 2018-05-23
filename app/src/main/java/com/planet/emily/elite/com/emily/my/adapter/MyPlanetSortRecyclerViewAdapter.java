package com.planet.emily.elite.com.emily.my.adapter;

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
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;

public class MyPlanetSortRecyclerViewAdapter extends RecyclerView.Adapter<MyPlanetSortRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;


    private List<PlanetInfo> planetInfoList = new ArrayList<>();

    private Context mContext;

    public MyPlanetSortRecyclerViewAdapter(Context context) {
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);

    }

    public void setCollectionItems(List<PlanetInfo> planetInfo) {
        this.planetInfoList = planetInfo;
        notifyItemMoved(0, planetInfo.size());
    }

    @Override
    public MyPlanetSortRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_planet_rank, parent, false);
        return new MyPlanetSortRecyclerViewAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyPlanetSortRecyclerViewAdapter.ViewHolder holder, int position) {


        holder.tv_planet_name.setText(planetInfoList.get(position).getPlanetName());
        holder.tv_planet_founder.setText(planetInfoList.get(position).getType());

        BmobFile file = planetInfoList.get(position).getPhoto();
        Glide.with(mContext)
                .load(file.getFileUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.topic_03_1x)
                .into(holder.iv_planet_avatar);

        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return planetInfoList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView iv_rank_sort;
        ImageView iv_planet_avatar;

        TextView tv_planet_name;
        TextView tv_planet_founder;
        ImageView iv_planet_enter;


        ViewHolder(View itemView) {
            super(itemView);
            iv_rank_sort = itemView.findViewById(R.id.iv_rank_sort);
            iv_planet_avatar = itemView.findViewById(R.id.iv_planet_sort_avatar);
            tv_planet_name = itemView.findViewById(R.id.tv_planet_sort_name);
            tv_planet_founder = itemView.findViewById(R.id.tv_planet_sort_founder);
            iv_planet_enter = itemView.findViewById(R.id.iv_planet_enter);

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
