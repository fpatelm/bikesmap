package com.faizal.bikesmap;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by fpatel on 21/05/2017.
 */

public class Utils {
    public static OkHttpClient GetRequestHeader(){
 /*       HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);*/
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(40,TimeUnit.SECONDS)
                .writeTimeout(40,TimeUnit.SECONDS).build();
    }
}
