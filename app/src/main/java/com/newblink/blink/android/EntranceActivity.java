package com.newblink.blink.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.newblink.blink.android.base.BaseViewActivity;
import com.newblink.blink.android.databinding.ActivityEntranceBinding;
import com.newblink.blink.android.login.activity.LoginActivity;
import com.newblink.blink.android.registration.activity.RegistrationActivity;

public class EntranceActivity extends BaseViewActivity<ActivityEntranceBinding> implements View.OnClickListener {

    private long exitTime = 0;

    public static void start(Context context) {
        Intent intent = new Intent(context, EntranceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        vBinding.btRegistration.setOnClickListener(this);
        vBinding.btLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId){
            case R.id.bt_registration:
                gotoRegistration();
                break;
            case R.id.bt_login:
                gotoLogin();
                break;
            default:
                break;
        }

    }

    private void gotoLogin() {
        LoginActivity.start(this);
    }

    private void gotoRegistration() {
        RegistrationActivity.start(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if (System.currentTimeMillis() - exitTime > 3000) {
                Toast.makeText(getApplicationContext(), R.string.double_click_quit, Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
