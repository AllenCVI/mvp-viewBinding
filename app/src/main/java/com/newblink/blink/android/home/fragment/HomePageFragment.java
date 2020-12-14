package com.newblink.blink.android.home.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dueeeke.videoplayer.player.VideoView;
import com.library.flowlayout.FlowLayoutManager;
import com.library.flowlayout.SpaceItemDecoration;
import com.newblink.blink.android.R;
import com.newblink.blink.android.common.utils.DeviceUtil;
import com.newblink.blink.android.common.utils.popupwindow.LocationPopupWindowManager;
import com.newblink.blink.android.databinding.FragmentHomepageBinding;
import com.newblink.blink.android.home.activity.RankActivity;
import com.newblink.blink.android.home.adapter.AnchorRVEasyAdapter;
import com.newblink.blink.android.home.adapter.CountryRVEasyAdapter;
import com.newblink.blink.android.home.adapter.RVinPopCountryEasyAdapter;
import com.newblink.blink.android.home.realizationmvp.HomePageContract;
import com.newblink.blink.android.model.local.CountryBean;
import com.newblink.blink.android.model.remote.responses.AnchorInfoBean;
import com.newblink.blink.android.base.mvpbase.BaseMvpFragment;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends BaseMvpFragment<FragmentHomepageBinding,VideoView,HomePageContract.Presenter> implements HomePageContract.View, View.OnClickListener, LocationPopupWindowManager.OnFinishInflateListener {
    private RecyclerView rvCountry;
    private CountryRVEasyAdapter countryRVAdapter;
    private RecyclerView rv_anchorList;
    private LocationPopupWindowManager countryPW;
    private AnchorRVEasyAdapter anchorRVAdapter;


    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }



    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        initCountryRecyclerView();
        initAnchorListRecyclerView();
        initCountryPopupWindow();
        vBinding.ivCountryChange.setOnClickListener(this);
        vBinding.ivRank.setOnClickListener(this);

    }


    private void initCountryPopupWindow() {
        countryPW  = new LocationPopupWindowManager(getActivity(),R.layout.pop_country_select_home_fg,this);
    }

    private void initAnchorListRecyclerView() {
        rv_anchorList = vBinding.rvAnchorList;
        rv_anchorList.setItemViewCacheSize(6);
        rv_anchorList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(context).resumeRequests();
                }else {
                    Glide.with(context).pauseRequests();
                }
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        rv_anchorList.setLayoutManager(gridLayoutManager);
        anchorRVAdapter = new AnchorRVEasyAdapter(R.layout.item_rv_anchor_home_fg);
        rv_anchorList.setAdapter(anchorRVAdapter);
        ArrayList<AnchorInfoBean> anchorInfoBeanArrayList = new ArrayList<>();
        //TODO 先放假数据
        for (int i=0;i<20;i++){
            anchorInfoBeanArrayList.add(new AnchorInfoBean());
        }
        anchorRVAdapter.setNewData(anchorInfoBeanArrayList);
    }

    private List<CountryBean> countryBeanList;
    private void initCountryRecyclerView() {
        rvCountry = vBinding.rvCountry;
        countryRVAdapter = new CountryRVEasyAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rvCountry.setLayoutManager(linearLayoutManager);
        rvCountry.setAdapter(countryRVAdapter);
        String[] countryArr = getResources().getStringArray(R.array.array_country);
        countryBeanList = new ArrayList<>(countryArr.length);
        for (int i=0;i<countryArr.length;i++){
            countryBeanList.add(new CountryBean(countryArr[i],false));
        }
        countryRVAdapter.setInfos(countryBeanList);
        countryRVAdapter.setOnItemClickListener((View view, int viewType, Object data, int position)->{
                for (int i=0;i<countryBeanList.size();i++){
                    if(position==i){
                        countryBeanList.get(i).setChecked(true);
                    }else {
                        countryBeanList.get(i).setChecked(false);
                    }
                }
                countryRVAdapter.notifyDataSetChanged();
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_countryChange:
                changeCountry();
                break;
            case R.id.iv_rank:
                rank();
                break;
            default:
                break;
        }
    }

    private void rank() {
        RankActivity.start(context);
    }

    private void changeCountry() {
         countryPW.showPopBgTransparentAsDropDown(vBinding.ivCountryChange,true,0, DeviceUtil.dp2px(context,6));
    }

    private RVinPopCountryEasyAdapter rVinPopCountryAdapter;
    @Override
    public void onFinishInflate(View view, PopupWindow window) {
        RecyclerView rv_countrySelect = view.findViewById(R.id.rv_countrySelect);
        FlowLayoutManager flowManager = new FlowLayoutManager();
        rv_countrySelect.addItemDecoration(new SpaceItemDecoration(DeviceUtil.dp2px(context,8)));
        rv_countrySelect.setLayoutManager(flowManager);
        rVinPopCountryAdapter = new RVinPopCountryEasyAdapter(context);
        rv_countrySelect.setAdapter(rVinPopCountryAdapter);
        rVinPopCountryAdapter.setInfos(countryBeanList);

        rVinPopCountryAdapter.setOnItemClickListener((View v, int viewType, Object data, int position)->{
            for (int i=0;i<countryBeanList.size();i++){
                if(position==i){
                    countryBeanList.get(i).setChecked(true);
                }else {
                    countryBeanList.get(i).setChecked(false);
                }
            }
            rVinPopCountryAdapter.notifyDataSetChanged();
            countryRVAdapter.notifyDataSetChanged();
            rvCountry.smoothScrollToPosition(position);
            countryPW.dismiss();
        });
    }
}
