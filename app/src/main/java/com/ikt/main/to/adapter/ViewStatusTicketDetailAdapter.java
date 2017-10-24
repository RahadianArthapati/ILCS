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
public class ViewStatusTicketDetailAdapter extends RecyclerView.Adapter<ViewStatusTicketDetailAdapter.ViewHolder> {

    private ArrayList<TruckActivitiesDetailObject> data;
    private Activity activity;
    private TapView listener;
    private TruckActivitiesDetailObject obj;

    public ViewStatusTicketDetailAdapter(Activity activity, ArrayList<TruckActivitiesDetailObject> data) {
        super();
        this.activity = activity;
        this.data = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(activity).inflate(R.layout.row_view_status_ticket_detail, parent, false);
        ViewHolder holder = new ViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
            obj = data.get(position);

            if (null == obj.getDriver() || obj.getDriver().equalsIgnoreCase("null")) {
                holder.txtViaAnnounce.setText(R.string.label_via_web);
                holder.txtViaOperation.setText(R.string.label_via_web);
                holder.txtViaComplete.setText(R.string.label_via_web);
                holder.txtViaLeft.setText(R.string.label_via_web);
            } else {
                holder.txtViaAnnounce.setText(R.string.label_via_mobile);
                holder.txtViaOperation.setText(R.string.label_via_mobile);
                holder.txtViaComplete.setText(R.string.label_via_mobile);
                holder.txtViaLeft.setText(R.string.label_via_mobile);
            }

            String an = obj.getAnnounce();
        Log.d("getAnnounce: ",an);
            if (null == obj.getAnnounce() || an.length() == 0 || obj.getAnnounce().equalsIgnoreCase("null")) {
                Drawable drAnnounce = ContextCompat.getDrawable(activity, R.drawable.step_1);
                holder.imgAnnounce.setImageDrawable(drAnnounce);
            } else {
                Drawable drAnnounce = ContextCompat.getDrawable(activity, R.drawable.step_1_success);
                holder.imgAnnounce.setImageDrawable(drAnnounce);
                holder.txtDateAnnounce.setText(obj.getAnnounce());
                holder.txtStatusAnnounce.setText(activity.getResources().getText(R.string.label_ANNOUNCE));
                holder.footer_layout_announce.setVisibility(View.VISIBLE);
                holder.imgAnnounce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.setAction(1, position, "announced");
                        notifyItemChanged(position);
                    }});
            }

            String op = obj.getOperation();
        Log.d("getOperation: ",op);
            if (null == obj.getOperation() || op.length() == 0 || obj.getOperation().equalsIgnoreCase("null")) {
                Drawable drOperation = ContextCompat.getDrawable(activity, R.drawable.step_2);
                holder.imgOperation.setImageDrawable(drOperation);
            } else {
                Drawable drOperation = ContextCompat.getDrawable(activity, R.drawable.step_2_success);
                holder.imgOperation.setImageDrawable(drOperation);
                holder.txtDateOperation.setText(obj.getOperation());
                holder.txtStatusOperation.setText(activity.getResources().getText(R.string.label_OPERATION));
                holder.footer_layout_operation.setVisibility(View.VISIBLE);
                holder.imgOperation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.setAction(1, position, "operation");
                        notifyItemChanged(position);
                    }
                });
            }

            String co = obj.getComplete();
        Log.d("getComplete: ",co);
            if (null == obj.getComplete() || co.length() == 0 || obj.getComplete().equalsIgnoreCase("null")) {
                Drawable drComplete = ContextCompat.getDrawable(activity, R.drawable.step_3);
                holder.imgComplete.setImageDrawable(drComplete);
            } else {
                Drawable drComplete = ContextCompat.getDrawable(activity, R.drawable.step_3_success);
                holder.imgComplete.setImageDrawable(drComplete);
                holder.txtDateComplete.setText(obj.getComplete());
                holder.txtStatusComplete.setText(activity.getResources().getText(R.string.label_COMPLETE));
                holder.footer_layout_complete.setVisibility(View.VISIBLE);
                holder.imgComplete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.setAction(1, position, "complete");
                        notifyItemChanged(position);
                    }
                });
            }

            String le = obj.getLeft();
        Log.d("getLeft: ",le);
            if (null == obj.getLeft() || le.length() == 0 || obj.getLeft().equalsIgnoreCase("null")) {
                Drawable drLeft = ContextCompat.getDrawable(activity, R.drawable.step_4);
                holder.imgLeft.setImageDrawable(drLeft);
            } else {
                Drawable drLeft = ContextCompat.getDrawable(activity, R.drawable.step_4_success);
                holder.imgLeft.setImageDrawable(drLeft);
                holder.txtDateLeft.setText(obj.getLeft());
                holder.txtStatusLeft.setText(activity.getResources().getText(R.string.label_LEFT));
                holder.footer_layout_left.setVisibility(View.VISIBLE);
                holder.imgLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.setAction(1, position, "left");
                        notifyItemChanged(position);
                    }
                });
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

        private TextView txtStatusAnnounce;
        private TextView txtStatusOperation;
        private TextView txtStatusComplete;
        private TextView txtStatusLeft;

        private TextView txtDateAnnounce;
        private TextView txtDateOperation;
        private TextView txtDateComplete;
        private TextView txtDateLeft;

        private TextView txtViaAnnounce;
        private TextView txtViaOperation;
        private TextView txtViaComplete;
        private TextView txtViaLeft;

        private ImageView imgAnnounce;
        private ImageView imgOperation;
        private ImageView imgComplete;
        private ImageView imgLeft;

        private TableLayout footer_layout_announce;
        private TableLayout footer_layout_operation;
        private TableLayout footer_layout_complete;
        private TableLayout footer_layout_left;

        public ViewHolder(View itemView) {
            super(itemView);
            txtStatusAnnounce = (TextView) itemView.findViewById(R.id.txtStatusAnnounce);
            txtStatusOperation = (TextView) itemView.findViewById(R.id.txtStatusOperation);
            txtStatusComplete = (TextView) itemView.findViewById(R.id.txtStatusComplete);
            txtStatusLeft = (TextView) itemView.findViewById(R.id.txtStatusLeft);

            txtDateAnnounce = (TextView) itemView.findViewById(R.id.txtDateAnnounce);
            txtDateOperation = (TextView) itemView.findViewById(R.id.txtDateOperation);
            txtDateComplete = (TextView) itemView.findViewById(R.id.txtDateComplete);
            txtDateLeft = (TextView) itemView.findViewById(R.id.txtDateLeft);

            txtViaAnnounce = (TextView) itemView.findViewById(R.id.txtViaAnnounce);
            txtViaOperation = (TextView) itemView.findViewById(R.id.txtViaOperation);
            txtViaComplete = (TextView) itemView.findViewById(R.id.txtViaComplete);
            txtViaLeft = (TextView) itemView.findViewById(R.id.txtViaLeft);

            imgAnnounce = (ImageView) itemView.findViewById(R.id.imgAnnounce);
            imgOperation = (ImageView) itemView.findViewById(R.id.imgOperation);
            imgComplete = (ImageView) itemView.findViewById(R.id.imgComplete);
            imgLeft = (ImageView) itemView.findViewById(R.id.imgLeft);

            footer_layout_announce = (TableLayout) itemView.findViewById(R.id.footer_layout_announce);
            footer_layout_operation = (TableLayout) itemView.findViewById(R.id.footer_layout_operation);
            footer_layout_complete = (TableLayout) itemView.findViewById(R.id.footer_layout_complete);
            footer_layout_left = (TableLayout) itemView.findViewById(R.id.footer_layout_left);

        }
    }
}
