
package com.rolmex.android.rolplayer.fragment;

import com.rolmex.android.rolplayer.Api;
import com.rolmex.android.rolplayer.R;
import com.rolmex.android.rolplayer.adapter.ItemAdapter;
import com.rolmex.android.rolplayer.adapter.RecommendAdapter;
import com.rolmex.android.rolplayer.model.CommendItem;
import com.rolmex.android.rolplayer.model.CommendResult;
import com.rolmex.android.rolplayer.model.HotItemBean;
import com.rolmex.android.rolplayer.model.Result;
import com.rolmex.android.rolplayer.service.WindowPlayer;
import com.rolmex.android.rolplayer.task.CommendTask;
import com.rolmex.android.rolplayer.task.CommendTask.CTaskCallback;
import com.rolmex.android.rolplayer.task.Task;
import com.rolmex.android.rolplayer.task.Task.TaskCallback;
import com.rolmex.android.rolplayer.ui.PlayActivity;
import com.rolmex.android.rolplayer.utils.OutlineContainer;
import com.rolmex.android.rolplayer.utils.Util;
import com.rolmex.android.rolplayer.widget.JazzyViewPager;
import com.rolmex.android.rolplayer.widget.JazzyViewPager.TransitionEffect;
import com.rolmex.android.rolplayer.widget.StreadyGridView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

public class HomeMain extends Fragment {

    private JazzyViewPager viewPager;

    private PullToRefreshScrollView scrollView;

    private Context mContext;

    private Resources res;

    private RelativeLayout hot_channel_ly, yl_channel_ly, gx_channel_ly, dm_channel_ly,
            yy_channel_ly, yc_channel_ly, dy_channel_ly, dsj_channel_ly;

    private StreadyGridView hot_gridView, yu_gridView, gx_gridView, pk_gridView, yy_gridView,
            yc_gridView, dy_gridView, dsj_gridView;

    private boolean isChange = true;

    private Thread cThread;

    private RadioGroup page_rg;

    private LayoutInflater mLayoutInflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        res = mContext.getResources();
        mLayoutInflater = LayoutInflater.from(mContext);
        View mView = inflater.inflate(R.layout.home_main, null);
        scrollView = (PullToRefreshScrollView)mView.findViewById(R.id.home_main_scrollView);
        viewPager = (JazzyViewPager)mView.findViewById(R.id.home_main_viewpager);
        page_rg = (RadioGroup)mView.findViewById(R.id.home_main_pager_bottom_rg);
        viewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isChange = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isChange = true;
                        break;
                    default:
                        isChange = true;
                        break;
                }
                return false;
            }
        });
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                ((RadioButton)page_rg.getChildAt(arg0 % 7)).setChecked(true);
            }

        });
        setupJazziness(TransitionEffect.CubeOut);

        initUI(mView);
        initGridView(mView);
        for (int i = 0; i < 7; i++) {
            RadioButton rb = (RadioButton)inflater.inflate(R.layout.radiobutton, null);
            page_rg.addView(rb);
        }
        ((RadioButton)page_rg.getChildAt(0)).setChecked(true);
        cThread = new Thread(pagerChange);
        cThread.start();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
         isChange = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isChange = false;
    }

    @Override
    public void onStart() {
        loadData();
       
        super.onStart();
    }

    private Handler myHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (!isChange)
                        return;
                    int curItem = viewPager.getCurrentItem();
                    if (curItem < Integer.MAX_VALUE)
                        viewPager.setCurrentItem((curItem + 1));
                    else
                        viewPager.setCurrentItem((0));
                    break;
            }
        }

    };

    private Runnable pagerChange = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (!((Activity)mContext).isFinishing()) {
                if (isChange) {
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {

                    }
                   
                    myHandler.sendEmptyMessage(0);
                }
            }
        }

    };

    private void initUI(View view) {
        hot_channel_ly = (RelativeLayout)view.findViewById(R.id.hot_channel_ly);
        hot_channel_ly.setOnClickListener(buttonListener);
        yl_channel_ly = (RelativeLayout)view.findViewById(R.id.yl_channel_ly);
        yl_channel_ly.setOnClickListener(buttonListener);
        gx_channel_ly = (RelativeLayout)view.findViewById(R.id.gx_channel_ly);
        gx_channel_ly.setOnClickListener(buttonListener);
        dm_channel_ly = (RelativeLayout)view.findViewById(R.id.dm_channel_ly);
        dm_channel_ly.setOnClickListener(buttonListener);
        yy_channel_ly = (RelativeLayout)view.findViewById(R.id.yy_channel_ly);
        yy_channel_ly.setOnClickListener(buttonListener);
        yc_channel_ly = (RelativeLayout)view.findViewById(R.id.yc_channel_ly);
        yc_channel_ly.setOnClickListener(buttonListener);
        dy_channel_ly = (RelativeLayout)view.findViewById(R.id.dy_channel_ly);
        dy_channel_ly.setOnClickListener(buttonListener);
        dsj_channel_ly = (RelativeLayout)view.findViewById(R.id.dsj_channel_ly);
        dsj_channel_ly.setOnClickListener(buttonListener);
    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

        }
    };

    private void loadData() {
        Api.getHotVideo(mContext, Util.HOT_CHANNEL, "4", "0", new TaskCallback() {

            @Override
            public void onTaskComplete(Task task, Result result) {
                // TODO Auto-generated method stub
                bindData(result);
            }

        });
        Api.getHotVideo(mContext, Util.YL_CHANNEL, "4", "0", new TaskCallback() {

            @Override
            public void onTaskComplete(Task task, Result result) {
                // TODO Auto-generated method stub
                bindYLData(result);
            }

        });
        Api.getHotVideo(mContext, Util.GX_CHANNEL, "4", "0", new TaskCallback() {

            @Override
            public void onTaskComplete(Task task, Result result) {
                // TODO Auto-generated method stub
                bindGXData(result);
            }

        });
        Api.getHotVideo(mContext, Util.YY_CHANNEL, "4", "0", new TaskCallback() {

            @Override
            public void onTaskComplete(Task task, Result result) {
                // TODO Auto-generated method stub
                bindYYData(result);
            }

        });
        Api.getHotVideo(mContext, Util.YC_CHANNEL, "4", "0", new TaskCallback() {

            @Override
            public void onTaskComplete(Task task, Result result) {
                // TODO Auto-generated method stub
                bindYCData(result);
            }

        });
        Api.getHotVideo(mContext, Util.DM_CHANNEL, "4", "0", new TaskCallback() {

            @Override
            public void onTaskComplete(Task task, Result result) {
                // TODO Auto-generated method stub
                bindDMData(result);
            }

        });
        Api.getRecommendVideo(mContext, Util.DY_CHANNEL, "6", "0", new CTaskCallback() {

            @Override
            public void onTaskComplete(CommendTask task, CommendResult result) {
                // TODO Auto-generated method stub
                bindDYData(result);
            }

        });
        Api.getRecommendVideo(mContext, Util.DSJ, "6", "0", new CTaskCallback() {

            @Override
            public void onTaskComplete(CommendTask task, CommendResult result) {
                // TODO Auto-generated method stub
                bindDSJData(result);
            }

        });
    }

    private void bindDSJData(CommendResult result) {
        if (result.list == null)
            return;
        List<CommendItem> list = result.list;
        RecommendAdapter dsjAdapter = new RecommendAdapter(mContext, list);
        dsj_gridView.setAdapter(dsjAdapter);
    }

    private void bindDYData(CommendResult result) {
        if (result.list == null)
            return;
        List<CommendItem> list = result.list;
        RecommendAdapter dyAdapter = new RecommendAdapter(mContext, list);
        dy_gridView.setAdapter(dyAdapter);

    }

    private void bindDMData(Result result) {
        if (result.list == null)
            return;
        List<HotItemBean> list = result.list;
        ItemAdapter dmAdapter = new ItemAdapter(mContext, list);
        pk_gridView.setAdapter(dmAdapter);
    }

    private void bindYCData(Result result) {
        if (result.list == null)
            return;
        List<HotItemBean> list = result.list;
        ItemAdapter ycAdapter = new ItemAdapter(mContext, list);
        yc_gridView.setAdapter(ycAdapter);
    }

    private void bindYYData(Result result) {
        if (result.list == null)
            return;
        List<HotItemBean> list = result.list;

        ItemAdapter yyAdapter = new ItemAdapter(mContext, list);
        yy_gridView.setAdapter(yyAdapter);
    }

    private void bindGXData(Result result) {
        if (result.list == null)
            return;
        List<HotItemBean> list = result.list;
        ItemAdapter gxAdapter = new ItemAdapter(mContext, list);
        gx_gridView.setAdapter(gxAdapter);
    }

    private void bindYLData(Result result) {
        if (result.list == null)
            return;
        List<HotItemBean> list = result.list;
        ItemAdapter yuleAdapter = new ItemAdapter(mContext, list);
        yu_gridView.setAdapter(yuleAdapter);
    }

    private void bindData(Result result) {
        if (result.list == null)
            return;
        List<HotItemBean> list = result.list;
        ItemAdapter hotAdapter = new ItemAdapter(mContext, list);
        hot_gridView.setAdapter(hotAdapter);

    }

    private void initGridView(View view) {

        hot_gridView = (StreadyGridView)view.findViewById(R.id.hot_gridview);

        yu_gridView = (StreadyGridView)view.findViewById(R.id.main_yule_gridview);
        gx_gridView = (StreadyGridView)view.findViewById(R.id.main_gx_gridview);

        pk_gridView = (StreadyGridView)view.findViewById(R.id.main_pk_gridview);

        yy_gridView = (StreadyGridView)view.findViewById(R.id.main_yy_gridview);

        yc_gridView = (StreadyGridView)view.findViewById(R.id.main_yc_gridview);

        dy_gridView = (StreadyGridView)view.findViewById(R.id.main_dy_gridview);

        dsj_gridView = (StreadyGridView)view.findViewById(R.id.main_dsj_gridview);

        hot_gridView.setOnItemClickListener(itemListener);
        yu_gridView.setOnItemClickListener(itemListener);
        gx_gridView.setOnItemClickListener(itemListener);
        pk_gridView.setOnItemClickListener(itemListener);
        yy_gridView.setOnItemClickListener(itemListener);
        yc_gridView.setOnItemClickListener(itemListener);
        dy_gridView.setOnItemClickListener(itemListener);
        dsj_gridView.setOnItemClickListener(itemListener);

    }

    private OnItemClickListener itemListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // TODO Auto-generated method stub
            Object obj = parent.getAdapter().getItem(position);
            Intent intent = new Intent(mContext, PlayActivity.class);
            String uid = "";
            String title = "";
            if (obj instanceof HotItemBean) {
                uid = ((HotItemBean)obj).vid;
                title = ((HotItemBean)obj).title;
            } else if (obj instanceof CommendItem) {
                uid = ((CommendItem)obj).vid;
                title = ((CommendItem)obj).title;
            }
            Log.e("antking_vid", uid);
            intent.putExtra("vid", uid);
            intent.putExtra("title", title);
            startActivity(intent);
//            Intent serviceIntent = new Intent(mContext,WindowPlayer.class);
//            mContext.startService(serviceIntent);
        }

    };

    private void setupJazziness(TransitionEffect effect) {

        viewPager.setTransitionEffect(effect);

        viewPager.setAdapter(new JazzyAdapter());

        viewPager.setPageMargin(10);

    }

    private class JazzyAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ScaleType.FIT_XY);
            imageView.setImageDrawable(res.getDrawable(R.drawable.top_drawable + position % 7));
            container.addView(imageView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            viewPager.setObjectForPosition(imageView, position);
            return imageView;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object obj) {
            container.removeView(viewPager.findViewFromObject(position));
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            if (view instanceof OutlineContainer) {
                return ((OutlineContainer)view).getChildAt(0) == obj;
            } else {
                return view == obj;
            }
        }
    }

}
