package com.planet.emily.elite.com.emily.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.MyCollectionItem;

import java.util.ArrayList;

public class MyPlanetSortRecyclerViewAdapter extends RecyclerView.Adapter<MyPlanetSortRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;


    private ArrayList<MyCollectionItem> collectionItems = new ArrayList<>();

    public MyPlanetSortRecyclerViewAdapter(Context context) {

        layoutInflater = LayoutInflater.from(context);

    }

    public void setCollectionItems(ArrayList<MyCollectionItem> collectionItems) {
        this.collectionItems = collectionItems;
        notifyItemMoved(0, collectionItems.size());
    }

    @Override
    public MyPlanetSortRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_planet_rank, parent, false);
        return new MyPlanetSortRecyclerViewAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyPlanetSortRecyclerViewAdapter.ViewHolder holder, int position) {

      /*  holder.tv_planet_name.setText(collectionItems.get(0).getContent());
        holder.tv_planet_founder.setText(collectionItems.get(0).getCommunity());
        holder.itemView.setTag(position);*/


    }

    @Override
    public int getItemCount() {
        return collectionItems.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_rank_sort;
        ImageView iv_planet_avatar;

        TextView tv_planet_name;
        TextView tv_planet_founder;
        ImageView iv_planet_enter;



        ViewHolder(View itemView) {
            super(itemView);
            iv_rank_sort = itemView.findViewById(R.id.iv_rank_sort);
            iv_planet_avatar = itemView.findViewById(R.id.iv_planet_avatar);
            tv_planet_name = itemView.findViewById(R.id.tv_planet_name);
            tv_planet_founder = itemView.findViewById(R.id.tv_planet_founder);
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
