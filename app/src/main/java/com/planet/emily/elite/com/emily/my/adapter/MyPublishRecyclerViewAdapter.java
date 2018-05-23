package com.planet.emily.elite.com.emily.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.PlanetCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emily on 2018/3/28
 */

public class MyPublishRecyclerViewAdapter extends RecyclerView.Adapter<MyPublishRecyclerViewAdapter.ViewHolder> {


    private LayoutInflater layoutInflater;


    private List<PlanetCard> planetCardArrayList = new ArrayList<>();

    public MyPublishRecyclerViewAdapter(Context context) {

        layoutInflater = LayoutInflater.from(context);

    }

    public void setPlanetCardArrayList(List<PlanetCard> planetCards) {
        this.planetCardArrayList = planetCards;
        notifyItemMoved(0, planetCards.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_my_publish, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.item_publish_topic.setText(planetCardArrayList.get(position).getCardTitle());
        holder.item_publish_content.setText(planetCardArrayList.get(position).getCardContent());
        holder.item_publish_community.setText(planetCardArrayList.get(position).getBelongPlanet().getPlanetName());
        holder.item_publish_time.setText(planetCardArrayList.get(position).getCreatedAt());

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return planetCardArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_publish_topic;
        TextView item_publish_content;
        TextView item_publish_community;
        TextView item_publish_time;


        ViewHolder(View itemView) {
            super(itemView);
            item_publish_topic = itemView.findViewById(R.id.item_publish_topic);
            item_publish_content = itemView.findViewById(R.id.item_publish_content);
            item_publish_community = itemView.findViewById(R.id.item_publish_community);
            item_publish_time = itemView.findViewById(R.id.item_publish_time);

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
