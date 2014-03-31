package com.rolmex.android.rolplayer.model;

import java.util.List;

public class CommendResult {
 public static final CommendResult DEFAULT_RESULT = new CommendResult("not found","not fond","360011");
    
    public String err;
    
    public String api;
    public String errno;
    //热门视频
    public List<CommendItem> list;

    public CommendResult(String err,String api,String errno){
        this.api = api;
        this.err = err;
        this.errno = errno;
    }

}
