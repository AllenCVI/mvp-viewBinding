package com.newblink.blink.android.common.view;

import android.content.Context;
import android.util.AttributeSet;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;


public class BlinkRefreshLayout extends SmartRefreshLayout {


    public BlinkRefreshLayout(Context context) {
        super(context);
    }

    public BlinkRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setBlinkRefreshListener(OnBlinkRefreshListener refreshListener) {

        setOnRefreshListener(v -> {
            if (refreshListener != null) {
                refreshListener.onRefresh(this);
            }
        });

        setOnLoadMoreListener(v -> {
            if (refreshListener != null) {
                refreshListener.onLoadMore(this);
            }
        });
    }

    public void setBlinkRefreshEnabled(boolean isEnabled) {
        setEnableRefresh(isEnabled);
    }

    public void setBlinkLoadMoreEnabled(boolean isEnabled) {
        setEnableLoadMore(isEnabled);
    }

    public boolean isBlinkRefreshing() {
        return isRefreshing();
    }

    public void BlinkRefreshingAutomatic() {
        autoRefresh();
    }

    public void BlinkRefreshingAutomatic(int delay) {
        autoRefresh(delay);
    }

    public void BlinkLoadMoreAutomatic() {
        autoLoadMore();
    }

    public void finishBlinkRefresh(boolean success) {
        finishRefresh(success);
    }

    public void finishBlinkRefresh() {
        finishRefresh();
    }


    public void setBlinkNoMoreData(boolean noMoreData){
        setNoMoreData(noMoreData);
    }


    public void finishBlinkLoadMore() {
        finishLoadMore();
    }

    public void finishBlinkLoadMoreNoDelayTime() {
        finishLoadMore(0);
    }

    public void finishBlinkRefreshNoDelayTime() {
        finishRefresh(0);
    }


    public void finishBlinkLoadMoreWithNoMoreData(){
        finishLoadMoreWithNoMoreData();
    }

    public boolean isBlinkLoading(){
        return isLoading();
    }


    public static abstract class OnBlinkRefreshListener {

        /**
         * 下拉刷新
         * @param blinkRefreshLayout
         */
        public abstract void onRefresh(BlinkRefreshLayout blinkRefreshLayout);

        /**
         * 下拉加载更多
         * @param blinkRefreshLayout
         */
        public void onLoadMore(BlinkRefreshLayout blinkRefreshLayout) {

        }
    }
}

