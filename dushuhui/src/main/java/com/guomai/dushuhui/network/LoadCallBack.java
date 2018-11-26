package com.guomai.dushuhui.network;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by panzhizhou on 2018/1/21.
 */

public abstract class LoadCallBack<T> extends BaseCallBack<T> {

    protected  void OnRequestBefore(Request request){

    }



    protected  void onResponse(Response response)
    {

    }



    protected  void inProgress(int progress, long total , int id){

    }
}

