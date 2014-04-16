package com.rolmex.android.rolplayer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import io.vov.vitamio.utils.Log;

public abstract class BaseActivity extends FragmentActivity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        showView(getLayout());
        Log.e("Oncreate", "Oncreate");
    }
    
    protected void showView(int id){
        setContentView(id);
        init();
    }
    
    /*
     * 初始化页面
     */
    abstract protected void init();
    
    /**
     * 获取页面
     */
    abstract protected int getLayout();
    

}
