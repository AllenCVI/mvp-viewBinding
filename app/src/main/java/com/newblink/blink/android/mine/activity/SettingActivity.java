package com.newblink.blink.android.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RatingBar;

import com.newblink.blink.android.EntranceActivity;
import com.newblink.blink.android.R;
import com.newblink.blink.android.base.BaseViewActivity;
import com.newblink.blink.android.common.LocalRepository;
import com.newblink.blink.android.common.utils.ActivityManager;
import com.newblink.blink.android.common.utils.popupwindow.LocationPopupWindowManager;
import com.newblink.blink.android.databinding.ActivitySettingBinding;
import com.newblink.blink.android.login.activity.LoginActivity;

public class SettingActivity extends BaseViewActivity<ActivitySettingBinding> implements View.OnClickListener, LocationPopupWindowManager.OnFinishInflateListener {
    private LocationPopupWindowManager rateUsPop;

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        vBinding.rlUserInfo.setOnClickListener(this);
        vBinding.rlAboutBlink.setOnClickListener(this);
        vBinding.rlRateUs.setOnClickListener(this);
        vBinding.btSwitchUser.setOnClickListener(this);
        initPopRateUs();
    }

    private void initPopRateUs() {
        rateUsPop = new LocationPopupWindowManager(this,R.layout.pop_rate_us_setting_act,this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_userInfo:
                userInfoClick();
                break;
            case R.id.rl_aboutBlink:
                aboutBlinkClick();
                break;
            case R.id.rl_rateUs:
                rateUs();
                break;
            case R.id.bt_switchUser:
                switchUser();
                break;
            default:
                break;
        }

    }

    private void switchUser() {
        LocalRepository.getInstance().clearUserInfo();
        LoginActivity.start(this);
        ActivityManager.getInstance().clearActivities();
    }

    private void rateUs() {
        rateUsPopShow();
    }

    private void rateUsPopShow() {
        rateUsPop.showPopBgTransparentAsLocation(vBinding.getRoot(),true, Gravity.CENTER,0,0);
    }

    private void aboutBlinkClick() {
        AboutBlinkActivity.start(this);
    }

    private void userInfoClick() {
        UserInfoActivity.start(this);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void onFinishInflate(View view, PopupWindow window) {
       RatingBar ratingBar = view.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.e("------------","当前的评价等级："+rating);
            }
        });

    }
}
