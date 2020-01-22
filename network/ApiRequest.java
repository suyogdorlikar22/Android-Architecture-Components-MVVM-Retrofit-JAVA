package com.snipex.suyog.mvvm.network;

import com.snipex.suyog.mvvm.model.PhotosResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiRequest {

            //======Retrofite ==================

//    @GET("v2/everything/")
//    Call<ArticleResponse> getMovieArticles(
//            @Query("q") String query,
//            @Query("apikey") String apiKey
//    );

            
//             https://stackoverflow.com/questions/37698501/retrofit-2-path-vs-query
        //======RxJava ==================
    @GET("photos")
    Flowable<List<PhotosResponse>> getPhotoList(
//            @Query("q") String query,
//            @Query("apikey") String apiKey
    );
}
