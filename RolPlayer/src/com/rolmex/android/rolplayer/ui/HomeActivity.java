package com.rolmex.android.rolplayer.ui;

import com.rolmex.android.rolplayer.R;
import com.wole56.sdk.Video;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class HomeActivity extends Activity{
    
    private TextView textView;
    
    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case 1:
                    textView.setText(msg.obj+"");
                    Log.e("antking", msg.obj+"");
                    break;
            }
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView = (TextView)this.findViewById(R.id.textview);
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
