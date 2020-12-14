package com.newblink.blink.android.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.newblink.blink.android.base.BaseViewActivity;
import com.newblink.blink.android.databinding.ActivityChangePasswordBinding;

public class ChangePasswordActivity extends BaseViewActivity<ActivityChangePasswordBinding> implements View.OnClickListener {


    public static void start(Context context) {
        Intent intent = new Intent(context, ChangePasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}
