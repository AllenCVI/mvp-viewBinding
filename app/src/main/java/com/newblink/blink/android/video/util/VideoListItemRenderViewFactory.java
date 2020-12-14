package com.newblink.blink.android.video.util;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.dueeeke.videoplayer.render.IRenderView;
import com.dueeeke.videoplayer.render.RenderViewFactory;
import com.dueeeke.videoplayer.render.TextureRenderView;
import com.newblink.blink.android.common.utils.DeviceUtil;

public class VideoListItemRenderViewFactory extends RenderViewFactory {

    public static VideoListItemRenderViewFactory create() {
        return new VideoListItemRenderViewFactory();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public IRenderView createRenderView(Context context) {
       TextureRenderView textureRenderView = new TextureRenderView(context);
//       textureRenderView.setOutlineProvider(new TextureVideoViewOutlineProvider(DeviceUtil.dp2px(context,20)));
//       textureRenderView.setClipToOutline(true);
       VideoListItemRenderView videoListItemRenderView = new VideoListItemRenderView(textureRenderView);
        return videoListItemRenderView;
    }
}
