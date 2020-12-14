package com.newblink.blink.android.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.newblink.blink.android.R;
import com.newblink.blink.android.common.utils.image.ImageLoaderUtil;
import com.newblink.blink.android.databinding.ActivityVipBinding;
import com.newblink.blink.android.mine.realizationmvp.VipContract;
import com.newblink.blink.android.model.remote.responses.DataBean;
import com.newblink.blink.android.base.mvpbase.BaseMvpActivity;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

public class VipActivity extends BaseMvpActivity<ActivityVipBinding, VipContract.Presenter> implements VipContract.View, View.OnClickListener {

    private View view_selectVipTime_bgMiddle;
    private ImageView iv_bigPineapple;
    private TextView tv_selectVIPMoneyMonthly;
    private View view_selectVipTime_bgMiddle1;
    private ImageView iv_bigPineapple1;
    private TextView tv_selectVIPMoneyMonthly1;
    private View view_selectVipTime_bgMiddle2;
    private ImageView iv_bigPineapple2;
    private TextView tv_selectVIPMoneyMonthly2;
    private Banner banner;

    public static void start(Context context) {
        Intent intent = new Intent(context, VipActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        vBinding.flMVPTime.setOnClickListener(this);
        vBinding.flMVPTime1.setOnClickListener(this);
        vBinding.flMVPTime2.setOnClickListener(this);
        view_selectVipTime_bgMiddle = vBinding.viewSelectVipTimeBgMiddle;
        iv_bigPineapple = vBinding.ivBigPineapple;
        tv_selectVIPMoneyMonthly = vBinding.tvSelectVIPMoneyMonthly;
        view_selectVipTime_bgMiddle1 = vBinding.viewSelectVipTimeBgMiddle1;
        iv_bigPineapple1 = vBinding.ivBigPineapple1;
        tv_selectVIPMoneyMonthly1 = vBinding.tvSelectVIPMoneyMonthly1;
        view_selectVipTime_bgMiddle2 = vBinding.viewSelectVipTimeBgMiddle2;
        iv_bigPineapple2 = vBinding.ivBigPineapple2;
        tv_selectVIPMoneyMonthly2 = vBinding.tvSelectVIPMoneyMonthly2;
        initBannerView();

    }

    private void initBannerView() {
            banner = vBinding.banner;
        banner.setIndicator(new CircleIndicator(this));
        banner.setBannerGalleryEffect(18, 10);
        banner.setAdapter(new BannerImageAdapter<DataBean>(DataBean.getTestData3()) {
            @Override
            public void onBindView(BannerImageHolder holder, DataBean data, int position, int size) {
                //TODO
                ImageLoaderUtil.loadCircleImgInNetNoPlaceholder(VipActivity.this,data.imageUrl,holder.imageView, 15);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_MVPTime:
                MVPTimeFirstSelected();
                break;

            case R.id.fl_MVPTime1:
                MVPTimeSecondSelected();
                break;

            case R.id.fl_MVPTime2:
                MVPTimeThirdSelected();
                break;
            default:
                break;
        }

    }

    private void MVPTimeThirdSelected() {
        becomeSelectedState(view_selectVipTime_bgMiddle2,iv_bigPineapple2,tv_selectVIPMoneyMonthly2);
        becomeUnSelectedState(view_selectVipTime_bgMiddle1,iv_bigPineapple1,tv_selectVIPMoneyMonthly1);
        becomeUnSelectedState(view_selectVipTime_bgMiddle,iv_bigPineapple,tv_selectVIPMoneyMonthly);
    }

    private void MVPTimeSecondSelected() {
        becomeSelectedState(view_selectVipTime_bgMiddle1,iv_bigPineapple1,tv_selectVIPMoneyMonthly1);
        becomeUnSelectedState(view_selectVipTime_bgMiddle,iv_bigPineapple,tv_selectVIPMoneyMonthly);
        becomeUnSelectedState(view_selectVipTime_bgMiddle2,iv_bigPineapple2,tv_selectVIPMoneyMonthly2);
    }

    private void MVPTimeFirstSelected() {
       becomeSelectedState(view_selectVipTime_bgMiddle,iv_bigPineapple,tv_selectVIPMoneyMonthly);
       becomeUnSelectedState(view_selectVipTime_bgMiddle1,iv_bigPineapple1,tv_selectVIPMoneyMonthly1);
        becomeUnSelectedState(view_selectVipTime_bgMiddle2,iv_bigPineapple2,tv_selectVIPMoneyMonthly2);

    }


    private void becomeSelectedState(View view_selectVipTime_bgMiddle,
                                     ImageView iv_bigPineapple, TextView tv_selectVIPMoneyMonthly) {
        view_selectVipTime_bgMiddle.setVisibility(View.VISIBLE);
        iv_bigPineapple.setVisibility(View.VISIBLE);
        tv_selectVIPMoneyMonthly.setTextColor(Color.WHITE);
    }

    private void becomeUnSelectedState( View view_selectVipTime_bgMiddle,
                                       ImageView iv_bigPineapple, TextView tv_selectVIPMoneyMonthly) {
        view_selectVipTime_bgMiddle.setVisibility(View.GONE);
        iv_bigPineapple.setVisibility(View.GONE);
        tv_selectVIPMoneyMonthly.setTextColor(getResources().getColor(R.color.b4_3));
    }

}
