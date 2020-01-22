package com.snipex.suyog.mvvm.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.snipex.suyog.mvvm.R;
import com.snipex.suyog.mvvm.model.PhotosResponse;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private static final String TAG = PhotoAdapter.class.getSimpleName();

    private Context context;
    ArrayList<PhotosResponse> photoArrayList;

    public PhotoAdapter(Context context, ArrayList<PhotosResponse> photoArrayList) {
        this.context = context;
        this.photoArrayList = photoArrayList;
    }

    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_each_row_movie_article,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.ViewHolder viewHolder, int i) {

        PhotosResponse response = photoArrayList.get(i);

        Log.d(TAG, "onBindViewHolder: " + response.getUrl());
        Log.d(TAG, "onBindViewHolder: " + photoArrayList.size());
        viewHolder.tvTitle.setText(response.getTitle());
        Glide.with(context)
                .load(response.getUrl())
                .into(viewHolder.imgViewCover);

    }


    @Override
    public int getItemCount() {
        return photoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgViewCover;
        private final TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgViewCover=(ImageView) itemView.findViewById(R.id.imgViewCover);
            tvTitle=(TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
