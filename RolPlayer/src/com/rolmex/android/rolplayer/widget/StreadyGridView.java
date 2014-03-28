package com.rolmex.android.rolplayer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class StreadyGridView extends GridView{
    
    public StreadyGridView(Context context) {
        super(context, null, 0);
        // TODO Auto-generated constructor stub
    }
    public StreadyGridView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public StreadyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        int expandSpec = MeasureSpec.makeMeasureSpec( 
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST); 
        
        super.onMeasure(widthMeasureSpec, expandSpec); 
    
    }

}
