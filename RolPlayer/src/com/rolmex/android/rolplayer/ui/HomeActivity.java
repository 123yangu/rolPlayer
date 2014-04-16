package com.rolmex.android.rolplayer.ui;

import com.rolmex.android.rolplayer.BaseActivity;
import com.rolmex.android.rolplayer.R;
import com.rolmex.android.rolplayer.fragment.HomeFragment;
import com.wole56.sdk.Video;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Window;


public class HomeActivity extends BaseActivity{
    
    private HomeFragment home_fragment;
    
    
    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case 1:
                   
                    break;
            }
        }
    };
    


    @Override
    protected void init() {
        // TODO Auto-generated method stub
        FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
        home_fragment = new HomeFragment();
        t.replace(R.id.home_fragment, home_fragment);
        t.commit();
    }

    @Override
    protected int getLayout() {
        // TODO Auto-generated method stub
        return R.layout.activity_home;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
   
    

}
