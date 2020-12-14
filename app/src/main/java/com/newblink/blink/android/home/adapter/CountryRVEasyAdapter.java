package com.newblink.blink.android.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.newblink.blink.android.R;
import com.newblink.blink.android.common.view.rv.BaseHolder;
import com.newblink.blink.android.common.view.rv.DefaultEasyAdapter;
import com.newblink.blink.android.model.local.CountryBean;

public class CountryRVEasyAdapter extends DefaultEasyAdapter<CountryBean> {


    Context mContext;

    public CountryRVEasyAdapter(Context context) {
        mContext = context;
    }

    @Override
    public BaseHolder<CountryBean> getHolder(View v, int viewType) {
        return new viewHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_rv_country_home_fg;
    }

    class viewHolder extends BaseHolder<CountryBean>{
        ImageView iv_countryLocation;
        TextView tv_countryName;

        public viewHolder(View itemView) {
            super(itemView);
            iv_countryLocation = itemView.findViewById(R.id.iv_countryLocation);
            tv_countryName = itemView.findViewById(R.id.tv_countryName);
        }

        @Override
        public void bindData(CountryBean data, int position) {
            if(data.isChecked()){
                iv_countryLocation.setVisibility(View.VISIBLE);
                tv_countryName.setTextColor(mContext.getResources().getColor(R.color.mE5E4E4));
                tv_countryName.setTextSize(15);
            }else {
                iv_countryLocation.setVisibility(View.INVISIBLE);
                tv_countryName.setTextColor(mContext.getResources().getColor(R.color.m9D9D9D));
                tv_countryName.setTextSize(13);
            }

            tv_countryName.setText(data.getName());

        }
    }

}
