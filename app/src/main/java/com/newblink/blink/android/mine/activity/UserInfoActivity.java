package com.newblink.blink.android.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.newblink.blink.android.R;
import com.newblink.blink.android.base.BaseViewActivity;
import com.newblink.blink.android.databinding.ActivityUserInfomationBinding;

public class UserInfoActivity extends BaseViewActivity<ActivityUserInfomationBinding> implements View.OnClickListener {

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        vBinding.rlChangePassword.setOnClickListener(this);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_Change_password:
                changePasswordClick();
                break;
            default:
                break;
        }

    }

    private void changePasswordClick() {
        ChangePasswordActivity.start(this);
    }
}
