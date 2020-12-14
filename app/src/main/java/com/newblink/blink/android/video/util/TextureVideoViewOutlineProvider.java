package com.newblink.blink.android.video.util;

import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;

import androidx.annotation.RequiresApi;

import com.newblink.blink.android.common.utils.DeviceUtil;
import com.newblink.blink.android.home.activity.RankActivity;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TextureVideoViewOutlineProvider extends ViewOutlineProvider {
    private float mRadius;

    public TextureVideoViewOutlineProvider(float radius) {
        this.mRadius = radius;
    }

    @Override
    public void getOutline(View view, Outline outline) {
        int left = DeviceUtil.dp2px(view.getContext(),2);
        int top = DeviceUtil.dp2px(view.getContext(),52);
        int right = DeviceUtil.dp2px(view.getContext(),347);
        int bottom = DeviceUtil.dp2px(view.getContext(),566);

        outline.setRoundRect(left, top, right, bottom,mRadius);

    }
}