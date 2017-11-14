package com.ikt.main.to.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.object.TruckActivitiesDetailObject;

import java.util.ArrayList;

/**
 * Created by tunggul.jati
 */
public class New2TruckActivitiesDetailAdapter extends RecyclerView.Adapter<New2TruckActivitiesDetailAdapter.ViewHolder> {

    private ArrayList<TruckActivitiesDetailObject> data;
    private Activity activity;
    private TapView listener;
    private TruckActivitiesDetailObject obj;

    public New2TruckActivitiesDetailAdapter(Activity activity, ArrayList<TruckActivitiesDetailObject> data) {
        super();
        this.activity = activity;
        this.data = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(activity).inflate(R.layout.new2_row_truck_activities_detail_layout, parent, false);
        ViewHolder holder = new ViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        obj = data.get(position);
        holder.txtTRK.setText(obj.getVisitId());
        holder.txtTime.setText(obj.getTime());
        holder.txtStatus.setText(obj.getStatus());
        holder.txtType.setText(obj.getType());
        holder.txtDriver.setText(obj.getDriver());
        String icon = obj.getIcon();
        if (null == icon || icon.length() == 0 || icon.equalsIgnoreCase("null")) {
            Drawable dr0 = ContextCompat.getDrawable(activity, R.drawable.ic_announce0);
            holder.imgIcon.setImageDrawable(dr0);
        } else {
        int ic = Integer.parseInt(obj.getIcon());
        switch (ic) {
            case 1:
                Drawable dr1 = ContextCompat.getDrawable(activity, R.drawable.ic1);
                holder.imgIcon.setImageDrawable(dr1);
                break;
            case 2:
                Drawable dr2 = ContextCompat.getDrawable(activity, R.drawable.ic2);
                holder.imgIcon.setImageDrawable(dr2);
                break;
            case 3:
                Drawable dr3 = ContextCompat.getDrawable(activity, R.drawable.ic3);
                holder.imgIcon.setImageDrawable(dr3);
                break;
            case 4:
                Drawable dr4 = ContextCompat.getDrawable(activity, R.drawable.ic4);
                holder.imgIcon.setImageDrawable(dr4);
                break;
            case 5:
                Drawable dr5 = ContextCompat.getDrawable(activity, R.drawable.ic5);
                holder.imgIcon.setImageDrawable(dr5);
                break;
        }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setListener(TapView listener) {
        this.listener = listener;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgIcon;
        private TextView txtTRK;
        private TextView txtTime;
        private TextView txtStatus;
        private TextView txtType;
        private TextView txtDriver;

        public ViewHolder(View itemView) {
            super(itemView);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
            txtTRK = (TextView) itemView.findViewById(R.id.txtTRK);
            txtTime = (TextView) itemView.findViewById(R.id.txtTime);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            txtType = (TextView) itemView.findViewById(R.id.txtType);
            txtDriver = (TextView) itemView.findViewById(R.id.txtDriver);
        }
    }
}
