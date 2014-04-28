package com.rolmex.android.rolplayer.service;

import com.rolmex.android.rolplayer.MyApplication;
import com.rolmex.android.rolplayer.R;
import com.rolmex.android.rolplayer.widget.MarqueeText;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.widget.CenterLayout;
import io.vov.vitamio.widget.VideoView;

public class WindowPlayer extends Service  implements OnInfoListener, OnBufferingUpdateListener {
    private WindowManager windowManager = null;
    private WindowManager.LayoutParams wmParams = null;
    
    private float mTouchStartX;
    private float mTouchStartY;
    private float startX;
    private float startY;
    private float x;
    private float y;
    
    private RelativeLayout prepare_ly;

    private VideoView player_buffer;

    private MarqueeText player_title;

    private TextView player_btn_back, player_btn_start;

    private TextView player_time;

    private String path = "http://119.167.146.12/fcs126.56.com/flvdownload/28/18/13950338514hd_clear.flv.mp4?t=_vvdH13rtMTryFzRk7W70w&r=47460&e=1395996812&v=1&s=1&tt=344&sz=24137946&vid=109381429";

    // private String path="";
    private Uri uri;

    private boolean isStart;

    private SeekBar player_seekbar;

    private ProgressBar pb;

    private GestureDetector mGestureDetector;

    private RelativeLayout player_top_ly, player_bottom_ly;

    private Button suspend_btn;

    private CenterLayout player_buffer_content;

    private int screen_width, screen_height;

    private View viFloatingWindow; 
    
    public void onCreate(){
        
        super.onCreate();
       
    }
    @Override
    
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
       
      
        screen_width = intent.getIntExtra("screen_width", 0);
        screen_height = intent.getIntExtra("screen_height", 0);
      
        viFloatingWindow = LayoutInflater.from(this).inflate(R.layout.float_window, null);
        player_buffer = (VideoView)viFloatingWindow.findViewById(R.id.player_buffer);
        player_top_ly = (RelativeLayout)viFloatingWindow.findViewById(R.id.player_top_ly);
        player_bottom_ly = (RelativeLayout)viFloatingWindow.findViewById(R.id.player_bottom_ly);
        prepare_ly = (RelativeLayout)viFloatingWindow.findViewById(R.id.player_prepare_ly);
        suspend_btn = (Button)viFloatingWindow.findViewById(R.id.player_suspend_btn);
        prepare_ly.setVisibility(View.GONE);

        player_title = (MarqueeText)viFloatingWindow.findViewById(R.id.player_title);
        player_btn_back = (TextView)viFloatingWindow.findViewById(R.id.player_btn_back);
        player_btn_start = (TextView)viFloatingWindow.findViewById(R.id.play_btn_start);
        

        player_seekbar = (SeekBar)viFloatingWindow.findViewById(R.id.player_seekbar);

        player_time = (TextView)viFloatingWindow.findViewById(R.id.player_time);
        player_buffer_content = (CenterLayout)viFloatingWindow.findViewById(R.id.player_buffer_content);
        
        player_buffer_content.setLayoutParams(new RelativeLayout.LayoutParams(screen_width,
                getCenterBufferHeight()));
       
        createFloaingWindow();
        prepareVideo(path);
        return super.onStartCommand(intent, flags, startId);
    }
    
    private void prepareVideo(String path) {
        if (path == "") {

        } else {
            uri = Uri.parse(path);

            player_buffer.setVideoURI(uri);
            // player_buffer.setMediaController(new MediaController(this));
            player_buffer.requestFocus();
            player_buffer.setOnInfoListener(this);
            player_buffer.setOnBufferingUpdateListener(this);
            player_buffer.setBufferSize(512 * 1024);
            player_buffer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    mp.setPlaybackSpeed(1.0f);
                }
            });
            prepare_ly.setVisibility(View.GONE);
            playerStart();
            upStartBtnBg(isStart);
           
        }
    }
    
    private void playerStart() {
        suspend_btn.setVisibility(View.GONE);
        if (player_buffer.isPlaying())
            return;
        player_buffer.start();
        isStart = true;
    }

    private void playerPause() {
        player_buffer.pause();
        suspend_btn.setVisibility(View.VISIBLE);
        isStart = false;
    }

    private void playOrPause() {
        if (player_buffer.isPlaying()) {
            playerPause();
        } else {
            playerStart();
        }
    }
    
    private void upStartBtnBg(boolean isStart) {
        if (isStart) {
            player_btn_start.setBackground(this.getResources().getDrawable(
                    R.drawable.play_detail_btn_start));
        } else {
            player_btn_start.setBackground(this.getResources().getDrawable(
                    R.drawable.play_detail_btn_suspend));
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
    
    private void createFloaingWindow(){
        windowManager = (WindowManager)getApplicationContext().getSystemService("window");
        wmParams = ((MyApplication) getApplication()).getMywmParams();
        wmParams.type = 2002;
        wmParams.flags |=8;
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;
        wmParams.x = 0;
        wmParams.y = 0;
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.format = 1;     
        wmParams.dimAmount =1.0f;
        wmParams.alpha =1.0f;
        windowManager.addView(viFloatingWindow, wmParams);
        viFloatingWindow.setOnTouchListener(new OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                x = event.getRawX();
                y = event.getRawY() -25;
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = x;
                        startY = y;
                        mTouchStartX = event.getX();
                        mTouchStartY = event.getY();
                      
                        break;
                    case MotionEvent.ACTION_MOVE:
                        updateViewPosition();
                        break;
                    case MotionEvent.ACTION_UP:
                        updateViewPosition();
                       
                        mTouchStartX = mTouchStartY =0;
                }
                return true;
            }
            
        });
    }
    private void updateViewPosition(){
        wmParams.x = (int)(x-mTouchStartX);
        wmParams.y = (int)(y-mTouchStartY);
        windowManager.updateViewLayout(viFloatingWindow, wmParams);
    }
    
    private int getCenterBufferHeight() {
        return (screen_width * screen_width) / screen_height;
    }
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        player_buffer.stopPlayback();
    }

}
