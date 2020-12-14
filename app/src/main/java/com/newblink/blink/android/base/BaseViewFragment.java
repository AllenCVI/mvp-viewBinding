package com.newblink.blink.android.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.dueeeke.videoplayer.player.VideoView;
import com.dueeeke.videoplayer.player.VideoViewManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseViewFragment<VB extends ViewBinding,T extends VideoView> extends Fragment
{

    protected VB vBinding;
    private View rootView = null;
    private boolean isViewCreated = false;
    private boolean isCurrentVisibleState = false;
    protected Context context;
    protected T mVideoView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        context = getContext();
        if(rootView==null) {
            Type superclass = getClass().getGenericSuperclass();
            Class<?> aClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
            try {
                Method method = aClass.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
                vBinding = (VB) method.invoke(null, getLayoutInflater(), container, false);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            rootView = vBinding.getRoot();
        }

        initView(savedInstanceState,rootView);
        isViewCreated = true;
        if(getUserVisibleHint()){
            setUserVisibleHint(true);
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initView(Bundle savedInstanceState,View rootView);

    protected void initData() {

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isViewCreated) {
            if (!isCurrentVisibleState && isVisibleToUser) {
                dispatchUserVisibleHint(true);
            } else if(isCurrentVisibleState && !isVisibleToUser){
                dispatchUserVisibleHint(false);
            }
        }
    }


    private void dispatchUserVisibleHint(boolean visibleState) {
        isCurrentVisibleState = visibleState;
        if(visibleState){
            onFragmentLoad();
        }else {
            onFragmentStop();
        }
    }

    //fragment不可见的时候调用
    public void onFragmentStop() {
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    //fragment可见的时候调用
    public void onFragmentLoad() {
        if (mVideoView != null) {
            mVideoView.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(getUserVisibleHint() && isCurrentVisibleState){
            dispatchUserVisibleHint(false);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(getUserVisibleHint() && !isCurrentVisibleState){
            dispatchUserVisibleHint(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.release();
        }
    }



    protected VideoViewManager getVideoViewManager() {
        return VideoViewManager.instance();
    }


    protected void showLoadingDialog()
    {
        Activity activity = getActivity();
        if (activity instanceof BaseViewActivity)
        {
            ((BaseViewActivity) activity).showLoadingDialog();
        }
    }

    protected void hideLoadingDialog()
    {
        Activity activity = getActivity();
        if (activity instanceof BaseViewActivity)
        {
            ((BaseViewActivity) activity).hideLoadingDialog();
        }
    }
}
