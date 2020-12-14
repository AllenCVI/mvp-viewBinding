package com.newblink.blink.android.video.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.dueeeke.videoplayer.player.VideoView;
import com.dueeeke.videoplayer.util.L;
import com.newblink.blink.android.databinding.FragmentVideoBinding;
import com.newblink.blink.android.base.mvpbase.BaseMvpFragment;
import com.newblink.blink.android.video.realizationmvp.VideoContract;
import com.newblink.blink.android.video.util.DataUtil;
import com.newblink.blink.android.video.util.FragmentVideoListAdapter;
import com.newblink.blink.android.video.util.PreloadManager;
import com.newblink.blink.android.video.util.ProxyVideoCacheManager;
import com.newblink.blink.android.video.util.Utils;
import com.newblink.blink.android.video.util.VerticalViewPager;
import com.newblink.blink.android.video.util.VideoInfoBean;
import com.newblink.blink.android.video.util.VideoListItemController;
import com.newblink.blink.android.video.util.VideoListItemRenderViewFactory;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends BaseMvpFragment<FragmentVideoBinding,VideoView,VideoContract.Presenter> implements VideoContract.View{

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }


    /**
     * 当前播放位置
     */
    private int mCurPos;
    private List<VideoInfoBean> mVideoList = new ArrayList<>();
    private FragmentVideoListAdapter mFragmentVideoListAdapter;
    private VerticalViewPager mViewPager;

    private PreloadManager mPreloadManager;

    private VideoListItemController mController;

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        initViewPager();
        initVideoView();
        mPreloadManager = PreloadManager.getInstance(context);
        addData(null);
        int index = 0;
        mViewPager.setCurrentItem(index);
        mViewPager.post(new Runnable() {
            @Override
            public void run() {
                startPlay(index);
            }
        });
    }

    public void addData(View view) {
        mVideoList.addAll(DataUtil.getTiktokDataFromAssets(context));
        mFragmentVideoListAdapter.notifyDataSetChanged();
    }

    private void initVideoView() {
        mVideoView = new VideoView(context);
        mVideoView.setLooping(true);

        //以下只能二选一，看你的需求
        mVideoView.setRenderViewFactory(VideoListItemRenderViewFactory.create());
        //        mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);

        mController = new VideoListItemController(context);
        mVideoView.setVideoController(mController);
    }

    private void initViewPager() {
        mViewPager = vBinding.vvp;
        mViewPager.setOffscreenPageLimit(4);
        mFragmentVideoListAdapter = new FragmentVideoListAdapter(mVideoList);
        mViewPager.setAdapter(mFragmentVideoListAdapter);
        mViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            private int mCurItem;

            /**
             * VerticalViewPager是否反向滑动
             */
            private boolean mIsReverseScroll;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (position == mCurItem) {
                    return;
                }
                mIsReverseScroll = position < mCurItem;
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == mCurPos) return;
                startPlay(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == VerticalViewPager.SCROLL_STATE_DRAGGING) {
                    mCurItem = mViewPager.getCurrentItem();
                }

                if (state == VerticalViewPager.SCROLL_STATE_IDLE) {
                    mPreloadManager.resumePreload(mCurPos, mIsReverseScroll);
                } else {
                    mPreloadManager.pausePreload(mCurPos, mIsReverseScroll);
                }
            }
        });
    }

    private void startPlay(int position) {
        int count = mViewPager.getChildCount();
        for (int i = 0; i < count; i ++) {
            View itemView = mViewPager.getChildAt(i);
            FragmentVideoListAdapter.ViewHolder viewHolder = (FragmentVideoListAdapter.ViewHolder) itemView.getTag();
            if (viewHolder.mPosition == position) {
                mVideoView.release();
                Utils.removeViewFormParent(mVideoView);

                VideoInfoBean videoInfoBean = mVideoList.get(position);
                String playUrl = mPreloadManager.getPlayUrl(videoInfoBean.videoDownloadUrl);
                L.i("startPlay: " + "position: " + position + "  url: " + playUrl);
                mVideoView.setUrl(playUrl);
                mController.addControlComponent(viewHolder.mVideoListItemView, true);
                viewHolder.mPlayerContainer.addView(mVideoView, 0);
                mVideoView.start();
                mCurPos = position;
                break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPreloadManager.removeAllPreloadTask();
        //清除缓存，实际使用可以不需要清除，这里为了方便测试
        ProxyVideoCacheManager.clearAllCache(context);

    }



}
