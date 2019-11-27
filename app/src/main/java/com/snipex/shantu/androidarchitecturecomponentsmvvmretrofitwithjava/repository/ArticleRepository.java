package com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.response.ArticleResponse;
import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.retrofit.ApiRequest;
import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {
    private static final String TAG = ArticleRepository.class.getSimpleName();
    private ApiRequest apiRequest;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();
    public ArticleRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    
      //=========================Retrofite-=======================

    public LiveData<ArticleResponse> getMovieArticles(String query, String key) {
        final MutableLiveData<ArticleResponse> data = new MutableLiveData<>();
        apiRequest.getMovieArticles(query, key)
                .enqueue(new Callback<ArticleResponse>() {

                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);

                        if (response.body() != null) {
                            data.postValue(response.body());

                            Log.d(TAG, "articles total result:: " + response.body().getTotalResults());
                            Log.d(TAG, "articles size:: " + response.body().getArticles().size());
                            Log.d(TAG, "articles title pos 0:: " + response.body().getArticles().get(0).getTitle());
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                        data.postValue(null);
                    }
                });
        return data;
    }
    
    //====================== END ==================

       //======================RxJava =================================

//        public LiveData<List<ArticleResponse>> getMovieArticles(String query, String key) {
//            final MutableLiveData<List<ArticleResponse>> data = new MutableLiveData<>();
// mIsUpdating.setValue(true);
//            apiRequest.getMovieArticles(query,key)
//                    .toObservable()
//                    .subscribeOn(Schedulers.io())
//                    .subscribe(new Observer<ArticleResponse>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//
//                        @Override
//                        public void onNext(ArticleResponse articleResponse) {
//                            if (articleResponse!=null){
//                            Log.d(TAG, "articles total result:: " + articleResponse.getArticles().get(0).getTitle());
//                                data.postValue(articleResponse);
//      mIsUpdating.setValue(false);
//                            }
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            Log.d(TAG, "articles total result:: " +e);
//                            data.postValue("");
    //      mIsUpdating.setValue(false);

//
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    });
//
//            return data;
//        }



  
}
