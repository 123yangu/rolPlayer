
package com.rolmex.android.rolplayer.ui;

import com.rolmex.android.rolplayer.Api;
import com.rolmex.android.rolplayer.BaseActivity;
import com.rolmex.android.rolplayer.R;
import com.rolmex.android.rolplayer.model.FileItem;
import com.rolmex.android.rolplayer.model.InfoItem;
import com.rolmex.android.rolplayer.model.Result;
import com.rolmex.android.rolplayer.task.Task;
import com.rolmex.android.rolplayer.task.Task.TaskCallback;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.utils.Log;

import io.vov.vitamio.widget.VideoView;

public class PlayActivity extends BaseActivity implements OnInfoListener, OnBufferingUpdateListener {

    private RelativeLayout prepare_ly;

    private VideoView player_buffer;

    private TextView player_title, player_btn_back, player_btn_start;

    private String path = "http://119.167.146.12/fcs126.56.com/flvdownload/28/18/13950338514hd_clear.flv.mp4?t=_vvdH13rtMTryFzRk7W70w&r=47460&e=1395996812&v=1&s=1&tt=344&sz=24137946&vid=109381429";

    // private String path="";
    private Uri uri;

    private boolean isStart;
    
    private SeekBar player_seekbar;

    private ProgressBar pb;

    @Override
    protected void init() {
        // TODO Auto-generated method stub
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        Intent intent = getIntent();

        String vid = intent.getStringExtra("vid");
        initUI();
        loadData(vid);

    }

    private void initUI() {
        player_buffer = (VideoView)this.findViewById(R.id.player_buffer);
        prepare_ly = (RelativeLayout)this.findViewById(R.id.player_prepare_ly);
        prepare_ly.setVisibility(View.GONE);

        player_title = (TextView)this.findViewById(R.id.player_title);
        player_btn_back = (TextView)this.findViewById(R.id.player_btn_back);
        player_btn_start = (TextView)this.findViewById(R.id.play_btn_start);
        
        player_seekbar = (SeekBar)this.findViewById(R.id.player_seekbar);

        player_btn_back.setOnClickListener(buttonListener);
        player_btn_start.setOnClickListener(buttonListener);
        
    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (v == player_btn_back) {
                player_buffer.stopPlayback();
                finish();
            }
            if(v == player_btn_start){
                playOrPause();
                upStartBtnBg(isStart);
                player_seekbar.setProgress((int)(player_buffer.getCurrentPosition()*100/player_buffer.getDuration()));
            }

        }
    };

    private void loadData(String vid) {
        Api.getVideoAddr(getApplicationContext(), vid, new TaskCallback() {

            @Override
            public void onTaskComplete(Task task, Result result) {
                // TODO Auto-generated method stub
                bindData(result);
            }

        });
    }

    private void bindData(Result result) {
        if (result.info == null) {
            return;
        }
        Log.e(result.msg, "antking_info");
        InfoItem info = result.info;
        player_title.setText(info.tags);
        List<FileItem> list = info.rfiles;
        Log.e(list.size() + "", list.size() + "");
        String path = "";
        for (FileItem item : list) {

            if (item.type.equals("qvga")) {
                path = item.url;
                break;
            }

        }
        if (path == "") {
            path = list.get(0).url;
        }
        prepareVideo(path);
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
            player_buffer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    mp.setPlaybackSpeed(1.0f);
                }
            });
            prepare_ly.setVisibility(View.GONE);
            playerStart();
           
        }
    }
    private void playerStart(){
        if(player_buffer.isPlaying())
            return;
        player_buffer.start();
        isStart = true;
    }
    private void playerPause(){
        player_buffer.pause();
        isStart = false;
    }
    private void playOrPause(){
        if(player_buffer.isPlaying()){
            playerPause();
        }else{
            playerStart();
        }
    }
    
    private void upStartBtnBg(boolean isStart){
        if(isStart){
            player_btn_start.setBackground(this.getResources().getDrawable(R.drawable.play_detail_btn_suspend));
        }else{
            player_btn_start.setBackground(this.getResources().getDrawable(R.drawable.play_detail_btn_start));
        }
    }
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected int getLayout() {
        // TODO Auto-generated method stub
        return R.layout.activity_player;
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

}
