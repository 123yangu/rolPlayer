package com.rolmex.android.rolplayer.utils;

import android.content.res.Resources;
import android.util.TypedValue;


public class Util {
	//app key and secret ???????????????????????APPKEY
	public static final String APPKEY = "3000004048";
	public static final String SECRET = "a3de652e5554f8e4";
	/**
	 * 最热视频
	 */
	public static final String HOT_CHANNEL = "2";
	public static final String YL_CHANNEL = "1";
	public static final String YC_CHANNEL = "3";
	public static final String YY_CHANNEL = "41";
	public static final String TY_CHANNEL = "14";
	public static final String GX_CHANNEL = "4";
	public static final String QC_CHANNEL = "28";
	public static final String DM_CHANNEL = "8";
	public static final String YX_CHANNEL = "26";
	public static final String NX_CHANNEL = "11";
	public static final String MY_CHANNEL = "34";
	public static final String LY_CHANNEL = "27";
	public static final String KJ_CHANNEL = "10";
	/**
	 * 推荐视频
	 * @param res
	 * @param dp
	 * @return
	 */
	public static final String JRRD = "1";
	public static final String ZY_CHANNEL = "4";
	public static final String DY_CHANNEL = "6";
	public static final String WXZB_CHANNEL = "9";
	public static final String WLHR_CHANNEL = "11";
	public static final String QC= "13";
	public static final String NX = "15";
	public static final String YLRD = "3";
	public static final String DSJ = "5";
	public static final String MNZB = "8";
	public static final String HXGX = "10";
	public static final String PKJL = "12";
	public static final String YX = "14";
	public static final String MY = "16";
	
	public static int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }
}
