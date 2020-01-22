package com.dorlikar.suyog.mvvm.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {

    
    //======================  Normal========================================
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    //==================Rx JAva==============================
       private static Retrofit retrofitBaseURL = null;

     private static OkHttpClient buildClient() {
         return new OkHttpClient
                 .Builder()
                 .readTimeout(200, TimeUnit.MINUTES)
                 .connectTimeout(200, TimeUnit.MINUTES)
                 .retryOnConnectionFailure(true)
                 .build();
     }

     public static Retrofit getRetrofitInstance() {
         Gson gson = new GsonBuilder().create();
         if (retrofitBaseURL == null) {
             retrofitBaseURL = new Retrofit.Builder()
                     .client(buildClient())
                     .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                     .addConverterFactory(GsonConverterFactory.create(gson))
                     .baseUrl(BASE_URL)
                     .build();
         }
         return retrofitBaseURL;
     }
    
    
    
}
