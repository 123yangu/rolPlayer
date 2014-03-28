package com.rolmex.android.rolplayer.utils;

import android.content.res.Resources;
import android.util.TypedValue;


public class Util {
	//app key and secret ???????????????????????APPKEY
	public static final String APPKEY = "3000004048";
	public static final String SECRET = "a3de652e5554f8e4";
	public static int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }
}
