package com.rolmex.android.rolplayer.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

public class MarqueeText extends TextView{
    
    public MarqueeText(Context con){
        super(con);
    }
    
    public MarqueeText(Context context,AttributeSet attrs){
        super(context,attrs);
    }
    
    public MarqueeText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }
    @Override
    public boolean isFocused(){
        return true;
    }
    @Override
    protected void onFocusChanged(boolean focused,int direction,Rect previouslyFocusedRect){
        
    }

}
