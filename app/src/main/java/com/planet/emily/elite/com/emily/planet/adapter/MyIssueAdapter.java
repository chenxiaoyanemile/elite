package com.planet.emily.elite.com.emily.planet.adapter;

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

public class MyIssueAdapter extends RecyclerView.Adapter<MyIssueAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;


    private List<PlanetIssue> planetIssueArrayList = new ArrayList<>();

    public MyIssueAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);

    }

    public void setPublishItems(List<PlanetIssue> planetIssues) {
        this.planetIssueArrayList = planetIssues;
        notifyItemMoved(0, planetIssueArrayList.size());
    }

    @Override
    public MyIssueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_project, parent, false);
        return new MyIssueAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyIssueAdapter.ViewHolder holder, int position) {


        holder.title.setText(planetIssueArrayList.get(position).getSummary());
        holder.version.setText(planetIssueArrayList.get(position).getVersion());
        holder.assignee.setText(planetIssueArrayList.get(position).getAssignee());
        holder.result.setText(planetIssueArrayList.get(position).getResult());
        holder.priority.setText(planetIssueArrayList.get(position).getPriority());
        holder.reporter.setText(planetIssueArrayList.get(position).getAuthor().getUsername());

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return planetIssueArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView version;
        TextView assignee;
        TextView result;
        TextView priority;
        TextView reporter;


        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_issue_tittle);
            version = itemView.findViewById(R.id.tv_issue_version);
            assignee = itemView.findViewById(R.id.tv_issue_assignee);
            result = itemView.findViewById(R.id.tv_issue_result);
            priority = itemView.findViewById(R.id.tv_issue_priority);
            reporter = itemView.findViewById(R.id.tv_issue_report);

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

    private MyIssueAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(MyIssueAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}

