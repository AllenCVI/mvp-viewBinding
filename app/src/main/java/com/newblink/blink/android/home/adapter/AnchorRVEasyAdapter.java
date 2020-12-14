package com.newblink.blink.android.home.adapter;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.newblink.blink.android.R;
import com.newblink.blink.android.common.utils.image.ImageLoaderUtil;
import com.newblink.blink.android.common.view.rv.BaseHolder;
import com.newblink.blink.android.common.view.rv.DefaultEasyAdapter;
import com.newblink.blink.android.model.remote.responses.AnchorInfoBean;

import java.util.List;

public class AnchorRVEasyAdapter extends BaseQuickAdapter<AnchorInfoBean, BaseViewHolder> {


    public AnchorRVEasyAdapter(int layoutResId, @Nullable List<AnchorInfoBean> data) {
        super(layoutResId, data);
    }

    public AnchorRVEasyAdapter(@Nullable List<AnchorInfoBean> data) {
        super(data);
    }

    public AnchorRVEasyAdapter(int layoutResId) {
        super(layoutResId);
    }




    @Override
    protected void convert(BaseViewHolder helper, AnchorInfoBean data) {
        ImageView iv_anchorImg = helper.getView(R.id.iv_anchorImg);
        ImageLoaderUtil.loadCircleImgInNet(mContext,R.drawable.bg_splash_act,iv_anchorImg,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,18);
    }

}