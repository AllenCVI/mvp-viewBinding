package com.newblink.blink.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.newblink.blink.android.base.App;
import com.newblink.blink.android.base.BaseViewActivity;
import com.newblink.blink.android.common.MakeStrUtil;
import com.newblink.blink.android.common.constant.CommonConstant;
import com.newblink.blink.android.common.fragment.FewFragmentPagerAdapter;
import com.newblink.blink.android.common.utils.DeviceUtil;
import com.newblink.blink.android.databinding.ActivityMainBinding;
import com.newblink.blink.android.home.fragment.HomePageFragment;
import com.newblink.blink.android.message.fragment.MessageFragment;
import com.newblink.blink.android.mine.fragment.MineFragment;
import com.newblink.blink.android.video.fragment.VideoFragment;

import java.util.ArrayList;

public class MainActivity extends BaseViewActivity<ActivityMainBinding> {

    private ViewPager viewPager;
    private long exitTime = 0;
    private RadioGroup rgMain;
    private int currentTabIndex = -1;
    private int[] rbIds = {R.id.tab_home, R.id.tab_video, R.id.tab_message, R.id.tab_mine};
    private static final String STATE_CURRENT_TAB_INDEX = "StateCurrentTabIndex";
    private static final int DEFAULT_INDEX = 0;
    private ArrayList<Fragment> fragmentList;


    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        rgMain = vBinding.rgMain;
        viewPager = vBinding.viewPager;
        int index;
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt(MainActivity.STATE_CURRENT_TAB_INDEX);
        } else {
            index = DEFAULT_INDEX;
        }
        rgMain.check(rbIds[index]);

        rgMain.setOnCheckedChangeListener((radioGroup, id) -> {
            int pos;
            switch (id) {
                case R.id.tab_home:
                    pos = 0;
                    break;
                case R.id.tab_video:
                    pos = 1;
                    break;
                case R.id.tab_message:
                    pos = 2;
                    break;
                case R.id.tab_mine:
                    pos = 3;
                    break;
                default:
                    pos = 0;
                    break;
            }
            viewPager.setCurrentItem(pos);
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rgMain.check(rbIds[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    protected void initData() {
        super.initData();
        createFragmentIntoList();
    }

    private void createFragmentIntoList() {
        fragmentList = new ArrayList<>();
        fragmentList.add(HomePageFragment.newInstance());
        fragmentList.add(VideoFragment.newInstance());
        fragmentList.add(MessageFragment.newInstance());
        fragmentList.add(MineFragment.newInstance());
        FragmentManager fm = getSupportFragmentManager();
        FewFragmentPagerAdapter fewFragmentPagerAdapter = new FewFragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT,fragmentList);
        viewPager.setAdapter(fewFragmentPagerAdapter);
        viewPager.setCurrentItem(DEFAULT_INDEX);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putInt(MainActivity.STATE_CURRENT_TAB_INDEX, currentTabIndex);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            currentTabIndex = savedInstanceState.getInt(MainActivity.STATE_CURRENT_TAB_INDEX);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if (System.currentTimeMillis() - exitTime > 3000) {
                Toast.makeText(getApplicationContext(), R.string.double_click_quit, Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}