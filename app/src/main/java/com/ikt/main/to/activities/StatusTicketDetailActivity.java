package com.ikt.main.to.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.R;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.DriverObject;
import com.ikt.main.to.object.TicketObjectDetail;
import com.ikt.main.to.parser.AssignDriverParser;
import com.ikt.main.to.parser.NewTicketObjectDetailParser;
import com.ikt.main.to.parser.RetrieveEditVisitParser;
import com.ikt.main.to.parser.SimpleParser;
import com.ikt.main.to.parser.TicketObjectDetailParser;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;
import com.ikt.main.to.util.Utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class StatusTicketDetailActivity extends BaseActivity2 implements IHttpResponse, View.OnClickListener, TapView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.buttonViewStatus)
    Button buttonViewStatus;
    @Bind(R.id.buttonAssign)
    Button buttonAssign;
    @Bind(R.id.text_carrier)
    TextView text_carrier;
    @Bind(R.id.text_transport_mode)
    TextView text_transport_mode;
    @Bind(R.id.text_driver)
    TextView text_driver;
    @Bind(R.id.text_license_plat)
    TextView text_license_plat;
    @Bind(R.id.text_type)
    TextView text_type;
    @Bind(R.id.text_outgoing_voy_nr)
    TextView text_outgoing_voy_nr;
    @Bind(R.id.text_outgoing_vessel)
    TextView text_outgoing_vessel;
    @Bind(R.id.text_time_begin)
    TextView text_time_begin;
    @Bind(R.id.text_time_end)
    TextView text_time_end;
    @Bind(R.id.text_load_lanes)
    TextView text_load_lanes;
    @Bind(R.id.spinnerNumberPickupCar)
    Spinner spinnerNumberPickupCar;
    @Bind(R.id.imageViewBarcode)
    ImageView imageViewBarcode;
    @Bind(R.id.txtVisitId)
    TextView txtVisitId;
    @Bind(R.id.txt_numberPickupCar)
    TextView txtNumberPickupCar;
    @Bind(R.id.view_car_pickup)
    LinearLayout viewCarPickup;
    @Bind(R.id.txt_numberVesselCar)
    TextView txtNumberVesselCar;
    @Bind(R.id.spinnerNumberVesselCar)
    Spinner spinnerNumberVesselCar;
    @Bind(R.id.view_car_vessel)
    LinearLayout viewCarVessel;
    @Bind(R.id.buttonEdit)
    Button buttonEdit;
    @Bind(R.id.txt_info)
    TextView txt_info;
    @Bind(R.id.view_car_info)
    LinearLayout view_car_info;
    @Bind(R.id.llButton)
    LinearLayout llButton;
    @Bind(R.id.buttonAssignNew)
    Button buttonAssignNew;
    @Bind(R.id.buttonDelete)
    Button buttonDelete;
    @Bind(R.id.buttonDummy)
    Button buttonDummy;
    @Bind(R.id.text_phone)
    TextView text_phone;
    @Bind(R.id.trPhone)
    TableRow trPhone;

    private ActionBar mActionBar;
    private String visitId;
    private String orgId, session, userId;
    private int smallerDimension;
    private boolean isAssign;
    private TicketObjectDetail mObjectDetail;
    private int pos = -1;
    // Satrio
    private final Context context = this;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_ticket_detail);
        ButterKnife.bind(this);
        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, this);
        session = PreferenceManagers.getData(Config.KEY_SESSION, this);
        userId = PreferenceManagers.getData(Config.KEY_USER_ID, this);
        Bundle bundle = getIntent().getExtras();

        //Find screen size
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        if (bundle != null) {
            pos = bundle.getInt("position", -1);
            visitId = bundle.getString("visitId");
            isAssign = bundle.getBoolean("isAssign");
            // Satrio
            if (!isAssign) {
                buttonAssign.setVisibility(View.GONE);
            }
        }

        setToolBar();
        getTicketDetail();

        buttonViewStatus.setOnClickListener(this);
        buttonAssign.setOnClickListener(this);
        buttonEdit.setOnClickListener(this);
        buttonAssignNew.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
    }

    private void getTicketDetail() {
        HttpTask task = new HttpTask(this, Config.URL_GET_TICKET_DETAIL, 1001, Config.POST, this, NewTicketObjectDetailParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_ORG_ID, orgId));
        post.add(new BasicNameValuePair(Config.KEY_SESSION, session));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, userId));
        post.add(new BasicNameValuePair(Config.KEY_VISIT_ID, visitId));

        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    private void getVisitEdit(){
        HttpTask task = new HttpTask(this, Config.URL_EDIT_VISIT_RETRIEVE, 1001, Config.POST, this, RetrieveEditVisitParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_SESSION, session));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, userId));
        post.add(new BasicNameValuePair(Config.KEY_VISIT_ID, visitId));

        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    private void deleteVisit(String id) {
        HttpTask task = new HttpTask(this, Config.URL_DELETE_VISIT, 1001, Config.POST, this, SimpleParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("visit_id", id));
        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    private void setToolBar() {
        setSupportActionBar(toolbar);

        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean isAvailableSearchBtn() {
        return false;
    }

    @Override
    public boolean isAvailableMyAccBtn() {
        return false;
    }

    @Override
    public boolean isAvailableLangBtn() {
        return false;
    }

    @Override
    public boolean isAvailableNotifBtn() {
        return false;
    }

    @Override
    public boolean isAvailableSignout() {
        return false;
    }

    @Override
    public boolean isAvailableRefreshBtn() {
        return false;
    }

    @Override
    public void onStarted(Context context, int pid) {
        Utility.hideKeyboard(this);
    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if (result instanceof NewTicketObjectDetailParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                ArrayList<TicketObjectDetail> ticketDetailsArr = VectorModel.getInstance().getTicketObjectDetails();
                if (ticketDetailsArr != null) {
                    mObjectDetail = ticketDetailsArr.get(0);
                    text_carrier.setText(mObjectDetail.getCarrier());
                    text_transport_mode.setText("Truck"); //?? truck
                    text_driver.setText(mObjectDetail.getDriverName().equalsIgnoreCase("null") ? "-" : mObjectDetail.getDriverName()+" "+mObjectDetail.getPhone());
                    text_type.setText("Backload");
                    text_outgoing_voy_nr.setText(mObjectDetail.getOutGoingVoyage().equalsIgnoreCase("null") ? "-" : mObjectDetail.getOutGoingVoyage());
                    text_outgoing_vessel.setText(mObjectDetail.getOutGoingVessel().equalsIgnoreCase("null") ? "-" : mObjectDetail.getOutGoingVessel());
                    text_time_begin.setText(mObjectDetail.getBegin());
                    text_time_end.setText(mObjectDetail.getEnd());
                    text_load_lanes.setText(mObjectDetail.getAreaOut().equalsIgnoreCase("null") ? "-" : mObjectDetail.getAreaOut());
                    imageViewBarcode.setDrawingCacheEnabled(true);
                    Bitmap bitmap = Utility.generateBarCode(visitId, imageViewBarcode.getDrawingCache(), smallerDimension);
                    imageViewBarcode.setImageBitmap(bitmap);
                    txtVisitId.setText(visitId);
                    // Satrio
//                    mObjectDetail.setPhone("08999106544");
                    text_phone.setText(mObjectDetail.getPhone());
                    Log.d("getPhone: ",mObjectDetail.getPhone());
                    if (mObjectDetail.getPhone().equalsIgnoreCase("null") || null == mObjectDetail.getPhone() || mObjectDetail.getPhone().length() == 0) {
                        trPhone.setVisibility(View.GONE);
                    } else {
                        trPhone.setVisibility(View.VISIBLE);
                    }
                    if (!mObjectDetail.getInfo().equalsIgnoreCase("null")){
                        view_car_info.setVisibility(View.VISIBLE);
                    }
                    if (mObjectDetail.isDeletable() || mObjectDetail.isNeed_assign() || mObjectDetail.isEditable()) {
                        llButton.setVisibility(View.VISIBLE);
                    }
//                    mObjectDetail.setEditable(true);
//                    mObjectDetail.setDeletable(true);
//                    mObjectDetail.setNeed_assign(true);
                    if (mObjectDetail.isNeed_assign() && mObjectDetail.isDeletable()){
                        buttonAssignNew.setVisibility(View.VISIBLE);
                        buttonDelete.setVisibility(View.VISIBLE);
                        ViewGroup.LayoutParams param = buttonDummy.getLayoutParams();
                        buttonAssignNew.setLayoutParams(param);
                        buttonDelete.setLayoutParams(param);
                    }
                    if (mObjectDetail.isNeed_assign() && mObjectDetail.isEditable()){
                        buttonAssignNew.setVisibility(View.VISIBLE);
                        buttonEdit.setVisibility(View.VISIBLE);
                        ViewGroup.LayoutParams param = buttonDummy.getLayoutParams();
                        buttonEdit.setLayoutParams(param);
                        buttonAssignNew.setLayoutParams(param);
                    }
                    if (mObjectDetail.isEditable() && mObjectDetail.isDeletable()){
                        buttonEdit.setVisibility(View.VISIBLE);
                        buttonDelete.setVisibility(View.VISIBLE);
                        ViewGroup.LayoutParams param = buttonDummy.getLayoutParams();
                        buttonEdit.setLayoutParams(param);
                        buttonDelete.setLayoutParams(param);
                    }
                    if (mObjectDetail.isNeed_assign()){
                        buttonAssignNew.setVisibility(View.VISIBLE);
                    }
                    if (mObjectDetail.isDeletable()){
                        buttonDelete.setVisibility(View.VISIBLE);
                    }
                    if (mObjectDetail.isEditable()){
                        buttonEdit.setVisibility(View.VISIBLE);
                    }
                    txt_info.setText(mObjectDetail.getInfo());
                    text_license_plat.setText(mObjectDetail.getLicenseplate().equalsIgnoreCase("null") ? "-" : mObjectDetail.getLicenseplate());
                    // end Satrio

//                    List<String> list = new ArrayList<String>();
                    int amountCarPickup = Integer.parseInt(mObjectDetail.getAmountCarPickup());
                    int amountCarVessel = Integer.parseInt(mObjectDetail.getAmountCarVessel());

                    if (amountCarPickup > 0) {
                        txtNumberPickupCar.setText(String.valueOf(amountCarPickup));
                        viewCarPickup.setVisibility(View.VISIBLE);
                    }
                    if (amountCarVessel > 0) {
                        txtNumberVesselCar.setText(String.valueOf(amountCarVessel));
                        viewCarVessel.setVisibility(View.VISIBLE);
                    }
                    if (!isAssign) {
                        buttonAssign.setVisibility(View.GONE);
                    }
                }
            }
        } else if (result instanceof AssignDriverParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                Toast.makeText(this, "Assign Driver Success", Toast.LENGTH_SHORT).show();
                buttonAssignNew.setVisibility(View.GONE);
                mObjectDetail.setNeed_assign(false);
                if (!mObjectDetail.isDeletable() && !mObjectDetail.isNeed_assign() && !mObjectDetail.isEditable()) {
                    llButton.setVisibility(View.GONE);
                }
            }
        }else if(result instanceof RetrieveEditVisitParser){
            IParser parser = (IParser) result;
            if(!parser.isError()){
                goToEditVisit();
            }
        } else if (result instanceof SimpleParser) {
            IParser parser = (IParser) result;
            if(!parser.isError()){
                setAction(0, 0, "info");
            }
        }
    }

    private void goToEditVisit(){
        Intent i = new Intent(this, EditVisitActivity.class);
        i.putExtra("visitId", visitId);
        startActivity(i);
    }

    @Override
    public void onFailed(Context context, int pid, Object result) {
        IParser parser = (IParser) result;
        Toast.makeText(this, parser.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleted(Context context, int pid) {
    }

    @Override
    public void onClick(View view) {
        if (view == buttonViewStatus) {
            Intent i = new Intent(this, ViewStatusTicketDetailActivity.class);
            i.putExtra("position", pos);
            i.putExtra("visitId", visitId);
            i.putExtra("driver", mObjectDetail.getDriverName());
            i.putExtra("isAssign", isAssign);
            i.putExtra("truck", mObjectDetail.getPlatNo());
            i.putExtra("desc", "-");
            startActivity(i);
        }
        if (view == buttonAssign) {
            Intent i = new Intent(this, SearchDriverActivity.class);
            startActivityForResult(i, 100);
        }
        if (view == buttonEdit){
            getVisitEdit();
        }
        if (view == buttonDelete){
            // dialog
            dialogs(null, null, null);
        }
        if (view == buttonAssignNew){
            Intent i = new Intent(this, SearchDriverActivity.class);
            startActivityForResult(i, 100);
        }
    }

    // Satrio
    private void dialogs(String info1, String info2, String info3){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.d_info);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Button btnConfirm = (Button) dialog.findViewById(R.id.btnNext);
        final TextView tvDialog = (TextView) dialog.findViewById(R.id.tvDialog);
        tvDialog.setText("DELETE TICKET");
        final TextView tvTitle1 = (TextView) dialog.findViewById(R.id.tvTitle1);
        tvTitle1.setText("Apakah anda yakin ?");
        final TextView tvTitle2 = (TextView) dialog.findViewById(R.id.tvTitle2);
        tvTitle2.setVisibility(View.GONE);
        final TextView tvTitle3 = (TextView) dialog.findViewById(R.id.tvTitle3);
        tvTitle3.setVisibility(View.GONE);
        final TextView tvInfo1 = (TextView) dialog.findViewById(R.id.tvInfo1);
        tvInfo1.setVisibility(View.GONE);
        final TextView tvInfo2 = (TextView) dialog.findViewById(R.id.tvInfo2);
        tvInfo2.setVisibility(View.GONE);
        final TextView tvInfo3 = (TextView) dialog.findViewById(R.id.tvInfo3);
        tvInfo3.setVisibility(View.GONE);
        btnConfirm.setText("Delete");
        btnConfirm.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                // hit delete jika sukses masuk setAction
                deleteVisit(visitId);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            Bundle arguments = data.getExtras();
            DriverObject driver = (DriverObject) arguments.getSerializable("data");
            String driverId = driver.getDriverId();
            assignDriver(driverId);
        }
    }

    private void assignDriver(String driverId) {
        HttpTask task = new HttpTask(this, Config.URL_ASSIGN_DRIVER, 1001, Config.POST, this, AssignDriverParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("user_id", userId));
        post.add(new BasicNameValuePair("session_id", session));
        post.add(new BasicNameValuePair("driver_id", driverId));
        post.add(new BasicNameValuePair("visit_id", visitId));

        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (pos == -1) {
                Intent i = new Intent(this, HomeActivity.class);
                i.putExtra("pos", 1);
                startActivity(i);
                finish();
            } else {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (pos == -1) {
            Intent i = new Intent(this, HomeActivity.class);
            i.putExtra("pos", 1);
            startActivity(i);
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void setAction(int type, int position, String name) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("pos", 1);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP /*| Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK*/);
        startActivity(intent);
        finish();
    }
}