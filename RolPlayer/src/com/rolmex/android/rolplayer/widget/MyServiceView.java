package com.rolmex.android.rolplayer.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MyServiceView extends SurfaceView implements Callback,Runnable{
    
    boolean mbLoop = false;
    
    //定义SurfaceHolder
    SurfaceHolder mSurfaceHolder = null;
    
    int miCount = 0;
    
    int y = 50;
    

    public MyServiceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mSurfaceHolder = this.getHolder();
        
        mSurfaceHolder.addCallback(this);
        this.setFocusable(true);
        mbLoop = true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        mbLoop = false;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(mbLoop){
            try{
                Thread.sleep(200);
            }catch(Exception e){
                
            }
            synchronized(mSurfaceHolder){
                Draw();
            }
        }
    }
    
    public void Draw(){
        Canvas canvas = mSurfaceHolder.lockCanvas();
        if(mSurfaceHolder==null||canvas==null){
            return;
        }
        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        canvas.drawColor(Color.BLACK);
        mSurfaceHolder.unlockCanvasAndPost(canvas);
        
    }

}
