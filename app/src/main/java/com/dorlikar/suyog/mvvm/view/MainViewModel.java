package com.dorlikar.suyog.mvvm.view;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.dorlikar.suyog.mvvm.repository.PhotoRepository;
import com.dorlikar.suyog.mvvm.model.PhotosResponse;

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
