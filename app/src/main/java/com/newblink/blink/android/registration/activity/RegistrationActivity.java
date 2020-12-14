package com.newblink.blink.android.registration.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.newblink.blink.android.MainActivity;
import com.newblink.blink.android.R;
import com.newblink.blink.android.common.utils.DateUtil;
import com.newblink.blink.android.common.utils.StringUtil;
import com.newblink.blink.android.common.utils.ToastUtil;
import com.newblink.blink.android.common.utils.popupwindow.BottomPopupWindowManager;
import com.newblink.blink.android.common.view.WheelItem;
import com.newblink.blink.android.common.view.WheelView;
import com.newblink.blink.android.databinding.ActivityRegistrationBinding;
import com.newblink.blink.android.base.mvpbase.BaseMvpActivity;
import com.newblink.blink.android.model.remote.request.SignUpBean;
import com.newblink.blink.android.registration.realizationmvp.RegistrationContract;
import com.newblink.blink.android.registration.realizationmvp.RegistrationPresenter;

public class RegistrationActivity extends BaseMvpActivity<ActivityRegistrationBinding, RegistrationContract.Presenter> implements RegistrationContract.View, View.OnClickListener, BottomPopupWindowManager.OnFinishInflateListener, WheelView.OnSelectedListener {

    private TextView tv_birthdayData;
    private ImageView iv_maleHeaderImg;
    private ImageView iv_femaleHeaderImg;
    private ImageView iv_maleChecked;
    private ImageView iv_femaleChecked;
    private BottomPopupWindowManager birthdayPopupWindowManager = null;
    private final int minAge = DateUtil.getSysYear()-99;
    private final int maxAge = DateUtil.getSysYear()-18;
    private int year;
    private int month;
    private int day;
    private int sex;//1 男 2 女


    public static void start(Context context) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        birthdayPopupWindowManager = new BottomPopupWindowManager(this, R.layout.pop_birthday_registration_act, this);
        vBinding.btMatching.setOnClickListener(this);
        vBinding.rlTimeSelector.setOnClickListener(this);
        tv_birthdayData = vBinding.tvBirthdayData;
        iv_maleHeaderImg = vBinding.ivMaleHeaderImg;
        iv_femaleHeaderImg = vBinding.ivFemaleHeaderImg;
        iv_maleChecked = vBinding.ivMaleChecked;
        iv_femaleChecked = vBinding.ivFemaleChecked;
        iv_maleHeaderImg.setOnClickListener(this);
        iv_femaleHeaderImg.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        new RegistrationPresenter(this);
        sex = 1;
        setDayMonthYearData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_matching:
                matching();
                break;
            case R.id.rl_timeSelector:
                popTimeSelector();
                break;
            case R.id.iv_maleHeaderImg:
                selectMale();
                break;
            case R.id.iv_femaleHeaderImg:
                selectFeMale();
                break;
            default:
                break;
        }

    }
    //选择女
    private void selectFeMale() {
        iv_maleHeaderImg.setImageResource(R.drawable.unchecked_header_img_male_registration_act);
        iv_femaleHeaderImg.setImageResource(R.drawable.checked_header_img_female_registration_act);
        iv_maleChecked.setVisibility(View.GONE);
        iv_femaleChecked.setVisibility(View.VISIBLE);
        sex = 2;
    }

    //选择男
    private void selectMale() {
        iv_maleHeaderImg.setImageResource(R.drawable.checked_header_img_male_registration_act);
        iv_femaleHeaderImg.setImageResource(R.drawable.unchecked_header_img_female_registration_act);
        iv_maleChecked.setVisibility(View.VISIBLE);
        iv_femaleChecked.setVisibility(View.GONE);
        sex = 1;
    }

    private void popTimeSelector() {
        setBirthDaySelectorValue();
        birthdayPopupWindowManager.showPopBgTransparent(vBinding.getRoot(), true);
    }

    //year month day 是全局逻辑共用
    private void setBirthDaySelectorValue() {
        setDayMonthYearData();
        wheel_view_year.setSelectedIndex(year - minAge);
        wheel_view_month.setSelectedIndex(month - 1);
        wheel_view_day.setSelectedIndex(day-1);
    }

    private void setDayMonthYearData() {
        String birthDayStr = tv_birthdayData.getText().toString().trim();
        int[] birthDayIntArr = StringUtil.StringToIntValueBySplit(birthDayStr,3,"/");
        day = birthDayIntArr[0];
        month = birthDayIntArr[1];
        year = birthDayIntArr[2];
    }

    private void matching() {
        showLoading();
        mPresenter.signUp(new SignUpBean(DateUtil.getSysYear()-year,sex+""));

    }

    private void gotoMain() {
        MainActivity.start(this);
        finish();
    }


    private WheelView wheel_view_month;
    private WheelView wheel_view_day;
    private WheelView wheel_view_year;

    @Override
    public void onFinishInflate(View view, PopupWindow window) {
        ImageView iv_close = view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(listener-> birthdayPopupWindowManager.dismiss());
        Button bt_save = view.findViewById(R.id.bt_save);
        bt_save.setOnClickListener(listener-> checkedBirthday());
        wheel_view_month = view.findViewById(R.id.wheel_view_month);
        wheel_view_month.setOnSelectedListener(this);
        wheel_view_day = view.findViewById(R.id.wheel_view_day);
        wheel_view_day.setOnSelectedListener(this);
        wheel_view_year = view.findViewById(R.id.wheel_view_year);
        wheel_view_year.setOnSelectedListener(this);
        initWheelViewYear();
        initWheelViewMonth();
    }

    private void checkedBirthday() {
        birthdayPopupWindowManager.dismiss();
        inputBirthdayToTv();
    }

    private void inputBirthdayToTv() {
        String yearStr = year + "";
        String monthStr = "";
        String dayStr = "";
        monthStr = StringUtil.initIntToString(monthStr,month);
        dayStr = StringUtil.initIntToString(dayStr,day);
        String birthDay = dayStr+"/" +monthStr+"/"+yearStr;
        tv_birthdayData.setText(birthDay);
    }



    private void initWheelViewYear() {
        WheelItem[] items = new WheelItem[maxAge-minAge+1];
        for(int i=0;i<=maxAge-minAge;i++){
            items[i] = new WheelItem(minAge+i+"");
        }
        wheel_view_year.setItems(items);
    }

    private void initWheelViewMonth() {
        String[] month = getResources().getStringArray(R.array.array_month);
        WheelItem[] items = new WheelItem[month.length];
        for (int i = 0; i < month.length; i++) {
            items[i] = new WheelItem(month[i]);
        }
        wheel_view_month.setItems(items);
        wheel_view_month.setSelectedIndex(0);
    }


    @Override
    public void onSelected(Context context, int selectedIndex, WheelView wheelView) {
        switch (wheelView.getId()) {
            case R.id.wheel_view_month:
                monthSelected(selectedIndex);
                break;
            case R.id.wheel_view_day:
                daySelected(selectedIndex);
                break;
            case R.id.wheel_view_year:
                yearSelected(selectedIndex);
                break;
            default:
                break;
        }
    }

    private void daySelected(int selectedIndex) {
        day = selectedIndex + 1;
    }

    private void yearSelected(int selectedIndex) {
        year = minAge + selectedIndex;
    }

    WheelItem[] itemsDay = null;
    private void monthSelected(int selectedIndex) {
        month = selectedIndex +1;
        showDayByMonth(selectedIndex);
    }

    private void showDayByMonth(int selectedIndex) {
        boolean isLastDay = false;
        if (itemsDay != null) {
            isLastDay = isLastMonthDay();
        }
        int dayCount = calculateTheMonthDay(selectedIndex);
        itemsDay = new WheelItem[dayCount];
        String dayStr="";
        for (int i = 1; i <= dayCount; i++) {
            dayStr = StringUtil.initIntToString(dayStr,i);
            itemsDay[i - 1] = new WheelItem(dayStr);
        }
        wheel_view_day.setItems(itemsDay);
        if (isLastDay) {
            wheel_view_day.setSelectedIndex(itemsDay.length - 1);
        }
    }

    private int calculateTheMonthDay(int selectedIndex) {
        int dayCount = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                dayCount = 31;
                break;
            case 2:
                if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
                    dayCount = 29;
                } else {
                    dayCount = 28;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                dayCount = 30;
                break;
        }
        return dayCount;
    }

    private boolean isLastMonthDay() {
        return itemsDay.length - 1 == wheel_view_day.getSelectedIndex();
    }

    @Override
    public void signUpSuccess() {
        hideLoading();
        gotoMain();
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }
}
