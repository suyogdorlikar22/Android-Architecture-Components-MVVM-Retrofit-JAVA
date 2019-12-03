package com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.retrofit;

import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.response.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

            //======Retrofite ==================

    @GET("v2/everything/")
    Call<ArticleResponse> getMovieArticles(
            @Query("q") String query,
            @Query("apikey") String apiKey
    );
    
            
//             https://stackoverflow.com/questions/37698501/retrofit-2-path-vs-query
        //======RxJava ==================
//    @GET("v2/everything/")
//    Flowable<List<ArticleResponse>> getMovieArticles(
//            @Query("q") String query,
//            @Query("apikey") String apiKey
//    );
}
