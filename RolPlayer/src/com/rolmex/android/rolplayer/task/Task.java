package com.rolmex.android.rolplayer.task;

import com.rolmex.android.rolplayer.model.Result;

import android.os.AsyncTask;

public abstract class Task extends AsyncTask<Void,Void,Result>{
    public interface TaskCallback{
         void onTaskComplete(Task task,Result result);
    }
    private TaskCallback callback;
    
    public Task(TaskCallback callback){
        this.callback = callback;
    }
    @Override
    protected void onPostExecute(Result result){
        if(callback!=null){
            callback.onTaskComplete(this, result);
        }
    }

}
