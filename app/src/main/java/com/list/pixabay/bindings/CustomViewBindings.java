package com.list.pixabay.bindings;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class CustomViewBindings {

    @BindingAdapter("setAdapter")
    public static void bindRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("userUrl")
    public static void bindUserImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView).load(imageUrl).apply(RequestOptions.circleCropTransform()).into(imageView);
    }

    @BindingAdapter("imageUrl")
    public static void bindPreviewImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView).load(imageUrl).into(imageView);
    }
}