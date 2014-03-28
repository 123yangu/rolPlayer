package com.rolmex.android.rolplayer.fragment;

import com.rolmex.android.rolplayer.R;
import com.rolmex.android.rolplayer.widget.JazzyViewPager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    
    //节目表单
    private TextView history_view,home_view,catory_view;
    //JazzyViewPager
    private JazzyViewPager viewPager;
    
    private FrameLayout fl;
    
    private Context mContext;
    
    private int screen_width;
    
    private int item_width; 
    //滑动游标
    private TextView slidView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        mContext = getActivity();
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        screen_width = dm.widthPixels;
        item_width = screen_width/3-1;
        
        View mView = inflater.inflate(R.layout.fragment_home, null);
        history_view = (TextView)mView.findViewById(R.id.home_history_view);
        home_view = (TextView)mView.findViewById(R.id.home_main_view);
        catory_view = (TextView)mView.findViewById(R.id.home_catory_view);
        fl= (FrameLayout)mView.findViewById(R.id.home_fl);
        viewPager = (JazzyViewPager)mView.findViewById(R.id.home_viewpager);
        
        /**
         * viewPager游标
         */
        TextView placeView = new TextView(mContext);
        placeView.setLayoutParams(new FrameLayout.LayoutParams(item_width * 3, 6));
        placeView.setBackgroundResource(R.color.half_white);
        fl.addView(placeView);
        slidView = new TextView(mContext);
        slidView.setLayoutParams(new FrameLayout.LayoutParams(item_width, 6));
        slidView.setBackgroundResource(R.color.holo_blue);
        fl.addView(slidView);
        
        return mView;
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }
    
    

}
