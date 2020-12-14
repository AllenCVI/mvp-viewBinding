package com.newblink.blink.android.mine.adapter;

import android.content.Context;
import android.view.View;

import com.newblink.blink.android.R;
import com.newblink.blink.android.common.view.rv.BaseHolder;
import com.newblink.blink.android.common.view.rv.DefaultEasyAdapter;
import com.newblink.blink.android.model.remote.responses.VisitorInfoBean;

public class VisitorRVEasyAdapter extends DefaultEasyAdapter<VisitorInfoBean> {

    Context mContext;

    public VisitorRVEasyAdapter(Context context) {
        mContext = context;
    }

    @Override
    public BaseHolder<VisitorInfoBean> getHolder(View v, int viewType) {
        return new viewHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_rv_visitor_act;
    }


    class viewHolder extends BaseHolder<VisitorInfoBean>{

        public viewHolder(View itemView) {
            super(itemView);

        }

        @Override
        public void bindData(VisitorInfoBean data, int position) {


        }
    }


}
