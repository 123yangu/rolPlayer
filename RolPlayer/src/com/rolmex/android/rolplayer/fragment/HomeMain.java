
package com.rolmex.android.rolplayer.fragment;

import com.rolmex.android.rolplayer.Api;
import com.rolmex.android.rolplayer.R;
import com.rolmex.android.rolplayer.adapter.ItemAdapter;
import com.rolmex.android.rolplayer.adapter.RecommendAdapter;
import com.rolmex.android.rolplayer.model.CommendItem;
import com.rolmex.android.rolplayer.model.CommendResult;
import com.rolmex.android.rolplayer.model.HotItemBean;
import com.rolmex.android.rolplayer.model.Result;
import com.rolmex.android.rolplayer.task.CommendTask;
import com.rolmex.android.rolplayer.task.CommendTask.CTaskCallback;
import com.rolmex.android.rolplayer.task.Task;
import com.rolmex.android.rolplayer.task.Task.TaskCallback;
import com.rolmex.android.rolplayer.utils.OutlineContainer;
import com.rolmex.android.rolplayer.utils.Util;
import com.rolmex.android.rolplayer.widget.JazzyViewPager;
import com.rolmex.android.rolplayer.widget.JazzyViewPager.TransitionEffect;
import com.rolmex.android.rolplayer.widget.StreadyGridView;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import java.util.List;


import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

public class HomeMain extends Fragment {
    
    private JazzyViewPager viewPager;
    
    private PullToRefreshScrollView scrollView;
    
    private Context mContext;
    
    private Resources res;
    
    private StreadyGridView hot_gridView,yu_gridView,gx_gridView,pk_gridView,yy_gridView,yc_gridView,dy_gridView,dsj_gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        res = mContext.getResources();
        View mView = inflater.inflate(R.layout.home_main, null);
        scrollView = (PullToRefreshScrollView)mView.findViewById(R.id.home_main_scrollView);
        viewPager = (JazzyViewPager)mView.findViewById(R.id.home_main_viewpager);
        setupJazziness(TransitionEffect.Accordion);
        initGridView(mView);
        
      
        return mView;
    }
    
    @Override
    public void onStart() {
        loadData();
         super.onStart();
    }
    
    private void loadData(){
        Api.getHotVideo(mContext, Util.HOT_CHANNEL, "4", "0", new TaskCallback(){

            @Override
            public void onTaskComplete(Task task, Result result) {
                // TODO Auto-generated method stub
                bindData(result);
            }
            
        });
        Api.getHotVideo(mContext, Util.YL_CHANNEL, "4", "0", new TaskCallback(){

            @Override
            public void onTaskComplete(Task task, Result result) {
                // TODO Auto-generated method stub
                bindYLData(result);
            }
            
        });
        Api.getHotVideo(mContext, Util.GX_CHANNEL, "4", "0", new TaskCallback(){

            @Override
            public void onTaskComplete(Task task, Result result) {
                // TODO Auto-generated method stub
                bindGXData(result);
            }
            
        });
        Api.getHotVideo(mContext, Util.YY_CHANNEL, "4", "0", new TaskCallback(){

            @Override
            public void onTaskComplete(Task task, Result result) {
                // TODO Auto-generated method stub
                bindYYData(result);
            }
            
        });
        Api.getHotVideo(mContext, Util.YC_CHANNEL, "4", "0", new TaskCallback(){

            @Override
            public void onTaskComplete(Task task, Result result) {
                // TODO Auto-generated method stub
                bindYCData(result);
            }
            
        });
        Api.getHotVideo(mContext, Util.DM_CHANNEL, "4", "0", new TaskCallback(){

            @Override
            public void onTaskComplete(Task task, Result result) {
                // TODO Auto-generated method stub
                bindDMData(result);
            }
            
        });
        Api.getRecommendVideo(mContext, Util.DY_CHANNEL, "6", "0", new CTaskCallback(){

            @Override
            public void onTaskComplete(CommendTask task, CommendResult result) {
                // TODO Auto-generated method stub
                bindDYData(result);
            }
     
        });
        Api.getRecommendVideo(mContext, Util.DSJ, "6", "0", new CTaskCallback(){

            @Override
            public void onTaskComplete(CommendTask task, CommendResult result) {
                // TODO Auto-generated method stub
                bindDSJData(result);
            }
            
        });
    }
    private void bindDSJData(CommendResult result){
        List<CommendItem> list = result.list;
        RecommendAdapter dsjAdapter = new RecommendAdapter(mContext,list);
        dsj_gridView.setAdapter(dsjAdapter);
    }
    private void bindDYData(CommendResult result){
        List<CommendItem> list = result.list;
        RecommendAdapter dyAdapter = new RecommendAdapter(mContext,list);
        dy_gridView.setAdapter(dyAdapter);
        
    }
    private void bindDMData(Result result){
        List<HotItemBean> list = result.list;
        ItemAdapter dmAdapter = new ItemAdapter(mContext,list);
        pk_gridView.setAdapter(dmAdapter);
    }
    private void bindYCData(Result result){
        List<HotItemBean> list = result.list;
        ItemAdapter ycAdapter = new ItemAdapter(mContext,list);
        yc_gridView.setAdapter(ycAdapter);
    }
    private void bindYYData(Result result){
        List<HotItemBean> list = result.list;
        ItemAdapter yyAdapter = new ItemAdapter(mContext,list);
        yy_gridView.setAdapter(yyAdapter);
    }
    private void bindGXData(Result result){
        List<HotItemBean> list = result.list;
        ItemAdapter  gxAdapter = new ItemAdapter(mContext,list);
        gx_gridView.setAdapter(gxAdapter);
    }
    private void bindYLData(Result result){
        List<HotItemBean> list = result.list;
        ItemAdapter yuleAdapter= new ItemAdapter(mContext,list);
        yu_gridView.setAdapter(yuleAdapter);
    }
    private void bindData(Result result){
        List<HotItemBean> list = result.list;
        ItemAdapter hotAdapter = new ItemAdapter(mContext,list);
        hot_gridView.setAdapter(hotAdapter);
        
    }
    private void initGridView(View view){
       
        
        hot_gridView = (StreadyGridView)view.findViewById(R.id.hot_gridview);
       
        yu_gridView = (StreadyGridView)view.findViewById(R.id.main_yule_gridview);
        gx_gridView = (StreadyGridView)view.findViewById(R.id.main_gx_gridview);
        
        pk_gridView = (StreadyGridView)view.findViewById(R.id.main_pk_gridview);
        
        yy_gridView = (StreadyGridView)view.findViewById(R.id.main_yy_gridview);
        
        yc_gridView = (StreadyGridView)view.findViewById(R.id.main_yc_gridview);
        
        dy_gridView = (StreadyGridView)view.findViewById(R.id.main_dy_gridview);
        
        dsj_gridView = (StreadyGridView)view.findViewById(R.id.main_dsj_gridview);
       
    }
    
    private void setupJazziness(TransitionEffect effect) {

        viewPager.setTransitionEffect(effect);
        
        viewPager.setAdapter(new JazzyAdapter());
        
        viewPager.setPageMargin(30);
       
    }
   
    private class JazzyAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ScaleType.FIT_XY);
            imageView.setImageDrawable(res.getDrawable(R.drawable.top_drawable+position));
            
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
            return 7;
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
