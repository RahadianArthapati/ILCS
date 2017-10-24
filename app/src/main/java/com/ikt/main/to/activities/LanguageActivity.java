package com.ikt.main.to.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.util.PreferenceManagers;
import com.ikt.main.to.util.Utility;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Arifin on 3/12/16.
 */
public class LanguageActivity extends BaseActivity2 {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.radioInd)
    AppCompatRadioButton radioInd;
    @Bind(R.id.radioEng)
    AppCompatRadioButton radioEng;

    private ActionBar mActionBar;
    // Satrio
    private final Context context = this;
    private int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_layout);
        ButterKnife.bind(this);
        setToolBar();
        touchIn();
        touchEng();
    }

    private void touchIn(){
        radioInd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean checked) {
                if (checked) {
                    check = 1;
                    radioEng.setChecked(false);
                    setResult(RESULT_OK);
                }
            }
        });
    }
    private void touchEng(){
        radioEng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean checked) {
                if (checked) {
                    check = 2;radioInd.setChecked(false);
                    setResult(RESULT_OK);
                }
            }
        });
    }

    private void setToolBar() {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.back_arrow);
            upArrow.setColorFilter(ContextCompat.getColor(this, android.R.color.white), PorterDuff.Mode.SRC_ATOP);
            mActionBar.setHomeAsUpIndicator(upArrow);
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setDisplayShowHomeEnabled(true);
            mActionBar.setTitle("");
        }
        toolbarTitle.setText(getResources().getString(R.string.action_language));
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

    // Satrio
    @OnClick(R.id.btnConfirmLang)
    void showDialoConfirm()
    {
        if (check == 1){
            PreferenceManagers.setDataWithSameKey("in", Utility.SELECTED_LANGUAGE, LanguageActivity.this);
            String textIn = "Restart aplikasi dibutuhkan setelah merubah bahasa";
            dialogs(textIn,"","");
        } else if (check == 2){
            PreferenceManagers.setDataWithSameKey("en", Utility.SELECTED_LANGUAGE, LanguageActivity.this);
            String textEn = "Application restart is required after changing language";
            dialogs(textEn,"","");
        }
    }

    private void dialogs(String info1, String info2, String info3){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.d_info);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Button btnConfirm = (Button) dialog.findViewById(R.id.btnNext);
        final TextView tvDialog = (TextView) dialog.findViewById(R.id.tvDialog);
        tvDialog.setText("Restart");
        final TextView tvTitle1 = (TextView) dialog.findViewById(R.id.tvTitle1);
        tvTitle1.setText(info1);
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
        btnConfirm.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v) {
                ((Activity) context).finish();
                Intent i = new Intent(((Activity) context), SplashActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                ((Activity) context).startActivity(i);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
