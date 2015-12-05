package com.ryctabo.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ryctabo.test.activity.R;
import com.ryctabo.test.model.NavDrawerItem;

import java.util.Collections;
import java.util.List;

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {

    private List<NavDrawerItem> data = Collections.emptyList();

    private LayoutInflater inflater;

    public NavigationDrawerAdapter(List<NavDrawerItem> data, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = data.get(position);
        holder.title.setText(current.getTitle());
        switch (position) {
            case 0:
                holder.icon.setImageResource(R.drawable.ic_action_home);
                break;
            case 1:
                holder.icon.setImageResource(R.drawable.ic_action_people);
                break;
            case 2:
                holder.icon.setImageResource(R.drawable.ic_action_organization);
                break;
            case 3:
                holder.icon.setImageResource(R.drawable.ic_action_business);
                break;
            case 4:
                holder.icon.setImageResource(R.drawable.ic_action_activity);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class  MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title_icon);
            this.icon = (ImageView) itemView.findViewById(R.id.icon_item);
        }
    }

}
