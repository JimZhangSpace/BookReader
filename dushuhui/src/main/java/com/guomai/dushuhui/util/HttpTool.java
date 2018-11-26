package com.guomai.dushuhui.util;
import com.guomai.dushuhui.config.AppConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/9/3 0003.
 */

public class HttpTool {
    public static String uid = "0";
    public static String token = "20170328";
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("application/json;charset=utf-8");
    private static OkHttpClient client =new OkHttpClient();    //实例话对象
    public static void post(String urlString,String params, Callback callback)    //用一个完整url获取一个string对象
    {
        if(params==null)
        {
            params = "";
        }
        String HttpToken = SharePrefUtil.getString("HttpToken",null);
        if(HttpToken!=null)
        {
            token = HttpToken;
        }
//        final StringBuffer sb = new StringBuffer(AppConstant.productServer);
//        sb.append(urlString);
//        sb.append("?uid=")
//        .append(uid)
//        .append("&token=")
//        .append(token)
//        .append("&innerVersion=")
//                .append(AppConstant.inner_version)
//                .append("&")
//                .append("clientType=android&role=renter")
//
//        ;

        final String url = String.format("%s?uid=%s&token=%s&clientType=android&innerVersion=%s&role=renter",AppConstant.productServer+urlString, uid,token, AppConstant.inner_version);
        Request request = new Request.Builder()
//                .addHeader("uid", uid)
//                .addHeader("token", token)
//                .addHeader("clientType", "android")
//                .addHeader("innerVersion", AppConstant.inner_version)
                .addHeader("accept", "*/*")
                .addHeader("connection", "Keep-Alive")
                .addHeader("Content-Type", "application/json;charset=utf-8")
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, params))
                .build();
        client.newCall(request).enqueue(callback);

    }

    public static void post(String urlString, Map<String,String> params,Callback callback)    //用一个完整url获取一个string对象
    {

        if(params==null)
        {
            params = new HashMap<>();
        }
        String HttpToken = SharePrefUtil.getString("HttpToken",null);
        if(HttpToken!=null)
        {
            token = HttpToken;
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            //追加表单信息
            builder.add(key, params.get(key));
        }
        //生成表单实体对象
        RequestBody formBody = builder.build();
        final String url = String.format("%s?uid=%s&token=%s&clientType=android&innerVersion=%s&role=renter",AppConstant.productServer+urlString, uid,token, AppConstant.inner_version);
        Request request = new Request.Builder()
//                .addHeader("uid", uid)
//                .addHeader("token", token)
//                .addHeader("clientType", "android")
//                .addHeader("innerVersion", AppConstant.inner_version)
                .addHeader("accept", "*/*")
                .addHeader("connection", "Keep-Alive")
                .addHeader("Content-Type", "application/json;charset=utf-8")
                .url(url)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(callback);

    }

    public static void get(String urlString,Map<String, String> paramsMap, Callback callback)    //用一个完整url获取一个string对象
    {

        try {

            String HttpToken = SharePrefUtil.getString("HttpToken",null);
            if(HttpToken!=null)
            {
                token = HttpToken;
            }
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            //生成表单实体对象

            final String url = String.format("%s?uid=%s&token=%s&clientType=android&innerVersion=%s&role=renter",AppConstant.productServer+urlString, uid,token, AppConstant.inner_version);
            Request request = new Request.Builder()
//                .addHeader("uid", uid)
//                .addHeader("token", token)
//                .addHeader("clientType", "android")
//                .addHeader("innerVersion", AppConstant.inner_version)
                    .addHeader("accept", "*/*")
                    .addHeader("connection", "Keep-Alive")
                    .addHeader("Content-Type", "application/json;charset=utf-8")
                    .url(url)
                    .get()
                    .build();
            client.newCall(request).enqueue(callback);
        }catch (Exception e)
        {

        }


    }


    public static OkHttpClient getClient(){
            return client;
    }







}
