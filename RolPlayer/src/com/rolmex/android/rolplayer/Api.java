package com.rolmex.android.rolplayer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rolmex.android.rolplayer.model.Result;
import com.rolmex.android.rolplayer.task.Task;
import com.rolmex.android.rolplayer.task.Task.TaskCallback;
import com.wole56.sdk.Video;

import android.content.Context;

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
    
    public static Result getHotView(Context context,String cid,String num,String page){
        String response = Video.getHotVideo(context, cid, num, page).toString();
        Result result = getGson().fromJson(response, Result.class);
        return ensureNotNull(result);
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