package com.snipex.suyog.mvvm.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.snipex.suyog.mvvm.repository.PhotoRepository;
import com.snipex.suyog.mvvm.model.PhotosResponse;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private PhotoRepository photoRepository;
        private LiveData<List<PhotosResponse>> photoResponseLiveData;

     private LiveData<Boolean> mIsUpdating ;


    public MainViewModel(@NonNull Application application) {
        super(application);

        photoRepository = new PhotoRepository();
           mIsUpdating = photoRepository.getIsUpdating();
           photoResponseLiveData= photoRepository.getPhotoList();
    }

        public LiveData<List<PhotosResponse>> getPhotoResponseLiveData() {
        return photoResponseLiveData;
    }
        public LiveData<Boolean> getIsUpdating() {
        return mIsUpdating;
    }
}
