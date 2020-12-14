package com.newblink.blink.android.home.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.newblink.blink.android.R;
import com.newblink.blink.android.databinding.ActivityRankBinding;
import com.newblink.blink.android.home.realizationmvp.RankContract;
import com.newblink.blink.android.base.mvpbase.BaseMvpActivity;

public class RankActivity extends BaseMvpActivity<ActivityRankBinding, RankContract.Presenter> implements  RankContract.View, View.OnClickListener {

    private TextView tv_Goddess;
    private TextView tv_MaleGod;

    public static void start(Context context) {
        Intent intent = new Intent(context, RankActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tv_Goddess = vBinding.tvGoddess;
        tv_MaleGod = vBinding.tvMaleGod;
        tv_Goddess.setOnClickListener(this);
        tv_MaleGod.setOnClickListener(this);
        tv_Goddess.setSelected(true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_Goddess:
                goddess();
                break;
            case R.id.tv_MaleGod:
                maleGod();
                break;
            default:
                break;
        }
    }

    private void maleGod() {
        maleGodButtonState();

    }

    private void maleGodButtonState() {
        tv_Goddess.setSelected(false);
        tv_MaleGod.setSelected(true);
        tv_Goddess.setTextColor(Color.WHITE);
        tv_MaleGod.setTextColor(getResources().getColor(R.color.mFF00FF));
    }

    private void goddess() {
        goddessButtonState();

    }

    private void goddessButtonState() {
        tv_Goddess.setSelected(true);
        tv_MaleGod.setSelected(false);
        tv_Goddess.setTextColor(getResources().getColor(R.color.mFF00FF));
        tv_MaleGod.setTextColor(Color.WHITE);
    }


}
