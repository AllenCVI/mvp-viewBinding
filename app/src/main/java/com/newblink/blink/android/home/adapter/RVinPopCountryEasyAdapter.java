package com.newblink.blink.android.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.newblink.blink.android.R;
import com.newblink.blink.android.common.view.rv.BaseHolder;
import com.newblink.blink.android.common.view.rv.DefaultEasyAdapter;
import com.newblink.blink.android.model.local.CountryBean;

public class RVinPopCountryEasyAdapter extends DefaultEasyAdapter<CountryBean> {

    Context mContext;

    public RVinPopCountryEasyAdapter(Context context) {
        mContext = context;
    }

    @Override
    public BaseHolder<CountryBean> getHolder(View v, int viewType) {
        return new viewHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_pop_rv_country_home_fg;
    }

    class viewHolder extends BaseHolder<CountryBean> {
        TextView tv_country;


        public viewHolder(View itemView) {
            super(itemView);
            tv_country = itemView.findViewById(R.id.tv_country);
        }

        @Override
        public void bindData(CountryBean data, int position) {

            if(position==0){
                tv_country.setCompoundDrawables(null, null, null, null);
            }else {
                Drawable nationalFlag =  mContext.getResources().getDrawable(R.drawable.india_national_flag);
                nationalFlag.setBounds(0, 0, nationalFlag.getMinimumWidth(), nationalFlag.getMinimumHeight());
                tv_country.setCompoundDrawables(nationalFlag, null, null, null);
            }

            if(data.isChecked()){
                tv_country.setTextColor(Color.WHITE);
                tv_country.setSelected(true);
            }else {
                tv_country.setTextColor(Color.BLACK);
                tv_country.setSelected(false);
            }

            tv_country.setText(data.getName());

        }
    }
}
