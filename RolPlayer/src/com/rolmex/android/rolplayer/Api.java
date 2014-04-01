package com.rolmex.android.rolplayer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rolmex.android.rolplayer.model.CommendResult;
import com.rolmex.android.rolplayer.model.HotResult;
import com.rolmex.android.rolplayer.model.Result;
import com.rolmex.android.rolplayer.task.CommendTask;
import com.rolmex.android.rolplayer.task.CommendTask.CTaskCallback;
import com.rolmex.android.rolplayer.task.Task;
import com.rolmex.android.rolplayer.task.Task.TaskCallback;
import com.wole56.sdk.Video;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Api {
    
    public static void getHotVideo(final Context context,final String cid, final String num,final String page,TaskCallback callback){
        new Task(callback){

            @Override
            protected Result doInBackground(Void... params) {
                // TODO Auto-generated method stub
                return getHotView(context,cid,num,page);
            }
            
        }.execute();
    }
    
    public static void getRecommendVideo(final Context context,final String mid,final String num,final String page,CTaskCallback callback){
        new CommendTask(callback){

            @Override
            protected CommendResult doInBackground(Void... params) {
                // TODO Auto-generated method stub
                return getRecommendView(context,mid,num,page);
            }
            
        }.execute();
        
    }
    public static void getVideoAddr(final Context context,final String vid,TaskCallback callback){
        new Task(callback){

            @Override
            protected Result doInBackground(Void... params) {
                // TODO Auto-generated method stub
                return getVideoAddr(context,vid);
            }
            
        }.execute();
    }
    
    public static Result getVideoAddr(Context context,String vid){
        String response = Video.getVideoAddress(context, vid).toString();
        Log.e("antking_addr", response);
        Result result = getGson().fromJson(response, Result.class);
        Log.e("antking_result", result.err+","+result.status);
        
        return ensureNotNull(result);
    }
    
    public static Result getHotView(Context context,String cid,String num,String page){
        String response = Video.getHotVideo(context, cid, num, page).toString();
        Result result = getGson().fromJson(response, Result.class);
        return ensureNotNull(result);
    }
    public static CommendResult getRecommendView(Context context,String mid,String num,String page){
        String response = Video.getRecommendVideo(context, mid, num, page).toString();
      
        CommendResult  result = getGson().fromJson(response, CommendResult.class);
        return ensureNotNull(result);
    }
    
   private static CommendResult ensureNotNull(CommendResult result){
       return result == null ? CommendResult.DEFAULT_RESULT:result;
   }
    private static Result ensureNotNull(Result result) {
        return result == null ? Result.DEFAULT_RESULT : result;
    }
    public static class DateDeserializer implements JsonDeserializer<Date> {
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            String JSONDateToMilliseconds = "\\/(Date\\((.*?)(\\+.*)?\\))\\/";
            Pattern pattern = Pattern.compile(JSONDateToMilliseconds);
            Matcher matcher = pattern.matcher(json.getAsJsonPrimitive().getAsString());
            String result = matcher.replaceAll("$2");
            return new Date(Long.valueOf(result));
        }
    }
    public static Gson getGson() {
        GsonBuilder gsonb = new GsonBuilder();
        DateDeserializer ds = new DateDeserializer();
        gsonb.registerTypeAdapter(Date.class, ds);
        Gson gson = gsonb.create();
        return gson;
    }

}
