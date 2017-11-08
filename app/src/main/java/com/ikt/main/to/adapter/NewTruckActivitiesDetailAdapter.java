package com.ikt.main.to.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.object.TruckActivitiesDetailObject;

import java.util.ArrayList;

/**
 * Created by Arifin on 3/15/16.
 */
public class NewTruckActivitiesDetailAdapter extends RecyclerView.Adapter<NewTruckActivitiesDetailAdapter.ViewHolder> {

    private ArrayList<TruckActivitiesDetailObject> data;
    private Activity activity;
    private TapView listener;
    private TruckActivitiesDetailObject obj;

    public NewTruckActivitiesDetailAdapter(Activity activity, ArrayList<TruckActivitiesDetailObject> data) {
        super();
        this.activity = activity;
        this.data = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(activity).inflate(R.layout.new_row_truck_activities_detail_layout, parent, false);
        ViewHolder holder = new ViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        obj = data.get(position);

            String an = obj.getAnnounce();
            if (null == obj.getAnnounce() || an.length() == 0 || obj.getAnnounce().equalsIgnoreCase("null")) {
                Drawable drAnnounce = ContextCompat.getDrawable(activity, R.drawable.ic_announce0);
                holder.imgStatus.setImageDrawable(drAnnounce);
                holder.txtTRK.setText(obj.getVisitId());
                holder.txtPlatNo.setText(obj.getTruckCode());
                holder.txtStatus.setText("Status");
                holder.txtDate.setText("Not Announced Yet");
                if (null == obj.getDriver() || obj.getDriver().equalsIgnoreCase("null")) {
                    holder.txtVia.setText(R.string.label_via_web);
                    holder.txtDriver.setText("");
                } else {
                    holder.txtVia.setText(R.string.label_via_mobile);
                    holder.txtDriver.setText(obj.getDriver());
                }

            } else {

                //test
                holder.txtTRK.setText(obj.getVisitId());
                holder.txtPlatNo.setText(obj.getTruckCode());
                holder.txtDriver.setText(obj.getDriver());

                if (null == obj.getDriver() || obj.getDriver().equalsIgnoreCase("null")) {
                    holder.txtVia.setText(R.string.label_via_web);
                    holder.txtDriver.setText("");
                } else {
                    holder.txtVia.setText(R.string.label_via_mobile);
                }
                //test

                Drawable drAnnounce1 = ContextCompat.getDrawable(activity, R.drawable.ic_announce1);
                holder.imgStatus.setImageDrawable(drAnnounce1);
                holder.txtDate.setText(an);
                holder.txtStatus.setText(activity.getResources().getText(R.string.label_ANNOUNCE).toString());
            }

            String op = obj.getOperation();
            if (null == obj.getOperation() || op.length() == 0 || obj.getOperation().equalsIgnoreCase("null")) {
                //do nothing
            } else {
                Drawable drOperation = ContextCompat.getDrawable(activity, R.drawable.ic_operational);
                holder.imgStatus.setImageDrawable(drOperation);
                holder.txtDate.setText(op);
                holder.txtStatus.setText(activity.getResources().getText(R.string.label_OPERATION).toString());
            }

            String co = obj.getComplete();
            if (null == obj.getComplete() || co.length() == 0 || obj.getComplete().equalsIgnoreCase("null")) {
                // do nothing
            } else {
                Drawable drComplete = ContextCompat.getDrawable(activity, R.drawable.ic_completed);
                holder.imgStatus.setImageDrawable(drComplete);
                holder.txtDate.setText(co);
                holder.txtStatus.setText(activity.getResources().getText(R.string.label_COMPLETE).toString());
            }

            String le = obj.getLeft();
            if (null == obj.getLeft() || le.length() == 0 || obj.getLeft().equalsIgnoreCase("null")) {
                // do nothing
            } else {
                Drawable drLeft = ContextCompat.getDrawable(activity, R.drawable.ic_left);
                holder.imgStatus.setImageDrawable(drLeft);
                holder.txtDate.setText(le);
                holder.txtStatus.setText(activity.getResources().getText(R.string.label_LEFT).toString());
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

        private TextView txtStatus;
        private TextView txtDate;
        private TextView txtVia;
        private TextView txtTRK;
        private TextView txtPlatNo;
        private TextView txtDriver;
        private ImageView imgStatus;
        private CardView truckActivitiesLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            txtVia = (TextView) itemView.findViewById(R.id.txtVia);
            txtTRK = (TextView) itemView.findViewById(R.id.txtTRK);
            txtPlatNo = (TextView) itemView.findViewById(R.id.txtPlatNo);
            txtDriver = (TextView) itemView.findViewById(R.id.txtDriver);
            imgStatus = (ImageView) itemView.findViewById(R.id.imgStatus);
            truckActivitiesLayout = (CardView) itemView.findViewById(R.id.rowTruckActivities);
        }
    }
}
