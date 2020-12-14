package com.newblink.blink.android.common.utils.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.newblink.blink.android.R;

public class LocationPopupWindowManager {
    private PopupWindow window;
    private Activity mActivity;

    public LocationPopupWindowManager(Activity context, int layoutId, OnFinishInflateListener listener) {

        this.mActivity = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(layoutId, null);
        window = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setAnimationStyle(R.style.pop_anim_alpha);
        window.setFocusable(true);
        window.setOnDismissListener(() -> {
            WindowManager.LayoutParams params = context.getWindow().getAttributes();
            params.alpha = 1f;
            context.getWindow().setAttributes(params);
        });

        if (listener != null) {
            listener.onFinishInflate(popupView, window);
        }

        Drawable drawable = new ColorDrawable(-0x50000000);
        window.setBackgroundDrawable(drawable);
    }

    /**
     * @param view 以哪个view为基准
     */
    public void showPop(View view, boolean isBackgroundHalfTransparent,int location,int x,int y) {
        if(isBackgroundHalfTransparent) {
            WindowManager.LayoutParams params = mActivity.getWindow().getAttributes();
            params.alpha = 0.7f;
            mActivity.getWindow().setAttributes(params);
        }
        window.showAtLocation(view,location, x, y);
    }


    public void dismiss(){
        window.dismiss();
    }


    /**
     * @param view 如果是Fragment，那么就是getView;如果是Activity，那么就是contentView
     */
    public void showPopBgTransparentAsLocation(View view, boolean isBackgroundHalfTransparent,int location,int x,int y) {
        if(isBackgroundHalfTransparent) {
            WindowManager.LayoutParams params = mActivity.getWindow().getAttributes();
            params.alpha = 0.7f;
            mActivity.getWindow().setAttributes(params);
        }
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.showAtLocation(view,location, x, y);
    }

    /**
     * @param view 如果是Fragment，那么就是getView;如果是Activity，那么就是contentView
     */
    public void showPopBgTransparentAsDropDown(View view, boolean isBackgroundHalfTransparent,int x,int y) {
        if(isBackgroundHalfTransparent) {
            WindowManager.LayoutParams params = mActivity.getWindow().getAttributes();
            params.alpha = 0.7f;
            mActivity.getWindow().setAttributes(params);
        }
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.showAsDropDown(view,x,y,Gravity.RIGHT);
    }




    public interface OnFinishInflateListener {

        /**
         * 在此方法中实现View监听绑定等操作
         *
         * @param view
         */
        void onFinishInflate(View view, PopupWindow window);
    }
}
