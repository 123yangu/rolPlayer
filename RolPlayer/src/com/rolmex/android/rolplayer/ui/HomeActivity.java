package com.rolmex.android.rolplayer.ui;

import com.rolmex.android.rolplayer.R;
import com.rolmex.android.rolplayer.fragment.HomeFragment;
import com.wole56.sdk.Video;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;


public class HomeActivity extends FragmentActivity{
    
    private HomeFragment home_fragment;
    
    
    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case 1:
                    Log.e("antking", msg.obj+"");
                    break;
            }
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
        home_fragment = new HomeFragment();
        t.replace(R.id.home_fragment, home_fragment);
        t.commit();
        loadData();
        
    }
    private void loadData(){
        new Thread(){
            @Override
            public void run(){
//               String data = Video.getRecommendVideo(getApplicationContext(), "10", "4", "0").toString();
                String data = Video.getVideoAddress(getApplicationContext(), "109381429").toString();
                
               Message msg = new Message();
               msg.what = 1;
               msg.obj = data;
               mHandler.sendMessage(msg);
            }
        }.start();
    }
    

}
