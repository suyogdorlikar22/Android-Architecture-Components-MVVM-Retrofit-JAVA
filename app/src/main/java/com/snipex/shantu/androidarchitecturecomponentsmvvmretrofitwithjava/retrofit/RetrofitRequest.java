package com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {

    
    //======================  Normal========================================
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://newsapi.org/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    
    
    
    //==================Rx JAva==============================
//       private static Retrofit retrofitBaseURL = null;

//     private static OkHttpClient buildClient() {
//         return new OkHttpClient
//                 .Builder()
//                 .readTimeout(200, TimeUnit.MINUTES)
//                 .connectTimeout(200, TimeUnit.MINUTES)
//                 .retryOnConnectionFailure(true)
//                 .build();
//     }

//     public static Retrofit getRetrofitInstance() {
//         Gson gson = new GsonBuilder().create();
//         if (retrofitBaseURL == null) {
//             retrofitBaseURL = new Retrofit.Builder()
//                     .client(buildClient())
//                     .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                     .addConverterFactory(GsonConverterFactory.create(gson))
//                     .baseUrl(API.BASE_URL_DEMO)
//                     .build();
//         }
//         return retrofitBaseURL;
//     }
    
    
    
}
