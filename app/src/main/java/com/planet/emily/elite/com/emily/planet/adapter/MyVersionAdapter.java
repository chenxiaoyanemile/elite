package com.planet.emily.elite.com.emily.planet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.planet.emily.elite.R;
import com.planet.emily.elite.bean.PlanetProject;

import java.util.ArrayList;
import java.util.List;

public class MyVersionAdapter extends RecyclerView.Adapter<MyVersionAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;


    private List<PlanetProject> planetProjectArrayList = new ArrayList<>();


    private Context mContext;

    public MyVersionAdapter(Context context) {
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);

    }

    public void setPlanetProjectArrayList(List<PlanetProject> planetProjects) {
        this.planetProjectArrayList = planetProjects;
        notifyItemMoved(0, planetProjectArrayList.size());
    }

    @Override
    public MyVersionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_backlog, parent, false);
        return new MyVersionAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyVersionAdapter.ViewHolder holder, int position) {

        holder.tv_project_version.setText(planetProjectArrayList.get(position).getVersion());
        holder.tv_project_status.setText(planetProjectArrayList.get(position).getStatus());
        holder.tv_project_time.setText(planetProjectArrayList.get(position).getCreatedAt());
        holder.tv_project_des.setText(planetProjectArrayList.get(position).getDescription());

        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return planetProjectArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_project_version;
        TextView tv_project_status;
        TextView tv_project_time;
        TextView tv_project_des;


        ViewHolder(View itemView) {
            super(itemView);
            tv_project_version = itemView.findViewById(R.id.tv_project_version);
            tv_project_status = itemView.findViewById(R.id.tv_project_status);
            tv_project_time = itemView.findViewById(R.id.tv_project_time);
            tv_project_des = itemView.findViewById(R.id.tv_project_des);

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

    private MyVersionAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(MyVersionAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
