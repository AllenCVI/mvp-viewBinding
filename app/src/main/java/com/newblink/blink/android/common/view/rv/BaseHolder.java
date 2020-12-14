package com.newblink.blink.android.common.view.rv;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


public abstract class BaseHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected OnViewClickListener mOnViewClickListener = null;
    protected final String TAG = this.getClass().getSimpleName();

    public BaseHolder(View itemView) {
        super(itemView);
        //绑定
        //点击事件
        itemView.setOnClickListener(this);
    }

    /**
     * 设置数据
     *
     * @param data
     * @param position
     */
    public abstract void bindData(T data, int position);


    /**
     * 在 Activity 的 onDestroy 中使用 {@link DefaultEasyAdapter#releaseAllHolder(RecyclerView)} 方法 (super.onDestroy() 之前)
     * {@link BaseHolder#onRelease()} 才会被调用, 可以在此方法中释放一些资源
     */
    public void onRelease() {

    }

    @Override
    public void onClick(View view) {
        if (mOnViewClickListener != null) {
            mOnViewClickListener.onViewClick(view, this.getPosition());
        }
    }

    public interface OnViewClickListener {
        /**
         * item点击
         *
         * @param view
         * @param position
         */
        void onViewClick(View view, int position);
    }

    public void setOnItemClickListener(OnViewClickListener listener) {
        this.mOnViewClickListener = listener;
    }
}
