
package com.rolmex.android.rolplayer;

import com.rolmex.android.rolplayer.ui.HomeActivity;
import com.rolmex.android.rolplayer.utils.Util;
import com.wole56.sdk.APP;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;

public class SplashActivity extends Activity {
    
    private Handler myHandler = new Handler(){
        
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case 0:
                    directTo();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        init();
    }
    private void init(){
       new Thread(){
           @Override
           public void run(){
               super.run();
               APP.init(getApplicationContext(), Util.APPKEY, Util.SECRET);
               myHandler.sendEmptyMessage(0);
           }
       }.start();
    }
    private void directTo(){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

}
