package com.snipex.suyog.mvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.snipex.suyog.mvvm.R;
import com.snipex.suyog.mvvm.model.PhotosResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView my_recycler_view;
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private PhotoAdapter adapter;
    private ArrayList<PhotosResponse> photoArrayList = new ArrayList<>();
    MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();

        getProgressUpdate();

    }

    private void getProgressUpdate() {
        viewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean==true){
                    progressBar.setVisibility(View.VISIBLE);
                }else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * initialization of views and others
     *
     * @param @null
     */
    private void initialization() {
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        // use a linear layout manager
        layoutManager = new LinearLayoutManager(MainActivity.this);
        my_recycler_view.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        my_recycler_view.setHasFixedSize(true);

        // adapter
        adapter = new PhotoAdapter(MainActivity.this, photoArrayList);
        my_recycler_view.setAdapter(adapter);

        // View Model

        getMovieArticles();

    }

    /**
     * get movies articles from news api
     *
     * @param @null
     */



    private void getMovieArticles() {

        photoArrayList.clear();
        viewModel.getPhotoResponseLiveData().observe(this, new Observer<List<PhotosResponse>>() {
            @Override
            public void onChanged(@Nullable List<PhotosResponse> photosResponses) {
                Log.d(TAG, "onChanged: "+photosResponses.size());
                if (photosResponses.size()>0){
                    photoArrayList.addAll(photosResponses);
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }
}
