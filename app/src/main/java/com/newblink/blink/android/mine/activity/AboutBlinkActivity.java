package com.newblink.blink.android.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.newblink.blink.android.base.BaseViewActivity;
import com.newblink.blink.android.databinding.ActivityAboutBlinkBinding;

public class AboutBlinkActivity extends BaseViewActivity<ActivityAboutBlinkBinding> implements View.OnClickListener {

    public static void start(Context context) {
        Intent intent = new Intent(context, AboutBlinkActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}
