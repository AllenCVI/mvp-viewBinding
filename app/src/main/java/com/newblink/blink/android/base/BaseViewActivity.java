package com.newblink.blink.android.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.viewbinding.ViewBinding;

import com.newblink.blink.android.R;
import com.newblink.blink.android.common.utils.ActivityManager;
import com.newblink.blink.android.common.utils.StatusUtil;
import com.newblink.blink.android.common.utils.ToastUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseViewActivity<VB extends ViewBinding> extends AppCompatActivity
{
    protected VB vBinding;
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(getScreenOrientation());//竖屏
        StatusUtil.setStatusBarDarkTheme(this, getResources().getColor(R.color.act_bg));
        try {
            Type superclass = getClass().getGenericSuperclass();
            Class<?> aClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
            Method method = aClass.getDeclaredMethod("inflate", LayoutInflater.class);
            vBinding = (VB) method.invoke(null, getLayoutInflater());
            setContentView(vBinding.getRoot());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
            initView(savedInstanceState);
            initData();
            mDialog = new ProgressDialog(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ToastUtil.reset();
        mDialog = null;
    }

    protected void initView(Bundle savedInstanceState) {

    }

    protected void initData() {

    }

    protected View getContentView()
    {
        return null;
    }

    protected View generateContentView(View contentView)
    {
        return contentView;
    }

    protected int getScreenOrientation()
    {
        return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    public void showLoadingDialog()
    {
        mDialog.show();
    }
    public void hideLoadingDialog()
    {
        if (mDialog != null) {
            try {
                mDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

