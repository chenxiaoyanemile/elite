package com.planet.emily.elite.com.emily.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.PlanetIssue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emily on 2018/3/28
 */

public class MyCollectionRecyclerViewAdapter extends RecyclerView.Adapter<MyCollectionRecyclerViewAdapter.ViewHolder> {


    private LayoutInflater layoutInflater;


    private List<PlanetIssue> planetIssueList = new ArrayList<>();

    public MyCollectionRecyclerViewAdapter(Context context) {

        layoutInflater = LayoutInflater.from(context);

    }

    public void setCollectionItems(List<PlanetIssue> collectionItems) {
        this.planetIssueList = collectionItems;
        notifyItemMoved(0, collectionItems.size());
    }

    @Override
    public MyCollectionRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_my_collection, parent, false);
        return new MyCollectionRecyclerViewAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyCollectionRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.tv_collection_content.setText(planetIssueList.get(position).getSummary());
        holder.tv_collection_topic.setText(planetIssueList.get(position).getBelongPlanet().getPlanetName());
        holder.tv_collection_time.setText(planetIssueList.get(position).getCreatedAt());

        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return planetIssueList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_collection_content;
        TextView tv_collection_topic;
        TextView tv_collection_time;


        ViewHolder(View itemView) {
            super(itemView);
            tv_collection_content = itemView.findViewById(R.id.tv_collection_content);
            tv_collection_topic = itemView.findViewById(R.id.tv_collection_topic);
            tv_collection_time = itemView.findViewById(R.id.tv_collection_time);

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

    private MyCollectionRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(MyCollectionRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
