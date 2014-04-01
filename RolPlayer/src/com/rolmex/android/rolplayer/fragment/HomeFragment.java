package com.rolmex.android.rolplayer.fragment;

import com.rolmex.android.rolplayer.R;
import com.rolmex.android.rolplayer.widget.JazzyViewPager;
import com.rolmex.android.rolplayer.widget.JazzyViewPager.TransitionEffect;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

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
    

    
    private ArrayList<Fragment> pagerItemList = new ArrayList<Fragment>();

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
        HomeHistory history_fragment = new HomeHistory();
        pagerItemList.add(history_fragment);
        HomeMain main_fragment = new HomeMain();
        pagerItemList.add(main_fragment);
        HomeCatory catory_gragment = new HomeCatory();
        pagerItemList.add(catory_gragment);
        
        
        viewPager.setTransitionEffect(TransitionEffect.Accordion);
        MyAdapter adapter = new MyAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);        
        return mView;
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        viewPager.setOnPageChangeListener(new OnPageChangeListener(){

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
               
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
               
                if (arg1 > 0.0 && arg1<1.0)
                    slidView.setTranslationX(item_width * arg1+arg0*item_width);
            }

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub

                slidView.setTranslationX(item_width * arg0);
                upTitle(arg0);
            }
            
        });
//         history_view,home_view,catory_view;
        history_view.setOnClickListener(buttonListener);
        home_view.setOnClickListener(buttonListener);
        catory_view.setOnClickListener(buttonListener);
        viewPager.setCurrentItem(1);
    }
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if(v == history_view){
                viewPager.setCurrentItem(0);
                upTitle(0);
            }
            if(v == home_view){
                viewPager.setCurrentItem(1);
                upTitle(1);
            }
            if(v==catory_view){
                viewPager.setCurrentItem(2);
                upTitle(2);
            }
        }
    };
    private void upTitle(int index){
        switch(index){
            case 0:
                history_view.setTextColor(mContext.getResources().getColor(R.color.holo_blue));
                home_view.setTextColor(Color.BLACK);
                catory_view.setTextColor(Color.BLACK);
                break;
            case 1:
                history_view.setTextColor(Color.BLACK);
                home_view.setTextColor(mContext.getResources().getColor(R.color.holo_blue));
                catory_view.setTextColor(Color.BLACK);
                break;
            case 2:
                history_view.setTextColor(Color.BLACK);
                home_view.setTextColor(Color.BLACK);
                catory_view.setTextColor(mContext.getResources().getColor(R.color.holo_blue));
                break;
                default:
                    break;
        }
    }
    public class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int position) {
            // TODO Auto-generated method stub
            Fragment fragment = null;
            if(position<pagerItemList.size())
                fragment = pagerItemList.get(position);
            else
                fragment = pagerItemList.get(0);
            viewPager.setObjectForPosition(fragment, position);
            return fragment;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return pagerItemList.size();
        }
        
    }
    

}
