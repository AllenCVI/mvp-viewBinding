package com.newblink.blink.android;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.newblink.blink.android.base.BaseViewActivity;
import com.newblink.blink.android.common.LocalRepository;
import com.newblink.blink.android.databinding.ActivitySplashBinding;

public class SplashActivity extends BaseViewActivity<ActivitySplashBinding> {

    private Handler mHandler = null;

    private final int DELAY_TIME = 1500;


    @Override
    protected void initData() {
        super.initData();
        mHandler = new Handler(Looper.myLooper());
        if(mHandler!=null){
           mHandler.postDelayed(new Runnable() {
               @Override
               public void run() {
                   if (!LocalRepository.getInstance().isLogin()) {
                       gotoEntrance();
                   } else {
                       gotoMain();
                   }
               }
           }, DELAY_TIME);
        }
    }

    private void gotoMain() {
        MainActivity.start(this);
        finish();
    }

    private void gotoEntrance() {
        EntranceActivity.start(this);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        mHandler=null;
    }
}
