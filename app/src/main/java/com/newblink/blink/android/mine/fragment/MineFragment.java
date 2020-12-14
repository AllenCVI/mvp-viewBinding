package com.newblink.blink.android.mine.fragment;

import android.os.Bundle;
import android.view.View;

import com.dueeeke.videoplayer.player.VideoView;
import com.newblink.blink.android.R;
import com.newblink.blink.android.common.utils.image.ImageLoaderUtil;
import com.newblink.blink.android.databinding.FragmentMineBinding;
import com.newblink.blink.android.mine.activity.SettingActivity;
import com.newblink.blink.android.mine.activity.VipActivity;
import com.newblink.blink.android.mine.activity.VisitorActivity;
import com.newblink.blink.android.mine.realizationmvp.MineContract;
import com.newblink.blink.android.base.mvpbase.BaseMvpFragment;

public class MineFragment extends BaseMvpFragment<FragmentMineBinding,VideoView,MineContract.Presenter> implements MineContract.View, View.OnClickListener {

    public static MineFragment newInstance() {
        return new MineFragment();
    }



    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        ImageLoaderUtil.loadCircleImgEasyChange(getContext(), R.drawable.ic_launcher_background,vBinding.ivHeaderImg, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,25);
        vBinding.rlSetting.setOnClickListener(this);
        vBinding.llMember.setOnClickListener(this);
        vBinding.rlVisitors.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_setting:
                settingClick();
                break;
            case R.id.ll_member:
                vip();
                break;
            case R.id.rl_visitors:
                Visitors();
                break;
            default:
                break;
        }
    }

    private void Visitors() {
        VisitorActivity.start(context);
    }

    private void vip() {
        VipActivity.start(context);
    }

    private void settingClick() {
        SettingActivity.start(context);
    }
}
