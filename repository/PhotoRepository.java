package com.snipex.suyog.mvvm.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.snipex.suyog.mvvm.model.PhotosResponse;
import com.snipex.suyog.mvvm.network.ApiRequest;
import com.snipex.suyog.mvvm.network.RetrofitRequest;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PhotoRepository {
    private static final String TAG = PhotoRepository.class.getSimpleName();
    private ApiRequest apiRequest;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public PhotoRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }


    public LiveData<Boolean> getIsUpdating() {

        return mIsUpdating;
    }


    //====================== END ==================

    //======================RxJava =================================

    public LiveData<List<PhotosResponse>> getPhotoList() {
        final MutableLiveData<List<PhotosResponse>> data = new MutableLiveData<>();
        mIsUpdating.setValue(true);
        apiRequest.getPhotoList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<PhotosResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<PhotosResponse> articleResponses) {
                        Log.d(TAG, "onResponse response:: " + articleResponses);
                        if (articleResponses != null) {
                            data.postValue(articleResponses);
                            mIsUpdating.postValue(false);

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mIsUpdating.postValue(false);
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return data;
    }


}
