package com.rolmex.android.rolplayer.service;


import com.rolmex.android.rolplayer.R;
import com.rolmex.android.rolplayer.widget.MyFloatView;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MediaPlaybackService extends Service{
    @Override
    public void onCreate() {
        super.onCreate();

    }




    public ViewGroup fView;
    MyFloatView sFloatView;

    private void createView(Context context) {
        if (fView != null) {
            return;
        }
        Log.e("antking", "service_create");
        fView = (ViewGroup) View.inflate(context, R.layout.main, null);
        // 显示myFloatView图像
        sFloatView = new MyFloatView(fView);
        sFloatView.bindViewListener();
        sFloatView.showLayoutView();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();

            String cmd = intent.getStringExtra("command");

            if ("createUI".equals(action)) {
                createView(this);
            } else if ("removeUI".equals(action)) {

                fView = null;
                sFloatView = null;
            }
        }

        return START_STICKY;
    }

    boolean iPlayState = false;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }





}

