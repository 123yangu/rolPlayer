package com.rolmex.android.rolplayer.model;

import java.util.List;



public class Result {
    public static final Result DEFAULT_RESULT = new Result("not found","not fond","360011");
    
    public String err;
    
    public String api;
    public String errno;
    
    public List<HotItemBean> list;
    
   

    public Result(String err,String api,String errno){
        this.api = api;
        this.err = err;
        this.errno = errno;
    }
}
